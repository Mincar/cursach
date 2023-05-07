package curs.hotel.service.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import curs.hotel.model.config.WebSecurityConfig;

@ConditionalOnBean(WebSecurityConfig.class)
@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public String findLoggedInUsername() {
        return null;
    }

    @Override
    public void autoLogin(String name, String password) {

    }
}
