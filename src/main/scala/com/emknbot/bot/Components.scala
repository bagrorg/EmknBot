package com.emknbot.bot

import java.time.ZonedDateTime
import canoe.models.MessageEntity.Email
import sun.security.util.Password

case class User(name : String, email: Email, password: Password, id : Additional.ID)
case class Event(description: String, dateTimeStart : ZonedDateTime, dateTimeEnd : ZonedDateTime)
case class Calendar(user : User, events: List[Event])
