package org.poo.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExchangeMoneyCommandStrategy implements CommandStrategy{
    public void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier){
        int index = 0;
        double value = 0;
        value = preluareDate(commands, index, value);
        commandInvoker.setCommand(new ExchangeMoneyCommand(cont,Double.parseDouble(commands[5]),value,commands[2],commands[4],commands[3]));
        commandInvoker.executeCommand();
    }
    private static double preluareDate(String[] commands, int index, double value) {
        try {
            File myObj = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + "common" + File.separator +  "exchangeRates.csv");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                index = getIndex(commands, index, data);
                value = getValue(commands, index, value, data);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return value;
    }

    private static double getValue(String[] commands, int index, double value, String data) {
        if(data.startsWith(commands[4])){
            String [] command = data.split(",");
            value = Double.parseDouble(command[index]);
        }
        return value;
    }

    private static int getIndex(String[] commands, int index, String data) {
        if(data.startsWith("Base")){
            String [] command = data.split(",");
            for(int i = 0; i < command.length; i++){
                if(command[i].equals(commands[3])){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

}
