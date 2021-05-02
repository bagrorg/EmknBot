package com.emknbot.bot

import java.time.ZonedDateTime

trait EventRepository[F[_], Id] extends Repository[F, Id, Event] {
    def getEvents(begin : ZonedDateTime, end : ZonedDateTime): List[Event]
}