package utils.actions

import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class UserRequest[A](val userName: Option[String], request: Request[A]) extends WrappedRequest[A](request)

class userHandlerAction @Inject()(val parser: BodyParsers.Default)(implicit val executionContext: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent] with ActionTransformer[Request, UserRequest] {
  def transform[A](request: Request[A]) = Future.successful {
    new UserRequest[A](request.headers.get("X-User"), request)
  }

}