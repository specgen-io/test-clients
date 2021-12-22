package testservice.client.v2

import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.softwaremill.sttp.akkahttp.AkkaHttpBackend
import testservice.client.tests.Util
import testservice.client.v2.echo._
import testservice.client.v2.models._

class EchoClientSpec extends FlatSpec {
  implicit val httpBackend = AkkaHttpBackend()

  "echoBody" should "return body with same members values" in {
    val client = new EchoClient(Util.service_url)
    val expected = Message(boolField = true, stringField = "some string")
    val responseFuture = client.echoBody(expected)
    val actual = Await.ready(responseFuture, Duration.Inf).value.get.get
    assert(actual == expected)
  }
}