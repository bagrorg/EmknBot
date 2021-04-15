package com.emknbot.bot

trait EventRepository[F[_], Id] extends Repository[F, Id, Event]