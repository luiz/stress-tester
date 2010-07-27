package net.vidageek.benchmark;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import net.vidageek.benchmark.StressTesterWorker;
import net.vidageek.benchmark.WrongResponseException;
import net.vidageek.benchmark.statistics.StatisticsCollector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StressTesterWorkerTest {

	private @Mock HttpClient httpClient;
	private @Mock HttpResponse response;
	private @Mock StatusLine statusLine;
	private @Mock StatisticsCollector collector;
	private @Mock HttpEntity entity;
	private StressTesterWorker worker;

	@Before
	public void setUp() throws Exception {
		worker = new StressTesterWorker(new URI("http://test.com"), httpClient, collector);

		when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(response);
		when(response.getStatusLine()).thenReturn(statusLine);
		when(response.getEntity()).thenReturn(entity);
	}

	@Test
	public void countsSuccessfulRequestIfResponseCodeIs200AndResponseBodyIsHelloWorld() throws Exception {
		when(statusLine.getStatusCode()).thenReturn(HttpServletResponse.SC_OK);
		when(entity.getContent()).thenReturn(createResponseBody("Hello world!"));

		worker.run();

		verify(collector).incrementCompletedRequests();
	}

	@Test
	public void countsFailedRequestIfResponseBodyIsNotHelloWorld() throws Exception {
		when(statusLine.getStatusCode()).thenReturn(HttpServletResponse.SC_OK);
		when(entity.getContent()).thenReturn(createResponseBody("Bye world!"));

		worker.run();

		verify(collector).incrementFailedRequests();
		verify(collector).incrementExceptionCount(WrongResponseException.class);
	}

	@Test
	public void countsFailedRequestIfResponseCodeIsNot200() throws Exception {
		when(statusLine.getStatusCode()).thenReturn(HttpServletResponse.SC_BAD_REQUEST);
		when(entity.getContent()).thenReturn(createResponseBody("Hello world!"));

		worker.run();

		verify(collector).incrementFailedRequests();
		verify(collector).incrementExceptionCount(WrongResponseException.class);
	}

	@Test
	public void countsFailedRequestIfAnExceptionIsThrown() throws Exception {
		when(statusLine.getStatusCode()).thenReturn(HttpServletResponse.SC_OK);
		when(entity.getContent()).thenThrow(new IOException());

		worker.run();

		verify(collector).incrementFailedRequests();
		verify(collector).incrementExceptionCount(IOException.class);
	}

	private InputStream createResponseBody(String responseBody) {
		InputStream stream = new ByteArrayInputStream(responseBody.getBytes());
		return stream;
	}
}
