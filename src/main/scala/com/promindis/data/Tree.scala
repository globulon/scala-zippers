package com.promindis.data

/**
 * Defines the tree algebraic data type
 **/
sealed trait Tree[+A]
object Empty extends Tree[Nothing]  {override def toString = "Empty"}
final case class Node[+A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

