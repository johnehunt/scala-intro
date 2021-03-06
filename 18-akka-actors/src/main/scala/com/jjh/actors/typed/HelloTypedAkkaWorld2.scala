package com.jjh.actors.typed

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.actor.typed.{ActorSystem, Behavior}

case class Message(info: String)

object HelloMessageWorldActor {
  def apply(): Behavior[Message] =
    Behaviors.setup(context => new HelloMessageWorldActor(context))
}

class HelloMessageWorldActor(context: ActorContext[Message])
  extends AbstractBehavior[Message](context) {

  override def onMessage(message: Message): Behavior[Message] = {
    println(message.info)
    this
  }
}

object HelloTypedAkkaWorld2 extends App {

  val actorSystem: ActorSystem[Message] =
    ActorSystem(HelloMessageWorldActor(), "MyActorSystem")

  actorSystem ! Message("Hello World")
  actorSystem ! Message("Goodbye World")

  // Cleanly shutdown the actor system
  actorSystem.terminate()

}
