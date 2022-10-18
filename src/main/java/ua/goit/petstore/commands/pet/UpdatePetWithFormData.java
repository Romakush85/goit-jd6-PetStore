package ua.goit.petstore.commands.pet;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.models.PetStatus;
import ua.goit.petstore.service.PetService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class UpdatePetWithFormData implements Command {
    public static final String UPD_WTH_FORM_DATA = "-updatePetWithFormData";
    private final View view;
    private final PetService petService;

    public UpdatePetWithFormData(View view, PetService petService) {
        this.view = view;
        this.petService = petService;
    }
    @Override
    public boolean canExecute(String input) {
        return input.equals(UPD_WTH_FORM_DATA);
    }

    @Override
    public void execute(String input) throws IOException {
        Long id;
        Pet pet;
        Pet updatedPet;
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
            view.write("Update name of the pet:");
            pet.setName(view.read());
            try {
                view.write("Enter pet status (available, pending, sold):");
                pet.setStatus(PetStatus.valueOf(view.read()));
                updatedPet = petService.updatePetWithFormData(pet);
                if (updatedPet == null) {
                    view.write("Pet doesn't updated!");
                } else {
                    view.write("Pet updated!" + updatedPet.toString());
                }
            }
            catch  (IllegalArgumentException ex) {
                view.write("Illegal pet status. Please, enter the one from suggested (available, pending, sold):");
            }
        }
    }
}
