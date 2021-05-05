package com.emknbot.bot

import cats.Id
import cats.catsInstancesForId._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.ZonedDateTime


class Tests extends AnyFunSuite with Matchers {
  val id1 = ID(123)
  val id2 = ID(321)

  test("inMemoryTest user") {
    val inMemUs = new InMemoryUserRepository[Id]

    val user1 = User("Savelij", ID(123))
    val user2 = User("Nikita", ID(321))

    inMemUs.save(id1, user1)
    inMemUs.save(id2, user2)

    val res1 = extract(inMemUs.findByID(ID(123)))
    val res2 = extract(inMemUs.findByID(ID(1)))

    val allRes = extract(inMemUs.getAll())

    assert(allRes == List((id2, user2), (id1, user1)))
    assert(res1 match {
      case Left(_) => false
      case Right(us) => us == user1
    })
    assert(res2 match {
      case Left(_) => true
      case Right(_) => false
    })


  }

  test("inMemoryTest event") {
    val inMemEv = new InMemoryEventRepository[Id]
    val ev1 = Event(ClassesSourceType, "blablabla", ZonedDateTime.parse("2019-04-03T11:21:12.123+05:30[UTC]"), ZonedDateTime.parse("2019-04-03T11:31:12.123+05:30[UTC]"))
    val ev2 = Event(ClassesSourceType, "blablabla", ZonedDateTime.parse("2019-05-05T22:21:12.123+05:30[UTC]"), ZonedDateTime.parse("2019-05-05T22:31:13.123+05:30[UTC]"))

    val range1 = ZonedDateTime.parse("2019-04-03T11:00:12.123+05:30[UTC]")
    val range2 = ZonedDateTime.parse("2019-04-03T11:53:12.123+05:30[UTC]")

    inMemEv.save(id1, List(ev1))
    inMemEv.save(id2, List(ev2))

    val res1 = extract(inMemEv.findByID(ID(123)))
    val res2 = extract(inMemEv.findByID(ID(1)))

    val allRes = extract(inMemEv.getAll())
    val rangeRes = extract(inMemEv.getEvents(id1, range1, range2))

    assert(allRes == List((id2, List(ev2)), (id1, List(ev1))))
    assert(res1 match {
      case Left(_) => false
      case Right(us) => us.head == ev1
    })
    assert(res2 match {
      case Left(_) => true
      case Right(_) => false
    })

    assert(rangeRes match {
      case Left(_) => false
      case Right(d) => d == List(ev1)
    })
  }
}
