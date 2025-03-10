package org.poo.cb;

public class AfisareContCommand implements Command{
    private String email;
    private Cont cont;
    public AfisareContCommand(Cont cont, String email){
        this.cont = cont;
        this.email = email;
    }
    public void execute() {
        cont.afisare(email);
    }
}
