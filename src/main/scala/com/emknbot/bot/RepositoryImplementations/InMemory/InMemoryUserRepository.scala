package com.emknbot.bot

import cats.Monad

import java.util.concurrent.ConcurrentHashMap
import scala.jdk.CollectionConverters.ConcurrentMapHasAsScala

class InMemoryUserRepository[F[_] : Monad] extends UserRepository[F, ID] {
  private val OuterMonad = Monad[F]
  private val repo = new ConcurrentHashMap[ID, User].asScala


  override def save(id : ID, data: User): F[Unit] = {
    repo.updateWith(id) {
      case None => Some(data)
      case Some(d) => Some(data)
    }
    OuterMonad.pure ()
  }

  override def findByID(id: ID): F[Either[String, User]] = OuterMonad.pure {
    repo.get(id) match {
      case None => Left("No such element in repo")
      case Some(d) => Right(d)
    }
  }

  override def getAll(): F[List[(ID, User)]] = OuterMonad.pure {
    repo.toList
  }
}
