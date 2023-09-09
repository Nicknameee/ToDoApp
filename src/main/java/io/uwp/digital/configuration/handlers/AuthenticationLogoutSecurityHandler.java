package io.uwp.digital.configuration.handlers;

import io.uwp.digital.utility.AuthorizationTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationLogoutSecurityHandler implements LogoutSuccessHandler {
    private final UserDetailsService userDetailsService;
    private final AuthorizationTokenUtil authorizationTokenUtil;

    @Override
    public void onLogoutSuccess(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                Authentication authentication) {
        String authorizationHeaderValue = request.getHeader("Authorization");

        if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer ")) {
            String authorizationToken = authorizationHeaderValue.substring(7);
            String username = authorizationTokenUtil.getUsernameFromToken(authorizationToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (!authorizationToken.isEmpty()
                    && authorizationTokenUtil.validateToken(authorizationToken, userDetails, request)) {
                authorizationTokenUtil.blacklistToken(authorizationToken);
            }
        }
    }
}
