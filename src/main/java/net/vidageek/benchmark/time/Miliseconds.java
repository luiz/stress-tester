package net.vidageek.benchmark.time;

public class Miliseconds implements TimeUnit {

	private final long value;

	public Miliseconds(long value) {
		if (value < 0) {
			throw new IllegalArgumentException("Cannot create miliseconds with negative value: " + value);
		}
		this.value = value;
	}

	public boolean smallerThan(TimeUnit other) {
		return this.value() < other.asMiliseconds().value();
	}

	public boolean equalsTo(TimeUnit other) {
		return other.asMiliseconds().value() == this.value();
	}

	public long value() {
		return this.value;
	}

	public Miliseconds asMiliseconds() {
		return this;
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
		if (!TimeUnit.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		TimeUnit other = (TimeUnit) obj;
		return this.equalsTo(other);
	}

	@Override
	public String toString() {
		return Long.toString(this.value) + "ms";
	}
}
