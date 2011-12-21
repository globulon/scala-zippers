package com.promindis.data

final case class Zipper[+A](tree: Tree[A], spots: List[Spot[A]]) {

  def updated[B >: A](f: A => B): Zipper[B] = {
    tree match {
      case Node(value, left, right) => Zipper(Node(f(value), left, right), spots)
      case Empty => this
    }
  }

  def left(): Option[Zipper[A]] = {
    tree match {
      case Node(value, left, right) => Some(Zipper[A](left, LeftSpot(value, right)::spots))
      case Empty => None
    }
  }

  def right(): Option[Zipper[A]] = {
    tree match {
      case Node(value, left, right) => Some(Zipper[A](right, RightSpot(value, left)::spots))
      case Empty => None
    }
  }

  def up(): Option[Zipper[A]]	= {
		spots match {
			case LeftSpot(value, right)::xs => 	Some(Zipper(Node(value, tree, right), xs))
			case RightSpot(value, left)::xs => 	Some(Zipper(Node(value, left, tree), xs))
			case Nil => None
		}
	}
}

