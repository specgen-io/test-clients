package test_client;

import org.junit.jupiter.api.Test;
import test_client.clients.check.*;
import test_client.clients.echo.*;
import test_client.models.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.UUID;

public class ClientTest {
	public static final String BASE_URL = "http://localhost:8081";

	@Test
	public void echoBody_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		Message request = new Message(123, "the string");
		Message response = client.echoBody(request);

		assertEquals(request, response);
	}

	@Test
	public void echoBody_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		assertDoesNotThrow(() -> client.echoBody(new Message(123, "the string")));
	}

	@Test
	public void echoQuery_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		Message request = new Message(123, "the string");
		Message response = client.echoQuery(123, "the string");

		assertEquals(request, response);
	}

	@Test
	public void echoQuery_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		assertDoesNotThrow(() -> client.echoQuery(123, "the string"));
	}

	@Test
	public void echoHeader_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		Message request = new Message(123, "the string");
		Message response = client.echoHeader(123, "the string");

		assertEquals(request, response);
	}

	@Test
	public void echoHeader_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		assertDoesNotThrow(() -> client.echoHeader(123, "the string"));
	}

	@Test
	public void echoUrlParams_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		Message request = new Message(123, "the string");
		Message response = client.echoUrlParams(123, "the string");

		assertEquals(request, response);
	}

	@Test
	public void echoUrlParams_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		assertDoesNotThrow(() -> client.echoUrlParams(123, "the string"));
	}

	@Test
	public void checkEmpty_doesntThrowException() {
		CheckClient client = new CheckClient(BASE_URL);

		assertDoesNotThrow(client::checkEmpty);
	}

	@Test
	public void checkQuery_doesntThrowException() {
		CheckClient client = new CheckClient(BASE_URL);

		String pString = "the string 1";
		String pStringOpt = "the string 2";
		String[] pStringArray = new String[]{"str1", "str2"};
		LocalDate pDate = LocalDate.parse("2020-01-01");
		LocalDate[] pDateArray = new LocalDate[]{LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02")};
		LocalDateTime pDatetime = LocalDateTime.parse("2019-11-30T17:45:55");
		int pInt = 123;
		long pLong = 1234;
		BigDecimal pDecimal = new BigDecimal("1.23");
		Choice pEnum = Choice.SECOND_CHOICE;
		String pStringDefaulted = "value";

		assertDoesNotThrow(() -> client.checkQuery(pString, pStringOpt, pStringArray, pDate, pDateArray, pDatetime, pInt, pLong, pDecimal, pEnum, pStringDefaulted));
	}

	@Test
	public void checkUrlParams_doesntThrowException() {
		CheckClient client = new CheckClient(BASE_URL);

		long intUrl = 1234;
		String stringUrl = "the string 1";
		float floatUrl = 1.23f;
		boolean boolUrl = true;
		UUID uuidUrl = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
		BigDecimal decimalUrl = new BigDecimal("1.23");
		LocalDate dateUrl = LocalDate.parse("2020-01-01");
		Choice enumUrl = Choice.SECOND_CHOICE;

		assertDoesNotThrow(() -> client.checkUrlParams(intUrl, stringUrl, floatUrl, boolUrl, uuidUrl, decimalUrl, dateUrl, enumUrl));
	}

	@Test
	public void checkForbidden_doesntThrowException() {
		CheckClient client = new CheckClient(BASE_URL);

		assertDoesNotThrow(client::checkForbidden);
	}
}
