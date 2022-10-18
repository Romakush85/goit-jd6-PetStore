package ua.goit.petstore.controller;

import ua.goit.petstore.commands.Command;
import ua.goit.petstore.exception.ExitException;
import ua.goit.petstore.view.View;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



public class PetStoreManagement {
    private final View view;
    private final List<Command> commands;

    public PetStoreManagement(View view, List<Command> commands) {
        this.view = view;
        this.commands = commands;
    }

    public void run(){
        view.write("Hello, pls enter help to see all commands");
        try{
            execute();
        } catch(ExitException | IOException e)  {

        }
    }



    private void execute() throws IOException {
        while (true) {
            String input = view.read();
            boolean isInputCorrect = false;
            for (Command command : commands) {
                if (command.canExecute(input)) {

                    command.execute(input);
                    isInputCorrect = true;
                }
            }
            if (!isInputCorrect) {
                view.write("Command not found. Please enter help to see all commands");
            }
        }
    }
}
