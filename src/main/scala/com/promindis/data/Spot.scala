package com.promindis.data

/**
 * So now the bread crumbs
 **/

sealed trait Spot[+A]
final case class LeftSpot[+A](value: A, right: Tree[A]) extends Spot[A]
final case class RightSpot[+A](value: A, left: Tree[A]) extends Spot[A]
