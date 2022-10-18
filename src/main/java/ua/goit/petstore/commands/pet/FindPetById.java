package ua.goit.petstore.commands.pet;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.models.PetStatus;
import ua.goit.petstore.service.PetService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class FindPetById implements Command {
    public static final String FIND_BY_ID = "-findPetById";
    private final View view;
    private final PetService petService;

    public FindPetById(View view, PetService petService) {
        this.view = view;
        this.petService = petService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_BY_ID);
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
                break;
            } catch (IllegalArgumentException e) {
                view.write("Invalid ID, use digits:");
            }
        }
        if(pet == null) {
            view.write("Pet not found");
        } else {
            view.write(pet.toString());
        }
    }
}
