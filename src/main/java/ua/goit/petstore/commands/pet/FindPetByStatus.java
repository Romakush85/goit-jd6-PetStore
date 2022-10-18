package ua.goit.petstore.commands.pet;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.models.PetStatus;
import ua.goit.petstore.service.PetService;
import ua.goit.petstore.view.View;

import java.io.IOException;
import java.util.List;

public class FindPetByStatus implements Command {
    public static final String FIND_BY_STATUS = "-findPetByStatus";
    private final View view;
    private final PetService petService;

    public FindPetByStatus(View view, PetService petService) {
        this.view = view;
        this.petService = petService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_BY_STATUS);
    }

    @Override
    public void execute(String input) throws IOException {
        PetStatus status;
        List<Pet> pets;
        while(true) {
            try {
                view.write("Enter pet status:");
                status = PetStatus.valueOf(view.read());
                pets = petService.findByStatus(status);
                System.out.println(pets);
                break;
            } catch (IllegalArgumentException e) {
                view.write("Invalid status, use one of (available, pending, sold):");
            }
        }
        pets.forEach(pet -> view.write(pet.toString()));
    }
}
