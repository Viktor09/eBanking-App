package org.poo.cb;

public class CreateContCommandStrategy implements CommandStrategy{
    public void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier){
        commandInvoker.setCommand(new CreateContCommand(cont, commands[3], commands[2], 0.0));
        commandInvoker.executeCommand();
    }
}
