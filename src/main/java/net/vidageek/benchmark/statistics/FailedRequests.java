package net.vidageek.benchmark.statistics;

public class FailedRequests {
	private final long value;

	public FailedRequests(long value) {
		this.value = value;
	}

	public long value() {
		return this.value;
	}

	@Override
	public String toString() {
		return "Failed requests: " + value;
	}
}
