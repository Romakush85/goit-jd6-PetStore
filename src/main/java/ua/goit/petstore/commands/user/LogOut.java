package ua.goit.petstore.commands.user;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.service.UserService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class LogOut implements Command {
    public static final String LOGOUT_USER = "-LogOutUser";
    private final View view;
    private final UserService userService;

    public LogOut(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(LOGOUT_USER);
    }

    @Override
    public void execute(String input) throws IOException {
        Integer statusCode = userService.logOut();
        view.write(statusCode.equals(200) ? "You are logged out" : "Something went wrong, pls try again");
    }
}
