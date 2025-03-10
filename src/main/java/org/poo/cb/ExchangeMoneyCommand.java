package org.poo.cb;

public class ExchangeMoneyCommand implements Command{
    private Cont cont;
    private double suma;
    private double cursValutar;
    private String valuta1;
    private String valuta2;
    private String email;

    public ExchangeMoneyCommand(Cont cont,double suma, double cursValutar, String valuta1, String valuta2,String email) {
        this.cont = cont;
        this.valuta1 = valuta1;
        this.valuta2 = valuta2;
        this.email =email;
        this.cursValutar = cursValutar;
        this.suma = suma;
    }
    public void execute() {
        try {
            cont.exchangeMoney(suma, cursValutar,
                    valuta1, valuta2,email);
        } catch (InsufficientAmountException e) {
            System.out.println(e.getMessage());
        }

    }
}
