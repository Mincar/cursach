package curs.hotel.service.view.admin;

import curs.hotel.service.util.directions.Pages;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import curs.hotel.dao.UserRepository;
import curs.hotel.model.enums.Role;
import curs.hotel.model.pojo.User;

import java.util.Optional;

import static curs.hotel.service.util.StringConverter.parseLong;

@Slf4j
@Service
public class UserTableServiceImpl implements UsersTableService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserTableServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void newUser(@NonNull String name, @NonNull String email, @NonNull String pass, @NonNull Role role
                       ) {
        try {
            userRepository.save( new User.Builder()
                    .withName(name)
                    .withRole(role)
                    .withPasswordEncoded(encoder.encode(pass))
                    .withEmail(email)
                    .build());
        } catch (Exception e) {
            log.error("Impossible to create User at admin/users: " +
                    e.getMessage());
        }
    }

    @Override
    public void delete(@NonNull String idString) {
        Optional<Long> id = parseLong(idString);
        id.ifPresent(userRepository::deleteById);
    }

    @Override
    public void changePrivilege(@NonNull String idString, @NonNull String method) {
        Optional<Long> id = parseLong(idString);
        if (id.isPresent()) {
            Optional<User> optionalUser = userRepository.findById(id.get());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setRole(method.equals("privilege_a") ? Role.ADMIN : Role.USER);
                userRepository.save(user);
            }
        }
    }

    @Override
    public String getUsers(@NonNull Model model, @NonNull Pageable pageable) {
        model.addAttribute("page", userRepository.findAll(pageable));
        return Pages.ADMIN_USERS_PAGE.getCropPath();
    }
}
