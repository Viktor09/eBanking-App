package org.poo.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CreateActiuniCommandStrategy implements CommandStrategy{
    public void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier) {
        try {
            File myObj = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + fisier);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(!data.contains("Stock")){
                    findStocks(actiuni, data, commandInvoker);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        actiuni.afisare();
    }

    private static void findStocks(Actiuni actiuni, String data, CommandInvoker commandInvoker) {
        String [] command = data.split(",");
        double sumaCinciZile = 0, sumaZeceZile = 0;
        String numeCompanie = command[0];
        for(int i = 1; i < command.length; i++){
            if(i > 5){
                sumaCinciZile = sumaCinciZile + Double.parseDouble(command[i]);
            }
            sumaZeceZile = sumaZeceZile + Double.parseDouble(command[i]);
        }
        if(sumaCinciZile/5 > sumaZeceZile/10){
            commandInvoker.setCommand(new CreateActiuniCommand(actiuni, numeCompanie, sumaCinciZile/5));
            commandInvoker.executeCommand();
        }
        //System.out.println(numeCompanie + " " + sumaCinciZile/5 + " " + sumaZeceZile/10);
    }
}
