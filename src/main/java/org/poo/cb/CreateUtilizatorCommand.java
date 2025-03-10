package org.poo.cb;

public class CreateUtilizatorCommand implements Command {
    private Utilizator utilizator;
    private String email;
    private String firstName;
    private String lastName;
    private String address;

    public CreateUtilizatorCommand(Utilizator utilizator, String email, String firstName, String lastName, String address) {
        this.utilizator = utilizator;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public void execute() {
        utilizator.createUtilizator(email, firstName, lastName, address);

    }
}
