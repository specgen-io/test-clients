package test_client

import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import test_client.clients.check.CheckClient
import test_client.clients.echo.*
import test_client.models.*
import java.math.BigDecimal
import java.time.*
import java.util.*

@MicronautTest
class EchoClientTest {
    @Inject
    @field:Client("/")
    lateinit var echoClient: EchoClient

    private val intValue = 123
    private val longValue = 12345
    private val floatValue = 1.23f
    private val doubleValue = 12.345
    private val decimalValue = BigDecimal("12345")
    private val boolValue = true
    private val stringValue = "the value"
    private val stringOptValue = "the value"
    private val stringDefaultedValue = "value"
    private val stringArrayValue = listOf("the str1", "the str2")
    private val uuidValue = UUID.fromString("123e4567-e89b-12d3-a456-426655440000")
    private val dateValue = LocalDate.parse("2020-01-01")
    private val dateArrayValue = listOf(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"))
    private val datetimeValue = LocalDateTime.parse("2019-11-30T17:45:55")
    private val enumValue = Choice.SECOND_CHOICE

    @Test
    fun echoBodyString() {
        val request = "Some text"
        val response = echoClient.echoBodyString(request)
        assertEquals(request, response)
    }

    @Test
    fun echoBodyModel() {
        val request = Message(intValue, stringValue)
        val response = echoClient.echoBodyModel(Message(intValue, stringValue))
        assertEquals(request, response)
    }

    @Test
    fun echoBodyArray() {
        val request = listOf("the str1", "the str2")
        val response = echoClient.echoBodyArray(request)
        assertEquals(request, response)
    }

    @Test
    fun echoBodyMap() {
        val request = hashMapOf("string_field" to "the value", "string_field_2" to "the value_2")
        val response = echoClient.echoBodyMap(request)
        assertEquals(request, response)
    }

    @Test
    fun echoQuery() {
        val expected = Parameters(
            intValue,
            longValue.toLong(),
            floatValue,
            doubleValue,
            decimalValue,
            boolValue,
            stringValue,
            stringOptValue,
            stringDefaultedValue,
            stringArrayValue,
            uuidValue,
            dateValue,
            dateArrayValue,
            datetimeValue,
            enumValue
        )
        val response = echoClient.echoQuery(
            intValue,
            longValue.toLong(),
            floatValue,
            doubleValue,
            decimalValue,
            boolValue,
            stringValue,
            stringOptValue,
            stringDefaultedValue,
            stringArrayValue,
            uuidValue,
            dateValue,
            dateArrayValue,
            datetimeValue,
            enumValue
        )

        assertEquals(expected, response)
    }

    @Test
    fun echoHeader() {
        val expected = Parameters(
            intValue,
            longValue.toLong(),
            floatValue,
            doubleValue,
            decimalValue,
            boolValue,
            stringValue,
            stringOptValue,
            stringDefaultedValue,
            stringArrayValue,
            uuidValue,
            dateValue,
            dateArrayValue,
            datetimeValue,
            enumValue
        )
        val response = echoClient.echoHeader(
            intValue,
            longValue.toLong(),
            floatValue,
            doubleValue,
            decimalValue,
            boolValue,
            stringValue,
            stringOptValue,
            stringDefaultedValue,
            stringArrayValue,
            uuidValue,
            dateValue,
            dateArrayValue,
            datetimeValue,
            enumValue
        )

        assertEquals(expected, response)
    }

    @Test
    fun echoUrlParams() {
        val expected = UrlParameters(
            intValue,
            longValue.toLong(),
            floatValue,
            doubleValue,
            decimalValue,
            boolValue,
            stringValue,
            uuidValue,
            dateValue,
            datetimeValue,
            enumValue
        )
        val response = echoClient.echoUrlParams(
            intValue,
            longValue.toLong(),
            floatValue,
            doubleValue,
            decimalValue,
            boolValue,
            stringValue,
            uuidValue,
            dateValue,
            datetimeValue,
            enumValue
        )

        assertEquals(expected, response)
    }

    @Test
    fun echoEverything() {
        val expected = Everything(
            Message(intValue, stringValue),
            floatValue,
            boolValue,
            uuidValue,
            datetimeValue,
            dateValue,
            decimalValue
        )
        val response = echoClient.echoEverything(
            Message(intValue, stringValue),
            floatValue,
            boolValue,
            uuidValue,
            datetimeValue,
            dateValue,
            decimalValue
        )

        assertThat(expected).usingRecursiveComparison().isEqualTo(response)
    }

    @Test
    fun echoSuccess_ok() {
        val expected = EchoSuccessResponse.Ok(OkResult("ok"))
        val response = echoClient.echoSuccess("ok")

        assertThat(expected).usingRecursiveComparison().isEqualTo(response)
    }

    @Test
    fun echoSuccess_created() {
        val expected = EchoSuccessResponse.Created(CreatedResult("created"))
        val response = echoClient.echoSuccess("created")

        assertThat(expected).usingRecursiveComparison().isEqualTo(response)
    }

    @Test
    fun echoSuccess_accepted() {
        val expected = EchoSuccessResponse.Accepted(AcceptedResult("accepted"))
        val response = echoClient.echoSuccess("accepted")

        assertThat(expected).usingRecursiveComparison().isEqualTo(response)
    }
}
