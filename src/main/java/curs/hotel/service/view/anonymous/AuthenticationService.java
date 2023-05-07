package curs.hotel.service.view.anonymous;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    String redirectIfAuthenticated(Authentication authentication);
}
