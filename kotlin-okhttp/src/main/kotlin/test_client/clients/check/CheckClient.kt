package test_client.clients.check

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
import test_client.models.*
import test_client.models.setupObjectMapper

class CheckClient(
	private val baseUrl: String,
	private val client: OkHttpClient = OkHttpClient(),
	private val objectMapper: ObjectMapper = setupObjectMapper(jacksonObjectMapper())
) {
	private val logger: Logger = LoggerFactory.getLogger(CheckClient::class.java)

	fun checkEmpty() {
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("check/empty")

		val request = RequestBuilder("GET", url.build(), null)

		logger.info("Sending request, operationId: check.check_empty, method: GET, url: /check/empty")
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
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun checkForbidden(): CheckForbiddenResponse {
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("check/forbidden")

		val request = RequestBuilder("GET", url.build(), null)

		logger.info("Sending request, operationId: check.check_forbidden, method: GET, url: /check/forbidden")
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
				CheckForbiddenResponse.Ok(responseBody)
			}
			403 -> {
				logger.info("Received response with status code {}", response.code)
				CheckForbiddenResponse.Forbidden()
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun sameOperationName(): SameOperationNameResponse {
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("check/same_operation_name")

		val request = RequestBuilder("GET", url.build(), null)

		logger.info("Sending request, operationId: check.same_operation_name, method: GET, url: /check/same_operation_name")
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
				SameOperationNameResponse.Ok()
			}
			403 -> {
				logger.info("Received response with status code {}", response.code)
				SameOperationNameResponse.Forbidden()
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}
}
