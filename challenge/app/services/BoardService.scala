package services;
import scala.concurrent.Future;
import models.{Board}
import scala.concurrent._
import ExecutionContext.Implicits.global
import database.{DataBasePool}
import cats.data.Kleisli
import repositories.{BoardRepository}


object BoardService {

  private val DBConnection: DataBasePool = DataBasePool.getConnection

  def addBoard(): Option[String] = {
    Some("Works")
  }

  def getAllBoards(): Future[Seq[Board]] = {
    BoardRepository.getBoards("SomeUser").run(DBConnection)
  }

}