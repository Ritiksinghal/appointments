package com.eastvantage.appointment.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class AuditLogging extends OncePerRequestFilter{

	private static final Logger LOGGER = LoggerFactory.getLogger(AuditLogging.class);
	private String correlationId = "correlationId";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

		filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);

		String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
		String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

		LOGGER.info("[{}]: Method: {}; Reuest URL: {}; Request Body: {}; Response Code: {}; Response Body: {};", 
				response.getHeader(correlationId), request.getMethod(), request.getRequestURL(), requestBody, response.getStatus(), responseBody);
		contentCachingResponseWrapper.copyBodyToResponse();

		if(response.getStatus() != 200) {
			LOGGER.error("[{}]: Method: {}; Reuest URL: {}; Request Body: {}; Response Code: {}; Response Body: {};", 
					response.getHeader(correlationId), request.getMethod(), request.getRequestURL(), requestBody, response.getStatus(), responseBody);
			contentCachingResponseWrapper.copyBodyToResponse();
		}
	}

	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
		try {
			return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("", e);
		}
		return "";
	}

	@Override
	protected boolean isAsyncDispatch(final HttpServletRequest request) {
		return false;
	}

	@Override
	protected boolean shouldNotFilterErrorDispatch() {
		return false;
	}
}
