package org.poo.cb;

public class createActiuniCommand implements Command{
    private Actiuni actiuni;


    public createActiuniCommand(Actiuni actiuni){
        this.actiuni = actiuni;
    }

    public void execute() {
        actiuni.afisare();

    }

}
