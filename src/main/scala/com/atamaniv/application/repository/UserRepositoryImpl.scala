package com.atamaniv.application.repository

import com.atamaniv.application.model.User
import slick.jdbc.H2Profile.api._

import scala.concurrent.Future


class UserRepositoryImpl extends UserRepository[Future] {

  val db = Database.forConfig("h2memUSERS")

  val users = TableQuery[Users]

  val schema = users.schema
  db.run(DBIO.seq(
    schema.create
  ))

  override def registerUser(user: User): Future[Int] = {
    db.run(users += user)
  }

  override def getById(id: Long): Future[Option[User]] =
    db.run(users.filter(_.id === id).result.headOption)

  override def getByUserName(username: String): Future[Option[User]] =
    db.run(users.filter(_.username === username).result.headOption)
}