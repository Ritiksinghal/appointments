package com.eastvantage.appointment.trace;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

public class MDCInterceptor implements HandlerInterceptor {

	String correlationId = "correlationId";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		MDC.put (correlationId, getCorrelationId()); 
		return true;
	}

	@Override
	public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		response.addHeader(correlationId, MDC.get(correlationId));
		MDC.remove (correlationId);
	}

	private String getCorrelationId() {
		return UUID.randomUUID().toString();
	}
}
