package ua.goit.petstore.commands.store;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.service.OrderService;
import ua.goit.petstore.models.*;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class FindOrderById implements Command {
    public static final String FIND_BY_ID = "-findOrderById";
    private final View view;
    private final OrderService orderService;

    public FindOrderById(View view, OrderService orderService) {
        this.view = view;
        this.orderService = orderService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_BY_ID);
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
                break;
            } catch (IllegalArgumentException e) {
                view.write("Invalid ID, use digits:");
            }
        }
        if(order == null) {
            view.write("Order not found");
        } else {
            view.write(order.toString());
        }
    }
}
