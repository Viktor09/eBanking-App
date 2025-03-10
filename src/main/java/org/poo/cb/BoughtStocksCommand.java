package org.poo.cb;

public class BoughtStocksCommand implements Command{
    private Actiuni actiuni;
    private String name;
    private Integer amount;
    private double sum;
    private String email;
    public BoughtStocksCommand(Actiuni actiuni, String name, Integer amount, double sum, String email){
        this.actiuni = actiuni;
        this.name = name;
        this.amount = amount;
        this.sum = sum;
        this.email = email;
    }
    public void execute() {
        actiuni.stocksToBuy(name, amount, sum, email);

    }
}
