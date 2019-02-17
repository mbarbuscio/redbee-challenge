package repositories;
import scala.concurrent.Future
import models.{Board, Hashtag}

import scala.concurrent._
import ExecutionContext.Implicits.global
import database.DataBasePool
import cats.data.Kleisli
import slick.jdbc.MySQLProfile.api._


object BoardRepository {

  def getBoards(userId: String): Kleisli[Future, DataBasePool, Seq[Board]] = Kleisli{ db =>
    val findByUser = for {
      boards <- Board.table.filter(b => b.owner === userId)
    } yield boards

    db.executor.run(findByUser.result).map(bs => bs)
  }

  def createBoard(board: Board): Kleisli[Future, DataBasePool, Option[Int]] = Kleisli{ db =>
    val insert = Board.table ++= Seq(board)

    db.executor.run(insert).map{result => result map { x => x }}
  }

  def getBoard(userId: String, boardId: Long): Kleisli[Future, DataBasePool, Board] = Kleisli{ db =>
    val find = Board.table.filter(b => { b.owner === userId && b.id === boardId }).take(1)

    db.executor.run(find.result.head)
  }

  def getHashTags(boardId: Long): Kleisli[Future, DataBasePool, Seq[Hashtag]] = Kleisli{ db =>
    val find = Hashtag.table.filter(t => t.boardId === boardId)

    db.executor.run(find.result)
  }
}