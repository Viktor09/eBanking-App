package org.poo.cb;

public class AddSumaCommandStrategy implements CommandStrategy{
    public void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier){
        double suma = Double.parseDouble(commands[4]);
        commandInvoker.setCommand(new AddSumaCommand(cont,commands[3], commands[2], suma));
        commandInvoker.executeCommand();
    }
}
