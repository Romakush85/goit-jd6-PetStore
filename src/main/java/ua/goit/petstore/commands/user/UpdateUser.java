package ua.goit.petstore.commands.user;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.models.User;
import ua.goit.petstore.service.PetService;
import ua.goit.petstore.service.UserService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class UpdateUser implements Command {
    public static final String UPDATE_USER = "-updateUser";
    private final View view;
    private final UserService userService;

    public UpdateUser(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_USER);
    }

    @Override
    public void execute(String input) throws IOException {
        view.write("Enter username to update:");
        String username = view.read();
        User userToUpdate = userService.findByUsername(username);
        if (userToUpdate == null) {
            view.write("User not found");
        } else {
            Integer statusCode = userService.updateUser(userToUpdate);
            if (statusCode.equals(200)) {
                view.write("User updated");
            } else {
                view.write("User didn't updated");
            }
        }
    }
}
