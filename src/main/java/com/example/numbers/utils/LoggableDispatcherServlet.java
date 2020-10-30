package com.example.numbers.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;

@Slf4j
public class LoggableDispatcherServlet extends DispatcherServlet {

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }
        HandlerExecutionChain handler = getHandler(request);

        try {
            super.doDispatch(request, response);
        } finally {
            log(request, response, handler);
            updateResponse(response);
        }
    }

    private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache, HandlerExecutionChain handler) throws IOException {

        String builder = "Status : " + responseToCache.getStatus() +
                "\n Method :: " + requestToCache.getMethod() +
                "\n Parameters :: " + getParams(requestToCache.getParameterMap(), requestToCache.getParameterNames()) +
                "\n Request Url :: " + requestToCache.getRequestURI() +
                "\n Remote address :: " + requestToCache.getRemoteAddr() +
                "\n Handler :: " + handler.toString() +
                "\n Response :: " + getResponsePayload(responseToCache) +
                "\n Body :: " + getRequestPayload(requestToCache);
        log.info("\n----------------------------------------------------- \n {} \n-----------------------------------------------------",
                builder);
    }

    private String getRequestPayload(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    log.error("Exception occurred converting payload {}", ex.getMessage());
                    payload = "[unknown]";
                }

                return payload;
            }
        }
        return "[unknown]";
    }

    private String getParams(Map<String, String[]> paramMap, Enumeration<String> params) {
        StringBuilder builder = new StringBuilder();
        String param = null;
        for (int count = 0; count < paramMap.size(); ++count) {
            param = params.nextElement();
            builder.append(param).append(" :: ").append(paramMap.get(param)[0]).append(" ");
        }

        return builder.toString();
    }

    private String getResponsePayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, 5120);
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                }
            }
        }
        return "[unknown]";
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        responseWrapper.copyBodyToResponse();
    }
}
