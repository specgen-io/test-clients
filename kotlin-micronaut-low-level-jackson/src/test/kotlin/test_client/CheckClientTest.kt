package test_client

import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import test_client.clients.check.*
import test_client.models.Message

@MicronautTest
class CheckClientTest {
    @Inject
    @field:Client("/")
    lateinit var checkClient: CheckClient

    @Test
    fun checkEmpty_doesntThrowException() {
        assertDoesNotThrow { checkClient.checkEmpty() }
    }

    @Test
    fun checkEmptyResponse_doesntThrowException() {
        assertDoesNotThrow { checkClient.checkEmptyResponse(Message(123, "the value")) }
    }

    @Test
    fun checkForbidden_throwsException() {
        assertThrows<HttpClientResponseException> { checkClient.checkForbidden() }
    }
}
