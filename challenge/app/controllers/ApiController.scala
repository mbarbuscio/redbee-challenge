package controllers

import com.fasterxml.jackson.databind.JsonNode
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.mvc.Http.RequestBody
import services.BoardService
import models.Board
import utils.actions.{NewBoardRequest, UserRequest, userHandlerAction}

import scala.concurrent._
import ExecutionContext.Implicits.global

@Singleton
class ApiController @Inject()(cc: ControllerComponents, userHandler: userHandlerAction) extends AbstractController(cc) {

  def getBoards() = userHandler.async { request: UserRequest[AnyContent] =>
    BoardService.getAllBoards(request.userName.get).map {
      boards => {
        Ok(Json.toJson(boards))
      }
    }
  }

  def createBoard() = userHandler.async { request =>
    BoardService.createBoard(newBoardRequest(request.body.asJson.get, request.userName.get).get).map {
      o => Ok
    }
  }

  def getBoard(id: Long) = userHandler.async { request =>
    BoardService.getBoard(request.userName.get, id).map {
      board => Ok(Json.toJson(board))
    }
  }

  private def newBoardRequest(json: JsValue, user: String): JsResult[Board] = {
    val name = (json \ "name").as[String];
    JsSuccess(Board(0,name,user))
  }
}
