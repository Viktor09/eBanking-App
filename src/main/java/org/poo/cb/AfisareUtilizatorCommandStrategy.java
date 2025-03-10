package org.poo.cb;

public class AfisareUtilizatorCommandStrategy implements CommandStrategy{
    public void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier) {
        commandInvoker.setCommand(new AfisareUtilizatorCommand(utilizator,commands[2]));
        commandInvoker.executeCommand();
    }
}
