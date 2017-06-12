package es.sccode.springdemo.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("requestTime")
public class RequestTime extends HandlerInterceptorAdapter {

	private final static Log LOG = LogFactory.getLog(RequestTime.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long starTime = (long)request.getAttribute("startTime");
		LOG.info("Peticion ->"+request.getRequestURL().toString() + "  Tiempo -> "+ (System.currentTimeMillis()-starTime) +" ms");
	}

	

}
