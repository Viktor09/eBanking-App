package org.poo.cb;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Running Main");
        }else{
            String comenzi = "commands.txt";
            Utilizator utilizator = Utilizator.getInstance();
            Cont cont = Cont.getInstance();
            Actiuni actiuni = Actiuni.getInstance();
            CommandInvoker commandInvoker = new CommandInvoker();
            CommandContext commandContext = new CommandContext();
            HashMap<String, CommandStrategy> strategyMap = HashConstruct();
            if(args[2].contains(comenzi)){
                File myObj = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + args[2]);

                try (BufferedReader reader = new BufferedReader(new FileReader(myObj))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        processLine(line, args[1], utilizator, cont, actiuni, commandInvoker, commandContext, strategyMap);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
            actiuni.delete();
            utilizator.delete();
            cont.delete();
        }

    }

    @NotNull
    private static HashMap<String, CommandStrategy> HashConstruct() {
        HashMap<String, CommandStrategy> strategyMap = new HashMap<>();
        strategyMap.put("CREATE USER", new CreateUtilizatorCommandStrategy());
        strategyMap.put("ADD FRIEND", new AddFriendCommandStrategy());
        strategyMap.put("LIST USER", new AfisareUtilizatorCommandStrategy());
        strategyMap.put("ADD ACCOUNT", new CreateContCommandStrategy());
        strategyMap.put("LIST PORTFOLIO", new AfisareContCommandStrategy());
        strategyMap.put("ADD MONEY", new AddSumaCommandStrategy());
        strategyMap.put("EXCHANGE MONEY", new ExchangeMoneyCommandStrategy());
        strategyMap.put("TRANSFER MONEY", new TransferMoneyCommandStrategy());
        strategyMap.put("RECOMMEND STOCKS", new CreateActiuniCommandStrategy());
        strategyMap.put("BUY STOCKS", new BoughtStocksCommandStrategy());
        return strategyMap;
    }

    private static void processLine(String line, String fisier, Utilizator utilizator,
                                    Cont cont, Actiuni actiuni, CommandInvoker commandInvoker,
                                    CommandContext commandContext, HashMap<String, CommandStrategy> strategyMap) {
        for (String keyword : strategyMap.keySet()) {
            if (line.startsWith(keyword)) {
                commandContext.setStrategy(strategyMap.get(keyword));
                commandContext.executeStrategy(line.split(" "), utilizator, cont, actiuni, commandInvoker, fisier);
                break;
            }
        }
    }

}