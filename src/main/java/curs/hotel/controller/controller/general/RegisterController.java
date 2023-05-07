package curs.hotel.controller.controller.general;

import curs.hotel.service.util.directions.Pathes;
import curs.hotel.service.view.anonymous.AuthenticationService;
import curs.hotel.service.view.anonymous.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class RegisterController {

    private RegisterService service;
    private final AuthenticationService authenticationService;

    @Autowired
    public RegisterController(RegisterService service, AuthenticationService authenticationService) {
        this.service = service;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/register")
    public String registerGet(Authentication authentication) {
        String redirect = authenticationService.redirectIfAuthenticated(authentication);
        return (redirect != null) ? redirect : Pathes.REGISTER.getCropPagePath();
    }

    @PostMapping("/register")
    public String registerPost(@RequestParam(name = "name") String name,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name="password") String pass) {
        service.register(name, email, pass);
        return "redirect:" + Pathes.LOGIN.getUrl();
    }
}
