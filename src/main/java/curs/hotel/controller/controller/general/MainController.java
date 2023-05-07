package curs.hotel.controller.controller.general;

import curs.hotel.service.util.directions.Pages;
import curs.hotel.service.view.anonymous.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {
    private final AuthenticationService authenticationService;

    @Autowired
    public MainController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/")
    public String index(Authentication authentication) {
        String redirect = authenticationService.redirectIfAuthenticated(authentication);
        return (redirect != null) ? redirect : Pages.INDEX_PAGE.getCropPath();
    }
}
