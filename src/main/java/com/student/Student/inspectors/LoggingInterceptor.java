package com.student.Student.inspectors;

import com.student.Student.utils.CachedBodyHttpServletRequest;
import com.student.Student.utils.CachedBodyHttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    // Runs BEFORE the request reaches the controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Wrap the request to cache the body
        CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(request);

        System.out.println("=== Incoming Request ===");
        System.out.println("Method: " + wrappedRequest.getMethod());
        System.out.println("URI: " + wrappedRequest.getRequestURI());
        System.out.println("Request Data: " + wrappedRequest.getRequestBody());
        log.info("Request logger Data: :{}",wrappedRequest.getRequestBody());

        // Replacing the original request with the cached one
        request = wrappedRequest;

        return true; // Continue to the controller
    }

    // Runs AFTER the controller but BEFORE sending the response
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        // Wrap the response to cache the body
        if (!(response instanceof CachedBodyHttpServletResponse)) {
            response = new CachedBodyHttpServletResponse(response);
        }
    }

    // Runs AFTER the response has been sent to the client
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Ensure the response is an instance of CachedBodyHttpServletResponse
        if (response instanceof CachedBodyHttpServletResponse) {
            CachedBodyHttpServletResponse wrappedResponse = (CachedBodyHttpServletResponse) response;

            System.out.println("=== Outgoing Response ===");
            System.out.println("Status Code: " + response.getStatus());
            System.out.println("Response Data: " + wrappedResponse.getResponseBody());
        }
    }
}
