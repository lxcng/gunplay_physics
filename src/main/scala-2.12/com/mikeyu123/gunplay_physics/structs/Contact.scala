package com.mikeyu123.gunplay_physics.structs

import com.mikeyu123.gunplay_physics.objects.PhysicsObject

/*
state:
  0 - init
  1 - presolve
  2 - solve
  3 - postsolve
  4 - solved
 */
object Contact {
  def apply(a: PhysicsObject, b: PhysicsObject): Contact = Contact(a, b, Point(0, 0), LineSegment(Point(0, 0), Point(0, 0)), 0)
}

case class Contact(a: PhysicsObject, b: PhysicsObject, contactPoint: Point, normal: LineSegment, state: Int) {

}
