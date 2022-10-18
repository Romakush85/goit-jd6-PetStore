package ua.goit.petstore.commands.store;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.models.Order;
import ua.goit.petstore.models.Pet;
import ua.goit.petstore.service.OrderService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class DeleteOrder implements Command {
    public static final String DELETE_ORDER = "-deleteOrder";
    private final View view;
    private final OrderService orderService;

    public DeleteOrder(View view, OrderService orderService) {
        this.view = view;
        this.orderService = orderService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_ORDER);
    }

    @Override
    public void execute(String input) throws IOException {
        Integer id;
        Order order;
        while(true) {
            try {
                view.write("Enter order ID:");
                id = Integer.parseInt(view.read());
                order = orderService.findById(id);
                if (order == null) {
                    view.write("The order with entered ID is missing");
                } else {
                    boolean isDeleted = orderService.deleteOrder(order);
                    if(isDeleted) {
                        view.write("Order deleted");
                    } else {
                        view.write("Order didn't deleted");
                    }
                }
                break;
            } catch (IllegalArgumentException e) {
                view.write("Invalid ID, use digits:");
            }
        }

    }
}
