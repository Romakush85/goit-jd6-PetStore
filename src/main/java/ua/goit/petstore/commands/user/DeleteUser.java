package ua.goit.petstore.commands.user;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.User;
import ua.goit.petstore.service.UserService;
import ua.goit.petstore.view.View;

import java.io.IOException;

    public class DeleteUser implements Command {
        public static final String DELETE_USER = "-deleteUser";
        private final View view;
        private final UserService userService;

        public DeleteUser(View view, UserService userService) {
            this.view = view;
            this.userService = userService;
        }

        @Override
        public boolean canExecute(String input) {
            return input.equals(DELETE_USER);
        }

        @Override
        public void execute(String input) throws IOException {
            view.write("Enter username to update:");
            String username = view.read();
            User userToDelete = userService.findByUsername(username);
            if (userToDelete == null) {
                view.write("User not found");
            } else {
                Integer statusCode = userService.deleteUser(userToDelete);
                if (statusCode.equals(200)) {
                    view.write("User deleted");
                } else {
                    view.write("User didn't deleted");
                }
            }
        }
    }

