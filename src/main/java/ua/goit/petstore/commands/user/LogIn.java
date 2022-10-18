package ua.goit.petstore.commands.user;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.User;
import ua.goit.petstore.service.UserService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class LogIn implements Command {
    public static final String LOGIN_USER = "-LogInUser";
    private final View view;
    private final UserService userService;

    public LogIn(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(LOGIN_USER);
    }

    @Override
    public void execute(String input) throws IOException {
        view.write("Enter username:");
        String userName = view.read();
        User user = userService.findByUsername(userName);
        if (user == null) {
            view.write("User with entered username is not found");
        } else {
            view.write("Enter password:");
            String userPassword = view.read();
            if(userPassword.equals(user.getPassword())) {
                Integer statusCode = userService.logIn(userName, userPassword);
                view.write(statusCode.equals(200) ? "You are logged in" : "Something went wrong, pls try again");
            } else {
                view.write("The password is incorrect");
            }
        }
    }
}
