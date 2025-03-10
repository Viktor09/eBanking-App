package org.poo.cb;

public interface CommandStrategy {
    void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier);
}
