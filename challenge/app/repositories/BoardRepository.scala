package repositories;
import scala.concurrent.Future;
import models.{Board}
import scala.concurrent._
import ExecutionContext.Implicits.global
import database.{DataBasePool}
import cats.data.Kleisli
import slick.jdbc.MySQLProfile.api._


object BoardRepository {

  def getBoards(userId: String): Kleisli[Future, DataBasePool, Seq[Board]] = Kleisli{ db =>
    val findByUser = for {
      boards <- Board.table.filter(b => b.owner === userId)
    } yield boards

    db.executor.run(findByUser.result).map(bs => bs)
  }
}