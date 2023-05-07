package curs.hotel.service.view.anonymous;

import curs.hotel.service.util.directions.Pages;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import curs.hotel.dao.UserRepository;
import curs.hotel.model.enums.Role;
import curs.hotel.model.pojo.User;

import java.util.Locale;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public RegisterServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public String register(@NonNull String name, @NonNull String email, @NonNull String pass) {

        User newUser = null;
        try {
            newUser = new User.Builder()
                    .withName(name)
                    .withRole(Role.USER)
                    .withPasswordEncoded(encoder.encode(pass))
                    .withEmail(email)
                    .build();
            userRepository.save(newUser);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return newUser == null ? Pages.REGISTER_PAGE.getCropPath() : Pages.LOGIN_PAGE.getCropPath();
    }
}
