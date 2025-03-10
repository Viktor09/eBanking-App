package org.poo.cb;

public class CreateUtilizatorCommandStrategy implements CommandStrategy{
    public void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier) {
        String email = commands[2];
        String firstName = commands[3];
        String lastName = commands[4];
        String address = prelucrareAdresa(commands);
        commandInvoker.setCommand(new CreateUtilizatorCommand(utilizator, email, firstName, lastName, address));
        commandInvoker.executeCommand();
    }
    private static String prelucrareAdresa(String[] command) {
        String address = "";
        for(int i = 5; i < command.length; i++){
            address = address + command[i];
            if(i == command.length - 1)
                break;
            address = address + " ";
        }
        return address;
    }
}
