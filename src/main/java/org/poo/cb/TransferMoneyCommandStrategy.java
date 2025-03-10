package org.poo.cb;

public class TransferMoneyCommandStrategy implements CommandStrategy{
    public void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier){
        commandInvoker.setCommand(new TransferMoneyCommand(cont,commands[2], commands[3], commands[4], Double.parseDouble(commands[5])));
        commandInvoker.executeCommand();
    }
}
