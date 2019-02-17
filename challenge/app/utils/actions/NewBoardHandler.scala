package utils.actions

import javax.inject.Inject
import models.Board
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class NewBoardRequest[A](val newBoard: JsResult[Board], request: UserRequest[A]) extends WrappedRequest[A](request) {
  def userName = request.userName
}
/*
class newBoardHandler @Inject()(val parser: BodyParsers.Default)(implicit val executionContext: ExecutionContext)
  extends ActionBuilder[NewBoardRequest, AnyContent] with ActionRefiner[UserRequest, NewBoardRequest] {

  private def parseBody(json: JsValue, user: String): JsResult[Board] = {
    val name = (json \ "name").as[String];
    JsSuccess(Board(0, name, user))
  }

  private def jsonBody[A](body: A): Option[JsValue] = body match {
    case js: JsValue => Some(js)
    case any: AnyContent => any.asJson
    case _ => None
  }

  def refine[A](request: UserRequest[A]): Future[Either[Result,NewBoardRequest[A]]] = Future.successful {
    jsonBody(request.body) match {
      case Some(js) => Right(new NewBoardRequest[A](parseBody(js, request.userName.get), request))
      case _ => Left(Results.BadRequest)
    }
  }

  override def invokeBlock[A](request: Request[A], block: NewBoardRequest[A] => Future[Result]): Future[Result] = {
    block(request)
  }

}
*/