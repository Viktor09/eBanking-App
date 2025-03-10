package org.poo.cb;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Actiuni {
    private static ArrayList<Double> stockuriRecomandate = new ArrayList<Double>();
    private static ArrayList<String> numeleStockurilor = new ArrayList<String>();
    private static ArrayList<String> numeCompanies= new ArrayList<String>();
    private static ArrayList<Integer> amountStock = new ArrayList<Integer>();
    private static ArrayList<String> emails = new ArrayList<String>();
    private ArrayList<ActiuniObserver> observers = new ArrayList<>();

    String numeCompanie;
    double suma;
    private static Actiuni instance;
    private Actiuni(){

    }
    public static Actiuni getInstance(){
        if(instance == null){
            instance = new Actiuni();
        }
        return instance;
    }

    public void addObserver(ActiuniObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String numeCompanie, Integer amount, double sum, String email) {
        for (ActiuniObserver observer : observers) {
            observer.update(numeCompanie, amount, sum, email);
        }
    }
    public void createActiuni(String numeCompanie, double suma) {
        this.numeCompanie = numeCompanie;
        this.suma = suma;
        numeleStockurilor.add(numeCompanie);
        stockuriRecomandate.add(suma);


        notifyObservers(numeCompanie, null, suma, null);
    }
    public int getSize(){
        return emails.size();
    }
    public String getEmail(int i){
        return emails.get(i);
    }
    public Integer getAmount(int i){
        return amountStock.get(i);
    }
    public String getNumeCompanie(int i){
        return numeCompanies.get(i);
    }


    public void afisare(){
        String a= "";
        a = a + "{\"stocksToBuy\":[";

        Collections.sort(numeleStockurilor);
        for(int i = numeleStockurilor.size() - 1; i >= 0; i--){
            a = a + "\""+ numeleStockurilor.get(i) + "\",";
        }
        a = a.substring(0, a.length()-1);
        a = a +"]}";
        System.out.println(a);
    }
    public void stocksToBuy(String name, Integer amount, double sum, String email){
        Cont cont = Cont.getInstance();
        int ok = 0, index = -1;
        for(int i = 0; i < cont.getSize(); i++){
            if(cont.getValuta(i).equals("USD") && cont.getSuma(i) >= sum){
                ok = 1;
                index = i;
                break;
            }
        }
        if(ok == 1){
            double suma = cont.getSuma(index) - sum;
            cont.setSumaBuyStocks(suma, index);
            numeCompanies.add(name);
            amountStock.add(amount);
            emails.add(email);


            notifyObservers(name, amount, sum, email);
            return;
        }else {

            try {
                throw new InsufficientAmountException("Insufficient amount in account for buying stock");
            } catch (InsufficientAmountException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void delete(){
        stockuriRecomandate.clear();
        numeCompanies.clear();
        numeleStockurilor.clear();
        amountStock.clear();
        emails.clear();
        observers.clear();

    }
}
