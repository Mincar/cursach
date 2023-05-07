package curs.hotel.service.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import curs.hotel.model.config.WebSecurityConfig;

@ConditionalOnBean(WebSecurityConfig.class)
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String name, String password );
}
