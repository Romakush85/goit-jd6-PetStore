package ua.goit.petstore.commands.store;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.service.OrderService;
import ua.goit.petstore.view.View;

import java.io.IOException;

public class GetInventory implements Command {
    public static final String GET_INVENTORY = "-getInventory";
    private final View view;
    private final OrderService orderService;

    public GetInventory(View view, OrderService orderService) {
        this.view = view;
        this.orderService = orderService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_INVENTORY);
    }

    @Override
    public void execute(String input) throws IOException {
        view.write(orderService.getInventory());
    }
}
