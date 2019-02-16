package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import services.BoardService
import scala.concurrent._
import ExecutionContext.Implicits.global

@Singleton
class ApiController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getBoards() = Action.async { implicit  request: Request[AnyContent] =>
      BoardService.getAllBoards map { boards =>
        Ok(Json.toJson(boards))
      }
  }
}
