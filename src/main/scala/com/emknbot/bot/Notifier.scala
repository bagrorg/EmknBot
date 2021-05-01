package com.emknbot.bot

trait Notifier[F[_]] {
  def notifyUser(user: User, event: Event): F[Unit]
}