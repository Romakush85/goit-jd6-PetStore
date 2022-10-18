package ua.goit.petstore.commands.pet;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.service.PetService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class DeletePet implements Command {
    public static final String DELETE_PET = "-deletePet";
    private final View view;
    private final PetService petService;


    public DeletePet(View view, PetService petService) {
        this.view = view;
        this.petService = petService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_PET);
    }

    @Override
    public void execute(String input) throws IOException {
        Long id;
        Pet pet;
        while(true) {
            try {
                view.write("Enter pet ID:");
                id = Long.parseLong(view.read());
                pet = petService.findById(id);
                if (pet == null) {
                    view.write("The pet with entered ID is missing");
                } else {
                    boolean isDeleted = petService.deletePet(pet);
                    if(isDeleted) {
                        view.write("Pet deleted");
                    } else {
                        view.write("Pet didn't deleted");
                    }
                }
                break;
            } catch (IllegalArgumentException e) {
                view.write("Invalid ID, use digits:");
            }
        }
    }
}

