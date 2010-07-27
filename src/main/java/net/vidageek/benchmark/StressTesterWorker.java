package net.vidageek.benchmark;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import net.vidageek.benchmark.statistics.StatisticsCollector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

public class StressTesterWorker implements Runnable {
	private final URI uri;
	private final HttpClient client;
	private final StatisticsCollector statisticsCollector;

	public StressTesterWorker(URI uri, HttpClient client, StatisticsCollector statisticsCollector) {
		this.uri = uri;
		this.client = client;
		this.statisticsCollector = statisticsCollector;
	}

	public void run() {
		HttpUriRequest request = new HttpGet(uri);
		try {
			HttpResponse response = client.execute(request);
			if (finishedSuccessfully(response)) {
				statisticsCollector.incrementCompletedRequests();
			} else {
				statisticsCollector.incrementFailedRequests();
				statisticsCollector.incrementExceptionCount(WrongResponseException.class);
			}
		} catch (Exception e) {
			statisticsCollector.incrementFailedRequests();
			statisticsCollector.incrementExceptionCount(e.getClass());
		} finally {
			request.abort();
		}
	}

	private boolean finishedSuccessfully(HttpResponse response) throws IllegalStateException, IOException {
		if (response.getStatusLine().getStatusCode() != HttpServletResponse.SC_OK) {
			return false;
		}
		return readResponseBody(response).equals("Hello world!");
	}

	private String readResponseBody(HttpResponse response) throws IllegalStateException, IOException {
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			return "";
		}
		Reader reader = new InputStreamReader(entity.getContent());
		char[] buffer = new char[0x10000];
		reader.read(buffer);
		return new String(buffer).trim();
	}
}