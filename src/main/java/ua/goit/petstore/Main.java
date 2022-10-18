package ua.goit.petstore;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import ua.goit.petstore.commands.*;
import ua.goit.petstore.commands.pet.*;
import ua.goit.petstore.commands.store.AddOrder;
import ua.goit.petstore.commands.store.DeleteOrder;
import ua.goit.petstore.commands.store.FindOrderById;
import ua.goit.petstore.commands.store.GetInventory;
import ua.goit.petstore.commands.user.*;
import ua.goit.petstore.controller.PetStoreManagement;
import ua.goit.petstore.models.User;
import ua.goit.petstore.service.OrderService;
import ua.goit.petstore.service.PetService;
import ua.goit.petstore.service.UserService;
import ua.goit.petstore.view.Console;
import ua.goit.petstore.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        PetService petService = new PetService(httpclient);
        OrderService orderService = new OrderService(httpclient);
        UserService userService = new UserService(httpclient);

        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);

        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new Exit(view));
        commands.add(new FindPetByStatus(view, petService));
        commands.add(new FindPetById(view, petService));
        commands.add(new AddPet(view, petService));
        commands.add(new UpdatePet(view, petService));
        commands.add(new UpdatePetWithFormData(view, petService));
        commands.add(new UploadImage(view, petService));
        commands.add(new DeletePet(view, petService));
        commands.add(new AddOrder(view, orderService, petService));
        commands.add(new FindOrderById(view, orderService));
        commands.add(new DeleteOrder(view, orderService));
        commands.add(new GetInventory(view, orderService));
        commands.add(new AddUser(view, userService));
        commands.add(new FindUserByUsername(view, userService));
        commands.add(new UpdateUser(view, userService));
        commands.add(new DeleteUser(view, userService));
        commands.add(new CreateUsersWithList(view, userService));
        commands.add(new LogIn(view, userService));
        commands.add(new LogOut(view, userService));

        PetStoreManagement petStoreManagement = new PetStoreManagement(view, commands);
        petStoreManagement.run();
    }
}
