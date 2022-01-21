package test_client

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import test_client.v2.clients.echo.*
import test_client.v2.models.*

class ClientV2Test {
    private val baseUrl = "http://localhost:8081"

    @Test
    fun echoBody_responseIsEqualToRequest() {
        val client = EchoClient(baseUrl)

        val request = Message(true, "the string")
        val response: Message = client.echoBodyModel(request)

        assertEquals(request, response)
    }

    @Test
    fun echoBody_doesntThrowException() {
        val client = EchoClient(baseUrl)

        assertDoesNotThrow {
            client.echoBodyModel(Message(true, "the string"))
        }
    }
}