package com.emknbot.bot

trait Notifier {
  def subscribe(user: User)
  def unsubscribe(user: User)
  def notifySubscribers()
}