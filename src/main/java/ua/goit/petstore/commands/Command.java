package ua.goit.petstore.commands;

import java.io.IOException;

public interface Command {
    boolean canExecute(String input);

    void execute(String input) throws IOException;
}
