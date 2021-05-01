package com.emknbot.bot

import scala.collection.mutable

trait Subscriber {
  def update(event: Event): Unit
}

trait SubscriptionStorage[F[_]] {
  val subscribers: mutable.Set[(Subscriber, Event)]

  def subscribe(subscriber: Subscriber, event: Event): Unit
  def unsubscribe(subscriber: Subscriber, event: Event) : Unit
}
