package net.vidageek.benchmark.statistics;

public final class CompletedRequests {
	private final long value;

	public CompletedRequests(long completedRequests) {
		this.value = completedRequests;
	}

	public long value() {
		return this.value;
	}

	@Override
	public String toString() {
		return "Completed requests: " + value;
	}
}
