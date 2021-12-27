package test_client.v2.models

import java.time.*
import java.util.*
import java.math.BigDecimal
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.annotation.*

data class Message(
	@JsonProperty(value = "bool_field", required = true)
	val boolField: Boolean,
	@JsonProperty(value = "string_field", required = true)
	val stringField: String,
)
