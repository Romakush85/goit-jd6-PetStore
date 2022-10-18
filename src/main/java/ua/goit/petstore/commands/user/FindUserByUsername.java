package ua.goit.petstore.commands.user;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.User;
import ua.goit.petstore.service.UserService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class FindUserByUsername implements Command {
    public static final String FIND_USER = "-findUser";
    private final View view;
    private final UserService userService;

    public FindUserByUsername(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_USER);
    }

    @Override
    public void execute(String input) throws IOException {
        String username;
        User user;
        view.write("Enter username:");
        username = view.read();
        user = userService.findByUsername(username);
        if(user == null) {
            view.write("User not found");
        } else {
            view.write(user.toString());
        }
    }
}
