package ua.goit.petstore.commands;

import ua.goit.petstore.exception.ExitException;
import ua.goit.petstore.view.View;

public class Exit implements Command{
    public static final String EXIT = "exit";
    public static final String BYE = "Bye!";
    private final View view;

    public Exit(View view) {
        this.view = view;
    }
    @Override
    public boolean canExecute(String input) {
        return input.equals(EXIT);
    }

    @Override
    public void execute(String input) {
        view.write(BYE);
        throw new ExitException();
    }
}
