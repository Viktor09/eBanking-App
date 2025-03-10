package org.poo.cb;

public class AfisareContCommandStrategy implements CommandStrategy{
    public void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier) {
        commandInvoker.setCommand(new AfisareContCommand(cont, commands[2]));
        commandInvoker.executeCommand();
    }
}