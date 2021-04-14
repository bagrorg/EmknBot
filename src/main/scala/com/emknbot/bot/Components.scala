package com.emknbot.bot

import java.time.ZonedDateTime

import canoe.models.MessageEntity.Email
import sun.security.util.Password


case class User(name : String, email: Email, password: Password)
case class Event(description: String, dateTime : ZonedDateTime)
case class Calendar(schedule: Map[ZonedDateTime, List[Event]])

