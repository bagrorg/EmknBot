package com.emknbot.bot

trait Subscriber {
  def update(event: Event): Unit
}