package org.poo.cb;

public class AddFriendCommandStrategy implements CommandStrategy{
    public void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier) {
        String emailUser = commands[2];
        String emailFriend = commands[3];
        commandInvoker.setCommand(new AddFriendCommand(utilizator, emailUser, emailFriend));
        commandInvoker.executeCommand();
    }
}
