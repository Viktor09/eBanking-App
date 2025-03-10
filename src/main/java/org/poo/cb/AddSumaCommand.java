package org.poo.cb;

public class AddSumaCommand implements Command{
    private String valuta;
    private double suma;
    private String email;
    private Cont cont;
    public AddSumaCommand(Cont cont, String valuta, String email, double suma) {
        this.cont = cont;
        this.valuta = valuta;
        this.email = email;
        this.suma = suma;
    }
    public void execute() {
        cont.setSuma(suma,email,valuta);

    }

}
