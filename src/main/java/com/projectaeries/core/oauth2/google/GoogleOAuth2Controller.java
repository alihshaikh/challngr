package com.projectaeries.core.oauth2.google;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/google/user")
public class GoogleOAuth2Controller {

    @GetMapping
    public Object user(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> result = new HashMap<>();
        System.out.println(principal);
        result.put("name", principal.getAttribute("name"));
        result.put("email", principal.getAttribute("email"));
        result.put("token", principal.getAttribute("token"));
        return principal;
    }
}
