package com.emknbot.bot

trait Repository[F[_], Id, Data] {
  def save(data: Data) : F[Unit]
  def findByID(id : Id) : F[Either[String, Data]]
}