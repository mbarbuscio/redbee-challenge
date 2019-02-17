package services;
import scala.concurrent.Future
import models.{Board, BoardDTO}

import scala.concurrent._
import ExecutionContext.Implicits.global
import database.DataBasePool
import cats.data.Kleisli
import repositories.BoardRepository


object BoardService {

  private val DBConnection: DataBasePool = DataBasePool.getConnection

  def addBoard(): Option[String] = {
    Some("Works")
  }

  def getAllBoards(userId: String): Future[Seq[Board]] = {
    BoardRepository.getBoards(userId).run(DBConnection)
  }

  def getBoard(userId: String, boardId: Long): Future[BoardDTO] = {
    BoardRepository.getBoard(userId, boardId).run(DBConnection).flatMap(board => {
      BoardRepository.getHashTags(boardId).run(DBConnection).map(hashTags => {
        BoardDTO(board, hashTags)
      })
    })
  }

  def createBoard(board: Board): Future[Option[Int]] = {
    BoardRepository.createBoard(board).run(DBConnection)
  }

}