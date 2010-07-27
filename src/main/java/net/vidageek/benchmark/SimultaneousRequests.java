package net.vidageek.benchmark;

public final class SimultaneousRequests {
	private final int value;

	public SimultaneousRequests(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}
}
