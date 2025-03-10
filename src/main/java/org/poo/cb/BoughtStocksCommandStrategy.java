package org.poo.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoughtStocksCommandStrategy implements CommandStrategy{
    public void execute(String[] commands, Utilizator utilizator, Cont cont, Actiuni actiuni, CommandInvoker commandInvoker, String fisier) {
        try {
            File myObj = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + fisier);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                if (findBought(commands, actiuni, myReader, commandInvoker)) return;
            }
            System.out.println();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static boolean findBought(String[] commands, Actiuni actiuni, Scanner myReader, CommandInvoker commandInvoker) {
        String data = myReader.nextLine();
        if (data.contains(commands[3])) {
            String[] command = data.split(",");
            double suma = Double.parseDouble(command[command.length - 1]) * Double.parseDouble(commands[4]);
            commandInvoker.setCommand(new BoughtStocksCommand(actiuni,commands[3], Integer.parseInt(commands[4]), suma, commands[2]));
            commandInvoker.executeCommand();
            return true;
        }
        return false;
    }

}
