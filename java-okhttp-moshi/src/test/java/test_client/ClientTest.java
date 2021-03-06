package test_client;

import org.junit.jupiter.api.Test;
import test_client.clients.check.*;
import test_client.clients.echo.*;
import test_client.models.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
	public static final String BASE_URL = "http://localhost:8081";

	@Test
	public void echoBodyString_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		String request = "TWFueSBoYW5kcyBtYWtlIGxpZ2h0IHdvcmsu";
		String response = client.echoBodyString(request);

		assertEquals(request, response);
	}

	@Test
	public void echoBodyString_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		assertDoesNotThrow(() -> client.echoBodyString("TWFueSBoYW5kcyBtYWtlIGxpZ2h0IHdvcmsu"));
	}

	@Test
	public void echoBodyModel_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		var request = new Message(123, "the string");
		var response = client.echoBodyModel(request);

		assertEquals(request, response);
	}

	@Test
	public void echoBodyModel_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		assertDoesNotThrow(() -> client.echoBodyModel(new Message(123, "the string")));
	}

	@Test
	public void echoBodyArray_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		var request = Arrays.asList("the str1", "the str2");
		var response = client.echoBodyArray(request);

		assertEquals(request, response);
	}

	@Test
	public void echoBodyArray_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		assertDoesNotThrow(() -> client.echoBodyArray(Arrays.asList("the str1", "the str2")));
	}

	@Test
	public void echoBodyMap_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		var request = new HashMap<String, String>() {{
			put("string_field", "the value");
			put("string_field_2", "the value_2");
		}};
		var response = client.echoBodyMap(request);

		assertEquals(request, response);
	}

	@Test
	public void echoBodyMap_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		assertDoesNotThrow(() -> client.echoBodyMap(new HashMap<>() {{
			put("string_field", "the value");
			put("string_field_2", "the value_2");
		}}));
	}

	@Test
	public void echoQuery_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		int intQuery = 123;
		var longQuery = 12345;
		float floatQuery = 1.23f;
		double doubleQuery = 12.345;
		BigDecimal decimalQuery = new BigDecimal("12345");
		boolean boolQuery = true;
		String stringQuery = "the value";
		String stringOptQuery = "the value";
		String stringDefaultedQuery = "value";
		List<String> stringArrayQuery = Arrays.asList("the str1", "the str2");
		UUID uuidQuery = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
		LocalDate dateQuery = LocalDate.parse("2020-01-01");
		List<LocalDate> dateArrayQuery = Arrays.asList(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"));
		LocalDateTime datetimeQuery = LocalDateTime.parse("2019-11-30T17:45:55");
		Choice enumQuery = Choice.SECOND_CHOICE;

		var request = new Parameters(intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery);
		var response = client.echoQuery(intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery);

		assertEquals(request, response);
	}

	@Test
	public void echoQuery_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		int intQuery = 123;
		var longQuery = 12345;
		float floatQuery = 1.23f;
		double doubleQuery = 12.345;
		BigDecimal decimalQuery = new BigDecimal("12345");
		boolean boolQuery = true;
		String stringQuery = "the value";
		String stringOptQuery = "the value";
		String stringDefaultedQuery = "value";
		List<String> stringArrayQuery = Arrays.asList("the str1", "the str2");
		UUID uuidQuery = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
		LocalDate dateQuery = LocalDate.parse("2020-01-01");
		List<LocalDate> dateArrayQuery = Arrays.asList(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"));
		LocalDateTime datetimeQuery = LocalDateTime.parse("2019-11-30T17:45:55");
		Choice enumQuery = Choice.SECOND_CHOICE;

		assertDoesNotThrow(() -> client.echoQuery(intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery));
	}


	@Test
	public void echoHeader_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		int intHeader = 123;
		var longHeader = 12345;
		float floatHeader = 1.23f;
		double doubleHeader = 12.345;
		BigDecimal decimalHeader = new BigDecimal("12345");
		boolean boolHeader = true;
		String stringHeader = "the value";
		String stringOptHeader = "the value";
		String stringDefaultedHeader = "value";
		List<String> stringArrayHeader = Arrays.asList("the str1", "the str2");
		UUID uuidHeader = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
		LocalDate dateHeader = LocalDate.parse("2020-01-01");
		List<LocalDate> dateArrayHeader = Arrays.asList(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"));
		LocalDateTime datetimeHeader = LocalDateTime.parse("2019-11-30T17:45:55");
		Choice enumHeader = Choice.SECOND_CHOICE;

		var request = new Parameters(intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader);
		var response = client.echoHeader(intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader);

		assertEquals(request, response);
	}

	@Test
	public void echoHeader_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		int intHeader = 123;
		var longHeader = 12345;
		float floatHeader = 1.23f;
		double doubleHeader = 12.345;
		BigDecimal decimalHeader = new BigDecimal("12345");
		boolean boolHeader = true;
		String stringHeader = "the value";
		String stringOptHeader = "the value";
		String stringDefaultedHeader = "value";
		List<String> stringArrayHeader = Arrays.asList("the str1", "the str2");
		UUID uuidHeader = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
		LocalDate dateHeader = LocalDate.parse("2020-01-01");
		List<LocalDate> dateArrayHeader = Arrays.asList(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"));
		LocalDateTime datetimeHeader = LocalDateTime.parse("2019-11-30T17:45:55");
		Choice enumHeader = Choice.SECOND_CHOICE;

		assertDoesNotThrow(() -> client.echoHeader(intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader));
	}

	@Test
	public void echoUrlParams_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		int intUrl = 123;
		var longUrl = 12345;
		float floatUrl = 1.23f;
		double doubleUrl = 12.345;
		BigDecimal decimalUrl = new BigDecimal("12345");
		boolean boolUrl = true;
		String stringUrl = "the value";
		UUID uuidUrl = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
		LocalDate dateUrl = LocalDate.parse("2020-01-01");
		LocalDateTime datetimeUrl = LocalDateTime.parse("2019-11-30T17:45:55");
		Choice enumUrl = Choice.SECOND_CHOICE;

		var request = new UrlParameters(intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl);
		var response = client.echoUrlParams(intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl);

		assertEquals(request, response);
	}

	@Test
	public void echoUrlParams_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		int intUrl = 123;
		var longUrl = 12345;
		float floatUrl = 1.23f;
		double doubleUrl = 12.345;
		BigDecimal decimalUrl = new BigDecimal("12345");
		boolean boolUrl = true;
		String stringUrl = "the value";
		UUID uuidUrl = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
		LocalDate dateUrl = LocalDate.parse("2020-01-01");
		LocalDateTime datetimeUrl = LocalDateTime.parse("2019-11-30T17:45:55");
		Choice enumUrl = Choice.SECOND_CHOICE;

		assertDoesNotThrow(() -> client.echoUrlParams(intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl));
	}

	@Test
	public void echoEverything_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		Message body = new Message(123, "the string");
		float floatQuery = 1.23f;
		boolean boolQuery = true;
		UUID uuidHeader = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
		LocalDateTime datetimeHeader = LocalDateTime.parse("2019-11-30T17:45:55");
		LocalDate dateUrl = LocalDate.parse("2020-01-01");
		BigDecimal decimalUrl = new BigDecimal("12345");

		var request = new EchoEverythingResponse.Ok(new Everything(body, floatQuery, boolQuery, uuidHeader, datetimeHeader, dateUrl, decimalUrl));
		var response = client.echoEverything(body, floatQuery, boolQuery, uuidHeader, datetimeHeader, dateUrl, decimalUrl);

		assertThat(request).usingRecursiveComparison().isEqualTo(response);
	}

	@Test
	public void echoEverything_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		Message body = new Message(123, "the string");
		float floatQuery = 1.23f;
		boolean boolQuery = true;
		UUID uuidHeader = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
		LocalDateTime datetimeHeader = LocalDateTime.parse("2019-11-30T17:45:55");
		LocalDate dateUrl = LocalDate.parse("2020-01-01");
		BigDecimal decimalUrl = new BigDecimal("12345");

		assertDoesNotThrow(() -> client.echoEverything(body, floatQuery, boolQuery, uuidHeader, datetimeHeader, dateUrl, decimalUrl));
	}

	@Test
	public void checkEmpty_doesntThrowException() {
		CheckClient client = new CheckClient(BASE_URL);

		assertDoesNotThrow(client::checkEmpty);
	}

	@Test
	public void checkEmptyResponse_doesntThrowException() {
		CheckClient client = new CheckClient(BASE_URL);

		assertDoesNotThrow(() -> client.checkEmptyResponse(new Message(123, "the string")));
	}

	@Test
	public void checkForbidden_doesntThrowException() {
		CheckClient client = new CheckClient(BASE_URL);

		assertDoesNotThrow(client::checkForbidden);
	}
}