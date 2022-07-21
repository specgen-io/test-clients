package test_client

import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import test_client.v2.clients.echo.EchoClient
import test_client.v2.models.*

@MicronautTest
class EchoClientV2Test {
    @Inject
    @field:Client("/")
    lateinit var echoClient: EchoClient

    private val boolValue = true
    private val stringValue = "the value"

    @Test
    fun echoBodyModel() {
        val request = Message(boolValue, stringValue)
        val response = echoClient.echoBodyModel(Message(boolValue, stringValue))
        assertEquals(request, response)
    }
}
