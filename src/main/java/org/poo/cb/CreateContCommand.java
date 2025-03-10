package org.poo.cb;

public class CreateContCommand implements Command{
    private Cont cont;
    private String valuta;
    private String email;
    private double suma;
    public CreateContCommand(Cont cont, String valuta, String email, double suma) {
        this.cont = cont;
        this.valuta = valuta;
        this.email = email;
        this.suma = suma;
    }
    public void execute() {
        try {
            cont.createCont(valuta, email, suma);
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

    }
}
