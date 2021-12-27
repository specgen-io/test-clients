package test_client.v2.clients.echo

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.*
import java.io.*
import java.math.BigDecimal
import java.time.*
import java.util.*

import test_client.*
import test_client.utils.*
import test_client.v2.models.*
import test_client.models.setupObjectMapper

class EchoClient(
	private val baseUrl: String,
	private val client: OkHttpClient = OkHttpClient(),
	private val objectMapper: ObjectMapper = setupObjectMapper(jacksonObjectMapper())
) {
	private val logger: Logger = LoggerFactory.getLogger(EchoClient::class.java)

	fun echoBody(body: Message): Message {
		val bodyJson = try {
			objectMapper.writeValueAsString(body)
		} catch (e: JsonProcessingException) {
			val errorMessage = "Failed to serialize JSON " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		val url = UrlBuilder(baseUrl)
		url.addPathSegment("v2")
		url.addPathSegment("echo/body")

		val requestBody = bodyJson.toRequestBody("application/json".toMediaTypeOrNull())
		val request = RequestBuilder("POST", url.build(), requestBody)

		logger.info("Sending request, operationId: echo.echo_body, method: POST, url: /v2/echo/body")
		val response = try {
			client.newCall(request.build()).execute()
		} catch (e: IOException) {
			val errorMessage = "Failed to execute the request " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		return when (response.code) {
			200 -> {
				logger.info("Received response with status code {}", response.code)
				val responseBody = try {
					 objectMapper.readValue(response.body!!.string(), Message::class.java)
				} catch (e: IOException) {
					val errorMessage = "Failed to deserialize response body " + e.message
					logger.error(errorMessage)
					throw ClientException(errorMessage, e)
				}
				responseBody
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}
}
