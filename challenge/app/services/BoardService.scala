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

  /* Boards Methods */
  def getAllBoards(userId: String): Future[Seq[Board]] = {
    BoardRepository.getBoards(userId).run(DBConnection)
  }

  def getBoard(userId: String, boardId: Long): Future[BoardDTO] = {
    BoardRepository.getBoard(userId, boardId).run(DBConnection).flatMap(board => {
      BoardRepository.getHashTags(board.id).run(DBConnection).map(hashTags => {
        BoardDTO(board, hashTags)
      })
    })
  }

  def createBoard(board: Board): Future[Option[Int]] = {
    BoardRepository.createBoard(board).run(DBConnection)
  }

  def deleteBoard(boardId: Long) = {
    BoardRepository.deleteHashTagsByBoard(boardId).run(DBConnection).flatMap(d =>
      BoardRepository.deleteBoard(boardId).run(DBConnection)
    )
  }

  /* Hashtag Methods */
  def AddHashtag(userId: String, boardId: Long, hashTag: String): Future[Option[Int]] = {
    BoardRepository.getBoard(userId, boardId).run(DBConnection).flatMap(board => {
      BoardRepository.addHashTag(board.id, hashTag).run(DBConnection)
    })
  }

  def DeleteHashtag(userId: String, boardId: Long, id: Long): Future[Int] = {
    // Instead of directly deleting, lookup board and owner. Real life scenarios would contain an authorization piece to solve this
    BoardRepository.getBoard(userId, boardId).run(DBConnection).flatMap(board => {
      BoardRepository.deleteHashTag(id).run(DBConnection)
    })
  }

}