package models;

import play.api.libs.json._
import scala.concurrent.Future

import slick.jdbc.MySQLProfile.api._
import scala.concurrent._
import ExecutionContext.Implicits.global

case class Board(id: Long, name: String, owner: String)

class BoardsTable(tag: slick.jdbc.MySQLProfile.api.Tag) extends Table[Board](tag, "Board") {
  def id = column[Long]("Id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("BoardNm")
  def owner = column[String]("OwnerNm")

  override def * = (id, name, owner) <>((Board.apply _).tupled, Board.unapply)

  def pk = primaryKey("PK_Board", (id))
}

object Board {

  val table = TableQuery[BoardsTable]

  implicit val format = Json.format[Board]

}
