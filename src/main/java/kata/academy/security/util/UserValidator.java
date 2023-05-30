package kata.academy.security.util;

import kata.academy.security.model.User;
import kata.academy.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignore) {
            return; // пользователь с таким именем не найден
        }
        errors.rejectValue("username", "", "Человек с таким именем пользователя существует");
    }
}
