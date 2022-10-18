package ua.goit.petstore.commands.user;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.*;
import ua.goit.petstore.service.UserService;
import ua.goit.petstore.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddUser implements Command {
    public static final String ADD_USER = "-addUser";
    private final View view;
    private final UserService userService;

    public AddUser(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(ADD_USER);
    }

    @Override
    public void execute(String input) throws IOException {
        User user = new User();
        try {
            view.write("Enter user ID:");
            user.setId(Integer.valueOf(view.read()));
            view.write("Enter username:");
            user.setUsername(view.read());
            view.write("Enter first name:");
            user.setFirstName(view.read());
            view.write("Enter last name:");
            user.setLastName(view.read());
            view.write("Enter email:");
            user.setEmail(view.read());
            view.write("Enter password:");
            user.setPassword(view.read());
            view.write("Enter phone:");
            user.setPhone(view.read());
            view.write("Enter user status:");
            user.setUserStatus(Integer.parseInt(view.read()));
            if(userService.addUser(user)) {
                view.write("User added!");
            } else {
                view.write("User didn't added!");
            }
        } catch (NumberFormatException ex) {
            view.write("Illegal number. Please try again");
        }
    }
}
