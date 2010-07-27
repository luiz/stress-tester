package net.vidageek.benchmark;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.vidageek.benchmark.statistics.StatisticsCollector;
import net.vidageek.benchmark.statistics.TestStatistics;
import net.vidageek.benchmark.statistics.TestStatisticsCollector;
import net.vidageek.benchmark.time.Seconds;
import net.vidageek.benchmark.time.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class StressTester {
	public static void main(String[] args) throws URISyntaxException {
		URI serverURI;
		if (args.length > 0) {
			serverURI = new URI(args[0]);
		} else {
			serverURI = new URI("http://localhost:8080/");
		}
		Seconds timeToTest = new Seconds(30);
		TestStatistics statistics = new StressTester().test(serverURI, timeToTest, new SimultaneousRequests(10));
		System.out.println(statistics);
	}

	private TestStatistics test(URI uri, TimeUnit timeToTest, SimultaneousRequests simultaneousRequests) {
		ExecutorService threadPool = Executors.newFixedThreadPool(simultaneousRequests.value());
		StatisticsCollector statisticsCollector = new TestStatisticsCollector();
		HttpClient httpClient = new DefaultHttpClient();
		Timer timer = createProgressIndicatorTimer();
		long startTimeMillis = System.currentTimeMillis();
		while (Seconds.since(startTimeMillis).smallerThan(timeToTest)) {
			StressTesterWorker worker = new StressTesterWorker(uri, httpClient, statisticsCollector);
			threadPool.execute(worker);
		}
		stopProgressIndicator(timer);
		threadPool.shutdownNow();
		return new TestStatistics(statisticsCollector);
	}

	private void stopProgressIndicator(Timer timer) {
		timer.cancel();
		System.out.println();
	}

	private Timer createProgressIndicatorTimer() {
		Timer timer = new Timer();
		TimerTask progressIndicatorTask = new TimerTask() {
			@Override
			public void run() {
				System.out.print(".");
			}
		};
		timer.scheduleAtFixedRate(progressIndicatorTask,
				new Seconds(0).asMiliseconds().value(),
				new Seconds(1).asMiliseconds().value());
		return timer;
	}
}
