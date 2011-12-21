package com.promindis.data

import org.specs2.Specification

final class ZipperSpecification extends Specification{def is =

  "Zipper Specification"                      ^
                                              p^
    "Left method in Zipper should"            ^
    "go left from current scope"              !e1^
    "return to root applying up to go Left"   !e2^
                                              p^
    "Right method in Zipper should"           ^
    "go right from root"                      !e3^
    "return to root applying up to go right"  !e4^
                                              p^
    "Navigation should"                       ^
    "prevent me from going too far"           !e5^
                                              p^
    "Update should"                           ^
    "allow me to update right node"           !e6

  val leftChild = Node(2,Node(3, Empty, Empty),Node(4, Empty, Empty))

  val rightChild = Node(5, Node(6, Empty, Empty), Empty)

  val updatedChild = Node(7, Node(6, Empty, Empty), Empty)

  val tree = Node(1, leftChild, rightChild)

  def e1()  =
    Zipper(tree, List()).left()
      .should(beSome(Zipper(leftChild, List(LeftSpot(1, rightChild)))))

  def e2() = {for (
      left <- Zipper(tree, List()).left();
      up <- left.up() )
    yield up}.should(beSome(Zipper(tree, List())))

  def e3() =
    Zipper(tree, List()).right()
      .should(beSome(Zipper(rightChild, List(RightSpot(1, leftChild)))))

  def e4() = {for (
      right <- Zipper(tree, List()).right();
      up <- right.up() )
    yield up}.should(beSome(Zipper(tree, List())))

  def e5 = { for (
      right <- Zipper(tree, List()).right();
      deeperRight <- right.right();
      tooFar <- deeperRight.right()
    ) yield tooFar}.should(beNone)

  def e6 = {for(
    right <- Zipper(tree, List()).right()
  ) yield right.updated(_ => 7)}
    .should(beSome(Zipper(updatedChild, List(RightSpot(1, leftChild)))))
}