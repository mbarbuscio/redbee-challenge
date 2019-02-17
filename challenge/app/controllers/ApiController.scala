package controllers

import com.fasterxml.jackson.databind.JsonNode
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.mvc.Http.RequestBody
import services.BoardService
import models.{Board}

import scala.concurrent._
import ExecutionContext.Implicits.global

@Singleton
class ApiController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getBoards() = Action.async { implicit  request: Request[AnyContent] =>
      BoardService.getAllBoards(request.headers.get("X-User").get) map { boards =>
        Ok(Json.toJson(boards))
      }
  }

  def createBoard() = Action.async { implicit request: Request[AnyContent] =>
    val body = request.body.asJson;

    BoardService.createBoard(newBoardRequest(body.get, request.headers.get("X-User").get).get).map {
      o => Ok
    }
  }

  private def newBoardRequest(json: JsValue, user: String): JsResult[Board] = {
    val name = (json \ "name").as[String];
    JsSuccess(Board(0,name,user))
  }
}
