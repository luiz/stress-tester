package net.vidageek.benchmark.time;

public interface TimeUnit {

	public boolean smallerThan(TimeUnit other);

	public boolean equalsTo(TimeUnit other);

	public long value();

	public Miliseconds asMiliseconds();

}