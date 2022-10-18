package ua.goit.petstore.commands.pet;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.service.PetService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class UpdatePet implements Command {
    public static final String UPDATE_PET = "-updatePet";
    private final View view;
    private final PetService petService;

    public UpdatePet(View view, PetService petService) {
        this.view = view;
        this.petService = petService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_PET);
    }

    @Override
    public void execute(String input) throws IOException {
        view.write("Enter pet ID to update:");
        Long id = Long.valueOf(view.read());
        Pet petToUpdate = petService.findById(id);
        Pet updatedPet = petService.updatePet(petToUpdate);
        view.write(updatedPet.toString());
    }
}
