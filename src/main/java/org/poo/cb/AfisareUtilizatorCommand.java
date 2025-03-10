package org.poo.cb;

public class AfisareUtilizatorCommand implements Command{
    private String email;
    private Utilizator utilizator;
    public AfisareUtilizatorCommand(Utilizator utilizator, String email){
        this.utilizator = utilizator;
        this.email = email;
    }
    public void execute() {
        utilizator.afisare(email);
    }

}
