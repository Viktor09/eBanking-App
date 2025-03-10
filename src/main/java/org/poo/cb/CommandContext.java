package org.poo.cb;

public class CommandContext {
    private CommandStrategy strategy;

    public void setStrategy(CommandStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier) {
        if (strategy != null) {
            strategy.execute(commands, utilizator, cont, actiuni, commandInvoker, fisier);
        }
    }
}
