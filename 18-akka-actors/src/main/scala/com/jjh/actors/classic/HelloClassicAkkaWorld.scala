package com.jjh.actors.classic

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

class Greeter extends Actor {
  // def receive: Receive = { // Short hand form of following
  def receive: PartialFunction[Any, Unit] = {
    case "hello" => println("Hello World")
    case "Goodbye" => println("Goodbye World")
    case 42 => println("Its the answer to Everything")
    case _ => println("Hello Whoever")
  }
}

object HelloClassicAkkaWorld extends App {
  // configure the actor system
  val props: Props = Props(new Greeter())
  val system: ActorSystem = ActorSystem("MyActorSystem")
  // create an actor
  val actor: ActorRef = system.actorOf(props)
  // send messages to the actor
  actor ! "hello"
  actor ! "Goodbye"
  actor ! 42
  actor ! true

  //shutdown the actor system
  system.terminate()
}