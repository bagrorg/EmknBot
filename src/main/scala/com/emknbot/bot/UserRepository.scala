package com.emknbot.bot

import com.emknbot.bot.ComponentsAliases.ID

trait UserRepository {
  def save(user: User)
  def findByID(id : ID) : List[Event]

  def refreshAll()
  def refreshByID()
}