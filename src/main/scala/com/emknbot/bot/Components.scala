package com.emknbot.bot

import java.time.ZonedDateTime

sealed trait SourceType

case object ClassesSourceType extends SourceType
case object TasksSourceType extends SourceType
case object EventsSourceType extends SourceType

case class User(name : String, id : ID)
case class Event(eventType : SourceType, description: String, dateTimeStart : ZonedDateTime, dateTimeEnd : ZonedDateTime)
case class Calendar(user : User, events: List[Event])
