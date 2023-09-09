package io.uwp.digital.utility;

import io.uwp.digital.exceptions.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationProcessingService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final AuthorizationTokenUtil authorizationTokenUtil;

    public Map<String, Object> authenticate(String username,
                                            String password,
                                            HttpServletRequest request) throws AuthenticationException {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token;

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            token = authorizationTokenUtil.generateToken(userDetails, request);
        } else {
            throw new AuthenticationException("Could not authenticate following credentials");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("expires_at", authorizationTokenUtil.getExpirationDateFromToken(token).getTime());

        return response;
    }
}
