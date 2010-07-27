package net.vidageek.benchmark.statistics;


public interface StatisticsCollector {

	public void incrementCompletedRequests();

	public CompletedRequests completedRequests();

	public void incrementFailedRequests();

	public FailedRequests failedRequests();

	public void incrementExceptionCount(Class<? extends Exception> exception);

	public ExceptionsStatistics exceptionsStatistics();

}