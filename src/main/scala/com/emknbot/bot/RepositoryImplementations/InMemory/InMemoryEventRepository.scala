package com.emknbot.bot

import cats.Monad
import java.time.ZonedDateTime
import java.util.concurrent.ConcurrentHashMap
import scala.jdk.CollectionConverters.ConcurrentMapHasAsScala

class InMemoryEventRepository[F[_] : Monad] extends EventRepository[F, ID] {
    private val OuterMonad = Monad[F]
    private val repo = new ConcurrentHashMap[ID, List[Event]].asScala

    private def findInRepo(id: ID): Either[String, List[Event]] = repo.get(id) match {
        case None => Left("No such element in repo")
        case Some(d) => Right(d)
    }

    override def getEvents(id : ID, begin: ZonedDateTime, end: ZonedDateTime): F[Either[String, List[Event]]] = OuterMonad.pure {
        findInRepo(id) match {
            case Left(s) => Left(s)
            case Right(d) => Right(d.filter( ev => {
                val diffSt = begin.compareTo(ev.dateTimeStart)
                val diffEn = end.compareTo(ev.dateTimeEnd)
                diffSt <= 0 && diffEn >= 0
            }))
        }
    }

    override def save(id : ID, data: List[Event]): F[Unit] = {
        repo.updateWith(id) {
            case None => Some(data)
            case Some(d) => Some(data ++ d)
        }
        OuterMonad.pure ()
    }

    override def findByID(id: ID): F[Either[String, List[Event]]] = OuterMonad.pure { findInRepo(id) }

    override def getAll(): F[List[(ID, List[Event])]] = OuterMonad.pure {
        repo.toList
    }
}