package test_client;

import org.junit.jupiter.api.*;
import test_client.v2.clients.echo.*;
import test_client.v2.models.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTestV2 {
	public static final String BASE_URL = "http://localhost:8081";

	@Test
	public void echoBody_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		Message request = new Message(true, "the string");
		Message response = client.echoBodyModel(request);

		assertEquals(request, response);
	}

	@Test
	public void echoBody_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		assertDoesNotThrow(() -> client.echoBodyModel(new Message(true, "the string")));
	}
}

