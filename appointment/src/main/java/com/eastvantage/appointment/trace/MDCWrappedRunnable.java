package com.eastvantage.appointment.trace;

import java.util.Map;

import org.slf4j.MDC;

public class MDCWrappedRunnable implements Runnable{

	private final Runnable runnable;
	private final Map<String, String> context;

	public MDCWrappedRunnable(Runnable runnable) {
		this.runnable = runnable;
		context = MDC.getCopyOfContextMap();
	}

	@Override
	public void run() {
		try {
			MDC.setContextMap(this.context);
			runnable.run();
		} finally {
			MDC.clear();
		}
	}
}
