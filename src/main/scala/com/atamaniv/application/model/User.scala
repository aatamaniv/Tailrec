package com.atamaniv.application.model

case class User(id: Long, username: String, address: Option[String], email: String)