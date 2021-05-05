package com.emknbot.bot

import java.time.ZonedDateTime

trait EventRepository[F[_], Id] extends Repository[F, Id, List[Event]] {
    def getEvents(id : Id, begin : ZonedDateTime, end : ZonedDateTime): F[Either[String, List[Event]]]
}