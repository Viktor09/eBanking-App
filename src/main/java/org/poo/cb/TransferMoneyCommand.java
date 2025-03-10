package org.poo.cb;

public class TransferMoneyCommand implements Command{
    private Cont cont;
    private String email;
    private String friendEmail;
    private String curency;
    private double amount;
    public TransferMoneyCommand(Cont cont, String email, String friendEmail, String curency, double amount) {
        this.cont = cont;
        this.friendEmail = friendEmail;
        this.email = email;
        this.curency = curency;
        this.amount = amount;
    }
    public void execute() {
        try {
            cont.transferMoney(email, friendEmail,
                    curency, amount);
        } catch (InsufficientAmountException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTransferException e) {
            System.out.println(e.getMessage());
        }

    }
}
