package models

import play.api.libs.json._
import scala.concurrent.Future

import slick.jdbc.MySQLProfile.api._
import scala.concurrent._
import ExecutionContext.Implicits.global

case class Hashtag(id: Long, name: String, boardId: Long)

class HashtagTable(tag: slick.jdbc.MySQLProfile.api.Tag) extends Table[Hashtag](tag, "BoardHashtag") {
  def id = column[Long]("Id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("HashTagTxt")
  def boardId = column[Long]("BoardId")

  override def * = (id, name, boardId) <>((Hashtag.apply _).tupled, Hashtag.unapply)

  def board = foreignKey("board", boardId, Board.table)(_.id)
}

object Hashtag {
  val table = TableQuery[HashtagTable]

  implicit val format = Json.format[Hashtag]
}
