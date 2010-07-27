package net.vidageek.benchmark.statistics;

import java.util.concurrent.ConcurrentHashMap;


public class TestStatisticsCollector implements StatisticsCollector {
	private long completedRequests = 0;
	private long failedRequests = 0;
	private ConcurrentHashMap<Class<? extends Exception>, Long> exceptions = new ConcurrentHashMap<Class<? extends Exception>, Long>();

	private Object completedRequestsLock = new Object();
	private Object failedRequestsLock = new Object();

	public void incrementCompletedRequests() {
		synchronized (completedRequestsLock) {
			completedRequests++;
		}
	}

	public CompletedRequests completedRequests() {
		synchronized (completedRequestsLock) {
			return new CompletedRequests(completedRequests);
		}
	}

	public void incrementFailedRequests() {
		synchronized (failedRequestsLock) {
			failedRequests++;
		}
	}

	public FailedRequests failedRequests() {
		synchronized (failedRequestsLock) {
			return new FailedRequests(failedRequests);
		}
	}

	public void incrementExceptionCount(Class<? extends Exception> exception) {
		Long count = exceptions.get(exception);
		if (count == null) {
			count = 1L;
		} else {
			count += 1L;
		}
		exceptions.put(exception, count);
	}

	public ExceptionsStatistics exceptionsStatistics() {
		return new ExceptionsStatistics(exceptions);
	}
}
