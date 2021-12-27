package test_client

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import test_client.clients.check.*
import test_client.clients.echo.*
import test_client.models.*
import java.math.BigDecimal
import java.time.*
import java.util.*

class ClientTest {
    private val baseUrl = "http://localhost:8081"

    @Test
    fun echoBodyString_responseIsEqualToRequest() {
        val client = EchoClient(baseUrl)

        val request = "TWFueSBoYW5kcyBtYWtlIGxpZ2h0IHdvcmsu"
        val response = client.echoBodyString(request)

        assertEquals(request, response)
    }

    @Test
    fun echoBodyString_doesntThrowException() {
        val client = EchoClient(baseUrl)

        assertDoesNotThrow<String> {
            client.echoBodyString("TWFueSBoYW5kcyBtYWtlIGxpZ2h0IHdvcmsu")
        }
    }

    @Test
    fun echoBody_responseIsEqualToRequest() {
        val client = EchoClient(baseUrl)

        val request = Message(123, "the string")
        val response = client.echoBody(request)

        assertEquals(request, response)
    }

    @Test
    fun echoBody_doesntThrowException() {
        val client = EchoClient(baseUrl)

        assertDoesNotThrow<Message> {
            client.echoBody(Message(123, "the string"))
        }
    }

    @Test
    fun echoQuery_responseIsEqualToRequest() {
        val client = EchoClient(baseUrl)

        val intQuery = 123
        val longQuery = 12345
        val floatQuery = 1.23f
        val doubleQuery = 12.345
        val decimalQuery = BigDecimal("12345")
        val boolQuery = true
        val stringQuery = "the value"
        val stringOptQuery = "the value"
        val stringDefaultedQuery = "value"
        val stringArrayQuery = listOf("the str1", "the str2")
        val uuidQuery = UUID.fromString("123e4567-e89b-12d3-a456-426655440000")
        val dateQuery = LocalDate.parse("2020-01-01")
        val dateArrayQuery = listOf(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"))
        val datetimeQuery = LocalDateTime.parse("2019-11-30T17:45:55")
        val enumQuery = Choice.SECOND_CHOICE

        val request = Parameters(
            intQuery,
            longQuery.toLong(),
            floatQuery,
            doubleQuery,
            decimalQuery,
            boolQuery,
            stringQuery,
            stringOptQuery,
            stringDefaultedQuery,
            stringArrayQuery,
            uuidQuery,
            dateQuery,
            dateArrayQuery,
            datetimeQuery,
            enumQuery
        )
        val response = client.echoQuery(
            intQuery,
            longQuery.toLong(),
            floatQuery,
            doubleQuery,
            decimalQuery,
            boolQuery,
            stringQuery,
            stringOptQuery,
            stringDefaultedQuery,
            stringArrayQuery,
            uuidQuery,
            dateQuery,
            dateArrayQuery,
            datetimeQuery,
            enumQuery
        )

        assertEquals(request, response)
    }

    @Test
    fun echoQuery_doesntThrowException() {
        val client = EchoClient(baseUrl)

        val intQuery = 123
        val longQuery = 12345
        val floatQuery = 1.23f
        val doubleQuery = 12.345
        val decimalQuery = BigDecimal("12345")
        val boolQuery = true
        val stringQuery = "the value"
        val stringOptQuery = "the value"
        val stringDefaultedQuery = "value"
        val stringArrayQuery = listOf("the str1", "the str2")
        val uuidQuery = UUID.fromString("123e4567-e89b-12d3-a456-426655440000")
        val dateQuery = LocalDate.parse("2020-01-01")
        val dateArrayQuery = listOf(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"))
        val datetimeQuery = LocalDateTime.parse("2019-11-30T17:45:55")
        val enumQuery = Choice.SECOND_CHOICE

        assertDoesNotThrow<Parameters> {
            client.echoQuery(
                intQuery,
                longQuery.toLong(),
                floatQuery,
                doubleQuery,
                decimalQuery,
                boolQuery,
                stringQuery,
                stringOptQuery,
                stringDefaultedQuery,
                stringArrayQuery,
                uuidQuery,
                dateQuery,
                dateArrayQuery,
                datetimeQuery,
                enumQuery
            )
        }
    }

    @Test
    fun echoHeader_responseIsEqualToRequest() {
        val client = EchoClient(baseUrl)

        val intHeader = 123
        val longHeader = 12345
        val floatHeader = 1.23f
        val doubleHeader = 12.345
        val decimalHeader = BigDecimal("12345")
        val boolHeader = true
        val stringHeader = "the value"
        val stringOptHeader = "the value"
        val stringDefaultedHeader = "value"
        val stringArrayHeader = listOf("the str1", "the str2")
        val uuidHeader = UUID.fromString("123e4567-e89b-12d3-a456-426655440000")
        val dateHeader = LocalDate.parse("2020-01-01")
        val dateArrayHeader = listOf(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"))
        val datetimeHeader = LocalDateTime.parse("2019-11-30T17:45:55")
        val enumHeader = Choice.SECOND_CHOICE

        val request = Parameters(
            intHeader,
            longHeader.toLong(),
            floatHeader,
            doubleHeader,
            decimalHeader,
            boolHeader,
            stringHeader,
            stringOptHeader,
            stringDefaultedHeader,
            stringArrayHeader,
            uuidHeader,
            dateHeader,
            dateArrayHeader,
            datetimeHeader,
            enumHeader
        )
        val response = client.echoHeader(
            intHeader,
            longHeader.toLong(),
            floatHeader,
            doubleHeader,
            decimalHeader,
            boolHeader,
            stringHeader,
            stringOptHeader,
            stringDefaultedHeader,
            stringArrayHeader,
            uuidHeader,
            dateHeader,
            dateArrayHeader,
            datetimeHeader,
            enumHeader
        )
        assertEquals(request, response)
    }

    @Test
    fun echoHeader_doesntThrowException() {
        val client = EchoClient(baseUrl)

        val intHeader = 123
        val longHeader = 12345
        val floatHeader = 1.23f
        val doubleHeader = 12.345
        val decimalHeader = BigDecimal("12345")
        val boolHeader = true
        val stringHeader = "the value"
        val stringOptHeader = "the value"
        val stringDefaultedHeader = "value"
        val stringArrayHeader = listOf("the str1", "the str2")
        val uuidHeader = UUID.fromString("123e4567-e89b-12d3-a456-426655440000")
        val dateHeader = LocalDate.parse("2020-01-01")
        val dateArrayHeader = listOf(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"))
        val datetimeHeader = LocalDateTime.parse("2019-11-30T17:45:55")
        val enumHeader = Choice.SECOND_CHOICE

        assertDoesNotThrow<Parameters?> {
            client.echoHeader(
                intHeader,
                longHeader.toLong(),
                floatHeader,
                doubleHeader,
                decimalHeader,
                boolHeader,
                stringHeader,
                stringOptHeader,
                stringDefaultedHeader,
                stringArrayHeader,
                uuidHeader,
                dateHeader,
                dateArrayHeader,
                datetimeHeader,
                enumHeader
            )
        }
    }

    @Test
    fun echoUrlParams_responseIsEqualToRequest() {
        val client = EchoClient(baseUrl)

        val intUrl = 123
        val longUrl = 12345
        val floatUrl = 1.23f
        val doubleUrl = 12.345
        val decimalUrl = BigDecimal("12345")
        val boolUrl = true
        val stringUrl = "the value"
        val uuidUrl = UUID.fromString("123e4567-e89b-12d3-a456-426655440000")
        val dateUrl = LocalDate.parse("2020-01-01")
        val datetimeUrl = LocalDateTime.parse("2019-11-30T17:45:55")
        val enumUrl = Choice.SECOND_CHOICE

        val request = UrlParameters(
            intUrl,
            longUrl.toLong(),
            floatUrl,
            doubleUrl,
            decimalUrl,
            boolUrl,
            stringUrl,
            uuidUrl,
            dateUrl,
            datetimeUrl,
            enumUrl
        )
        val response = client.echoUrlParams(
            intUrl,
            longUrl.toLong(),
            floatUrl,
            doubleUrl,
            decimalUrl,
            boolUrl,
            stringUrl,
            uuidUrl,
            dateUrl,
            datetimeUrl,
            enumUrl
        )

        assertEquals(request, response)
    }

    @Test
    fun echoUrlParams_doesntThrowException() {
        val client = EchoClient(baseUrl)

        val intUrl = 123
        val longUrl = 12345
        val floatUrl = 1.23f
        val doubleUrl = 12.345
        val decimalUrl = BigDecimal("12345")
        val boolUrl = true
        val stringUrl = "the value"
        val uuidUrl = UUID.fromString("123e4567-e89b-12d3-a456-426655440000")
        val dateUrl = LocalDate.parse("2020-01-01")
        val datetimeUrl = LocalDateTime.parse("2019-11-30T17:45:55")
        val enumUrl = Choice.SECOND_CHOICE

        assertDoesNotThrow<UrlParameters> {
            client.echoUrlParams(
                intUrl,
                longUrl.toLong(),
                floatUrl,
                doubleUrl,
                decimalUrl,
                boolUrl,
                stringUrl,
                uuidUrl,
                dateUrl,
                datetimeUrl,
                enumUrl
            )
        }
    }

    @Test
    fun echoEverything_responseIsEqualToRequest() {
        val client = EchoClient(baseUrl)

        val body = Message(123, "the string")
        val floatQuery = 1.23f
        val boolQuery = true
        val uuidHeader = UUID.fromString("123e4567-e89b-12d3-a456-426655440000")
        val datetimeHeader = LocalDateTime.parse("2019-11-30T17:45:55")
        val dateUrl = LocalDate.parse("2020-01-01")
        val decimalUrl = BigDecimal("12345")

        val request = EchoEverythingResponse.Ok(
            Everything(
                body,
                floatQuery,
                boolQuery,
                uuidHeader,
                datetimeHeader,
                dateUrl,
                decimalUrl
            )
        )
        val response = client.echoEverything(body, floatQuery, boolQuery, uuidHeader, datetimeHeader, dateUrl, decimalUrl)

        assertThat(request).usingRecursiveComparison().isEqualTo(response)
    }

    @Test
    fun echoEverything_doesntThrowException() {
        val client = EchoClient(baseUrl)

        val body = Message(123, "the string")
        val floatQuery = 1.23f
        val boolQuery = true
        val uuidHeader = UUID.fromString("123e4567-e89b-12d3-a456-426655440000")
        val datetimeHeader = LocalDateTime.parse("2019-11-30T17:45:55")
        val dateUrl = LocalDate.parse("2020-01-01")
        val decimalUrl = BigDecimal("12345")

        assertDoesNotThrow<EchoEverythingResponse> {
            client.echoEverything(
                body,
                floatQuery,
                boolQuery,
                uuidHeader,
                datetimeHeader,
                dateUrl,
                decimalUrl
            )
        }
    }

    @Test
    fun checkEmpty_doesntThrowException() {
        val client = CheckClient(baseUrl)

        assertDoesNotThrow { client.checkEmpty() }
    }

    @Test
    fun checkForbidden_doesntThrowException() {
        val client = CheckClient(baseUrl)

        assertDoesNotThrow<CheckForbiddenResponse> { client.checkForbidden() }
    }
}
