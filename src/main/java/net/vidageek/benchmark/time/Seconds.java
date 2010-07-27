package net.vidageek.benchmark.time;

public final class Seconds implements TimeUnit {
	private final long value;

	public Seconds(long value) {
		if (value < 0) {
			throw new IllegalArgumentException("Cannot create Seconds with negative value: " + value);
		}
		this.value = value;
	}

	public long value() {
		return this.value;
	}

	public static Seconds since(long startTimeMillis) {
		long currentTimeMillis = System.currentTimeMillis();
		long secondsElapsed = (currentTimeMillis - startTimeMillis) / 1000;
		return new Seconds(secondsElapsed);
	}

	public boolean smallerThan(TimeUnit other) {
		return this.asMiliseconds().value() < other.asMiliseconds().value();
	}

	public boolean equalsTo(TimeUnit other) {
		return this.asMiliseconds().value() == other.asMiliseconds().value();
	}

	public Miliseconds asMiliseconds() {
		return new Miliseconds(this.value * 1000);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (value ^ (value >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Seconds other = (Seconds) obj;
		return this.equalsTo(other);
	}

	@Override
	public String toString() {
		return Long.toString(value) + "s";
	}
}