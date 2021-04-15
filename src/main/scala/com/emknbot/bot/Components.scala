package com.emknbot.bot

import java.time.ZonedDateTime
import canoe.models.MessageEntity.Email
import com.emknbot.bot.ComponentsAliases.ID
import sun.security.util.Password

object ComponentsAliases {
  type ID = Int
}

case class User(name : String, email: Email, password: Password, id : ID)
case class Event(description: String, dateTimeStart : ZonedDateTime, dateTimeEnd : ZonedDateTime)
case class Calendar(user : User, events: List[Event])
