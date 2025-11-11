package org.example.trading.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.List;

@Component
public class TradingAuthenticationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(TradingAuthenticationFilter.class.getName());

    private static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/api/public/", "/swagger-ui/", "/v3/api-docs", "/favicon.ico"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String authToken = httpRequest.getHeader("Authorization");

        if (isPublicEndpoint(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        if (authToken == null || !isValidToken(authToken)) {
            logger.warning("Unauthorized access attempt to: " + requestURI);
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write("{\"error\":\"Authentication required\"}");
            return;
        }

        logger.info("Authenticated request to: " + requestURI);
        chain.doFilter(request, response);  // Lanjutkan jika valid
    }

    // Memeriksa apakah endpoint termasuk yang public
    private boolean isPublicEndpoint(String requestURI) {
        return PUBLIC_ENDPOINTS.stream().anyMatch(requestURI::startsWith);
    }

    private boolean isValidToken(String token) {
        return token != null && token.startsWith("Bearer ");
    }
}
