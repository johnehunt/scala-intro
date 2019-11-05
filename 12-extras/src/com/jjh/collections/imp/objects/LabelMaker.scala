package com.jjh.collections.imp.objects

case class Address(street: String, number: Int)

// Defines a trait to convert things into labels
trait LabelMaker[T] {
  def output(t: T): String
}

object LabelMaker {
  // Adapter class that converts Address to labels
  // and an instance created from it for use with implicit params
  implicit object AddressLabelMaker extends LabelMaker[Address] {
    def output(address: Address): String = {
       address.number + " @ " + address.street
    }
  }
  // label method that uses an implicit param
  // either
  // def printLabel[T](t: T)(implicit lm: LabelMaker[T]) = lm.output(t)
  // or via short hand form (with unnamed implicit param
  def label[T : LabelMaker](text: T): String = implicitly[LabelMaker[T]].output(text)
}