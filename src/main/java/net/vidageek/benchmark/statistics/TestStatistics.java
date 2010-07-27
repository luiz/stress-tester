package net.vidageek.benchmark.statistics;


public final class TestStatistics {
	private final CompletedRequests completedRequests;
	private final FailedRequests failedRequests;
	private ExceptionsStatistics exceptionsStatistics;

	public TestStatistics(StatisticsCollector statisticsCollector) {
		this.completedRequests = statisticsCollector.completedRequests();
		this.failedRequests = statisticsCollector.failedRequests();
		this.exceptionsStatistics = statisticsCollector.exceptionsStatistics();
	}

	@Override
	public String toString() {
		return completedRequests.toString() + "\n" + failedRequests.toString() + "\n" + exceptionsStatistics.toString();
	}
}
