import org.junit.jupiter.api.Test;
import test_client.v2.clients.echo.*;
import test_client.v2.models.*;

public class ClientTestV2 {
	public static final String BASE_URL = "http://localhost:8081";

	@Test
	public void echoBody_responseIsEqualToRequest() {
		EchoClient client = new EchoClient(BASE_URL);

		Message request = new Message(true, "the string");
		Message response = client.echoBody(request);

		assertEquals(request, response);
	}

	@Test
	public void echoBody_doesntThrowException() {
		EchoClient client = new EchoClient(BASE_URL);

		assertDoesNotThrow(() -> client.echoBody(new Message(true, "the string")));
	}
}
