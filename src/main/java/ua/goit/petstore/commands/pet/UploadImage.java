package ua.goit.petstore.commands.pet;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.service.PetService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class UploadImage implements Command {
    public static final String UPLOAD_IMAGE = "-uploadPetImage";
    private final View view;
    private final PetService petService;

    public UploadImage(View view, PetService petService) {
        this.view = view;
        this.petService = petService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPLOAD_IMAGE);
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
            view.write("Enter filepath:");
            String filePath = view.read();
            boolean isUploaded = petService.uploadImage(pet, filePath);
            if(isUploaded) {
                view.write("Image uploaded!");
            } else {
                view.write("Image didn't uploaded!");
            }
        }
    }
}
