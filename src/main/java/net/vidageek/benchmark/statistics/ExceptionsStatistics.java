package net.vidageek.benchmark.statistics;

import java.util.Map;

public class ExceptionsStatistics {

	private final Map<Class<? extends Exception>, Long> exceptions;

	public ExceptionsStatistics(Map<Class<? extends Exception>, Long> exceptions) {
		this.exceptions = exceptions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Exceptions:\n");
		for (Class<? extends Exception> exceptionType : exceptions.keySet()) {
			builder.append('\t');
			builder.append(exceptionType.getName());
			builder.append(": ");
			builder.append(exceptions.get(exceptionType));
			builder.append('\n');
		}
		return builder.toString();
	}

}
