package io.uwp.digital.controller;

import io.uwp.digital.exceptions.AuthenticationException;
import io.uwp.digital.utility.AuthenticationProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationProcessingService authenticationProcessingService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        HttpServletRequest request) throws SecurityException, AuthenticationException {
        return ResponseEntity.ok(authenticationProcessingService.authenticate(username, password, request));
    }
}
