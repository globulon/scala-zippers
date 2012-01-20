package com.promindis.data

object UseTree {
	def main(args: Array[String]) {
//		println(Node[Int](1, Empty, Empty))
    for  {
      value <- Some(1)
    } yield println(value)
	}
}
