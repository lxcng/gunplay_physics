package com.mikeyu123.gunplay_physics.structs

import java.util.UUID

import com.mikeyu123.gunplay_physics.objects.{ImmovableObject, MovableObject, PhysicsObject, StaticObject}

//import scala.runtime.Statics
import scala.util.hashing.MurmurHash3


object Contact {
  def apply(a: PhysicsObject, b: PhysicsObject): Contact =
    Contact(Set(a, b), Vector(0, 0))
}

case class Contact(ab: Set[PhysicsObject], normal: Vector, state: Int = 0) {

  val (a, b) = (ab.head, ab.last) match {
    case (a: ImmovableObject, b: MovableObject) => (b, a)
    case (a: StaticObject, b: MovableObject) => (b, a)
    case (a: StaticObject, b: ImmovableObject) => (b, a)
    case (a, b) => (a, b)
  }

  def setNormal(vector: Vector): Contact = {
    Contact(ab, vector)
  }

  def other(physicsObject: PhysicsObject): PhysicsObject = {
    //    ab.filter(!physicsObject.equals(_)).head
    ab.find(!physicsObject.equals(_)).get
  }

  def swapSubject(old: PhysicsObject, next: PhysicsObject): Contact = {
    ab.contains(old) match {
      case true => Contact(ab - old + next, normal)
      case _ => this
    }
  }

  def hasObject(uUID: UUID): Boolean={
    ab.exists(_.id == uUID)
  }

  def remove: Contact = {
    Contact(ab, normal, -1)
  }

  def removeA: Contact={
    Contact(ab,normal, -2)
  }

  def removeB: Contact={
    Contact(ab,normal, -3)
  }
}
