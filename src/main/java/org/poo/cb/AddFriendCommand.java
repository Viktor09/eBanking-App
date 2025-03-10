package org.poo.cb;

public class AddFriendCommand implements Command{
    private Utilizator utilizator;
    private String emailUser;
    private String emailFriend;

    public AddFriendCommand(Utilizator utilizator, String emailUser, String emailFriend) {
        this.utilizator = utilizator;
        this.emailUser = emailUser;
        this.emailFriend = emailFriend;
    }

    @Override
    public void execute() {
        utilizator.adaugaPrienteni(emailUser, emailFriend);
    }
}
