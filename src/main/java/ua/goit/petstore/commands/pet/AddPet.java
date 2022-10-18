package ua.goit.petstore.commands.pet;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.Category;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.models.PetStatus;
import ua.goit.petstore.models.Tag;
import ua.goit.petstore.service.PetService;
import ua.goit.petstore.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddPet implements Command {
    public static final String ADD_PET = "-addPet";
    private final View view;
    private final PetService petService;

    public AddPet(View view, PetService petService) {
        this.view = view;
        this.petService = petService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(ADD_PET);
    }

    @Override
    public void execute(String input) throws IOException {
        Pet pet = new Pet();
        try {
            view.write("Enter pet ID:");
            pet.setId(Long.valueOf(view.read()));
            Category category = new Category();
            view.write("Enter category ID:");
            category.setId(Integer.valueOf(view.read()));
            view.write("Enter category name:");
            category.setName(view.read());
            pet.setCategory(category);
            view.write("Enter pet name:");
            pet.setName(view.read());
            view.write("How many photos will be  uploaded?");
            int count = Integer.parseInt(view.read());
            String[] photoUrlArray = new String[count];
            view.write("Enter all url, press Enter after each url");
            for (int i = 0; i < count; i++) {
                photoUrlArray[i] = view.read();
            }
            pet.setPhotoUrls(photoUrlArray);
            view.write("How many tags will be added?");
            count = Integer.parseInt(view.read());
            List<Tag> tagsList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Tag tag = new Tag();
                view.write("Enter tag ID:");
                tag.setId(Integer.valueOf(view.read()));
                view.write("Enter tag name:");
                tag.setName(view.read());
                tagsList.add(tag);
            }
            pet.setTags(tagsList);
            view.write("Enter pet status (available, pending, sold):");
            pet.setStatus(PetStatus.valueOf(view.read()));
            Pet addedPet = petService.addPet(pet);
            if(addedPet == null) {
                view.write("Pet doesn't added!");
            } else {
                view.write("Pet added!" + addedPet.toString());
            }
        } catch (NumberFormatException ex) {
            view.write("Illegal number. Please try again");
        } catch (IllegalArgumentException ex) {
            view.write("Illegal pet status. Please, enter the one from suggested (available, pending, sold):");
        }

    }
}
