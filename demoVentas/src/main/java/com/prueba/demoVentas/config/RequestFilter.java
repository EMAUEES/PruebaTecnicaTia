package com.prueba.demoVentas.config;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

@Component
public class RequestFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			ThreadContext.put("mdcData", requestId());
			chain.doFilter(request, response);
		} finally {
			ThreadContext.clearMap();
		}
	}

	private String requestId() {
		return UUID.randomUUID().toString();
	}
}
