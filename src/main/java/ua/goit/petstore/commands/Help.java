package ua.goit.petstore.commands;

import ua.goit.petstore.commands.pet.*;
import ua.goit.petstore.commands.store.AddOrder;
import ua.goit.petstore.commands.store.DeleteOrder;
import ua.goit.petstore.commands.store.FindOrderById;
import ua.goit.petstore.commands.store.GetInventory;
import ua.goit.petstore.commands.user.*;
import ua.goit.petstore.view.View;

public class Help implements Command{
    private static final String HELP = "help";
    private final View view;

    public Help(View view) {
        this.view = view;
    }


    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP);
    }

    @Override
    public void execute(String input) {
        view.write(String.format("-----Pet commands-----"));
        view.write(String.format("Enter %s to see all commands", Help.HELP));
        view.write(String.format("Enter %s to exit program", Exit.EXIT));
        view.write(String.format("Enter %s to find pets by pet status", FindPetByStatus.FIND_BY_STATUS));
        view.write(String.format("Enter %s to find pet by ID", FindPetById.FIND_BY_ID));
        view.write(String.format("Enter %s to add new pet to store", AddPet.ADD_PET));
        view.write(String.format("Enter %s to update existing pet", UpdatePet.UPDATE_PET));
        view.write(String.format("Enter %s to update pet with form data", UpdatePetWithFormData.UPD_WTH_FORM_DATA));
        view.write(String.format("Enter %s to upload pet image", UploadImage.UPLOAD_IMAGE));
        view.write(String.format("Enter %s to delete pet", DeletePet.DELETE_PET));
        view.write(String.format("-----Order commands-----"));
        view.write(String.format("Enter %s to add new order to store", AddOrder.ADD_ORDER));
        view.write(String.format("Enter %s to find order by ID", FindOrderById.FIND_BY_ID));
        view.write(String.format("Enter %s to delete order", DeleteOrder.DELETE_ORDER));
        view.write(String.format("Enter %s to get inventory", GetInventory.GET_INVENTORY));
        view.write(String.format("-----User commands-----"));
        view.write(String.format("Enter %s to add new user", AddUser.ADD_USER));
        view.write(String.format("Enter %s to find user by username", FindUserByUsername.FIND_USER));
        view.write(String.format("Enter %s to update user", UpdateUser.UPDATE_USER));
        view.write(String.format("Enter %s to delete user", DeleteUser.DELETE_USER));
        view.write(String.format("Enter %s to create list of users ", CreateUsersWithList.ADD_WITH_LIST));
        view.write(String.format("Enter %s to log in ", LogIn.LOGIN_USER));
        view.write(String.format("Enter %s to log out ", LogOut.LOGOUT_USER));

    }


}
