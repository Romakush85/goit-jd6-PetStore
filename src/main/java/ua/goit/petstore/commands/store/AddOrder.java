package ua.goit.petstore.commands.store;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.*;
import ua.goit.petstore.service.OrderService;
import ua.goit.petstore.service.PetService;
import ua.goit.petstore.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddOrder implements Command {
    public static final String ADD_ORDER = "-addOrder";
    private final View view;
    private final OrderService orderService;
    private final PetService petService;

    public AddOrder(View view, OrderService orderService, PetService petService) {
        this.view = view;
        this.orderService = orderService;
        this.petService = petService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(ADD_ORDER);
    }

    @Override
    public void execute(String input) throws IOException {
        Order order = new Order();
        try {
            view.write("Enter order ID:");
            order.setId(Integer.valueOf(view.read()));
            view.write("Enter pet ID:");
            order.setPetId(Long.valueOf(view.read()));
            if(petService.findById(order.getPetId()) == null) {
                view.write("Pet with entered ID is not found");
            }
            view.write("Enter quantity:");
            order.setQuantity(Integer.valueOf(view.read()));
            view.write("Enter ship date:");
            order.setShipDate(view.read());
            view.write("Enter order status (placed, approved, delivered):");
            order.setStatus(OrderStatus.valueOf(view.read()));
            view.write("Enter if order is completed (true, false):");
            order.setComplete(Boolean.valueOf(view.read()));
            Order addedOrder = orderService.addOrder(order);
            if(addedOrder == null) {
                view.write("Order doesn't added!");
            } else {
                view.write("Order added!" + addedOrder.toString());
            }


        } catch (NumberFormatException ex) {
            view.write("Illegal number. Please try again");
        } catch (IllegalArgumentException e) {
            view.write("Illegal order status. Please, enter the one from suggested (placed, approved, delivered):");
        }

    }
}
