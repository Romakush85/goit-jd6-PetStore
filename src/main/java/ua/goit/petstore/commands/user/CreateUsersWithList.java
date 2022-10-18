package ua.goit.petstore.commands.user;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.User;
import ua.goit.petstore.service.UserService;
import ua.goit.petstore.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateUsersWithList implements Command {
    public static final String ADD_WITH_LIST = "-addUsersWithList";
    private final View view;
    private final UserService userService;

    public CreateUsersWithList(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(ADD_WITH_LIST);
    }

    @Override
    public void execute(String input) throws IOException {
        view.write("How many users will be in list?:");
        int count = Integer.parseInt(view.read());
        List<User> users = new ArrayList<>();
        for(int i = 1; i <= count; i++) {
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
                users.add(user);
                view.write("User №" + i + " added to list");
            } catch (NumberFormatException ex) {
                view.write("Illegal number. Please try again");
            }
        }
        view.write("Adding users to store .....");
        Integer statusCode = userService.createWithList(users);
        if (statusCode.equals(200)) {
            view.write("All users added to store");
        } else {
            view.write("Users didn't added to store");
        }
    }
}
