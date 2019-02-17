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

  def deleteBoard(boardId: Long): Kleisli[Future, DataBasePool, Int] = Kleisli{ db =>
    val delete = Board.table.filter(b => b.id === boardId).delete

    (db.executor.run(delete))
  }


  def getHashTags(boardId: Long): Kleisli[Future, DataBasePool, Seq[Hashtag]] = Kleisli{ db =>
    val find = Hashtag.table.filter(t => t.boardId === boardId)

    db.executor.run(find.result)
  }

  def addHashTag(boardId: Long, hashTag: String): Kleisli[Future, DataBasePool, Option[Int]] = Kleisli{ db =>
    val insert = Hashtag.table ++= Seq(Hashtag(0,hashTag,boardId))

    db.executor.run(insert).map{result => result map {x => x}}
  }

  def deleteHashTag(id: Long): Kleisli[Future, DataBasePool, Int] = Kleisli{ db =>
    val delete = Hashtag.table.filter(t => t.id === id).delete

    db.executor.run(delete)
  }

  def deleteHashTagsByBoard(boardId: Long): Kleisli[Future, DataBasePool, Int] = Kleisli{ db =>
    val delete = Hashtag.table.filter(t => t.boardId === boardId).delete

    db.executor.run(delete)
  }
}