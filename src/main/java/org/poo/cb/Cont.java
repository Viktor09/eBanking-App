package org.poo.cb;
import java.util.ArrayList;

public class Cont {
    private String valuta;
    private double suma;
    private String email;
    private static ArrayList<String> listaValuta = new ArrayList<String>();
    private static ArrayList<String> listaCompanii = new ArrayList<String>();
    private static ArrayList<Double> listaSuma = new ArrayList<Double>();
    private static ArrayList<String> listaEmailuri = new ArrayList<String>();
    private static Cont instance;
    private Cont(){

    }
    public static Cont getInstance(){
        if(instance == null){
            instance = new Cont();
        }
        return instance;
    }
    public void createCont(String valuta, String email, double suma) throws UserAlreadyExistsException {
        for (int i = 0; i < listaValuta.size(); i++) {
            if (listaEmailuri.contains(email) && listaValuta.contains(valuta)) {
                throw new UserAlreadyExistsException("User with " + email + " and currency " + valuta + " already exists");
            }
        }

        this.valuta = valuta;
        this.email = email;
        this.suma = suma;

        listaValuta.add(valuta);
        listaEmailuri.add(email);
        listaSuma.add(suma);
    }
    public int getSize(){
        return listaSuma.size();
    }
    public String getEmail(int i){
        return listaEmailuri.get(i);
    }
    public double getSuma(int i){
        return listaSuma.get(i);
    }
    public String getValuta(int i){
        return listaValuta.get(i);
    }
    public void setSumaBuyStocks(double suma, int index){
        listaSuma.set(index, suma);
    }
    public void setSuma(double suma, String email, String valuta){
        for(int i = 0; i < listaSuma.size(); i++){
            if(listaEmailuri.get(i).contains(email) &&
                    listaValuta.get(i).contains(valuta)){
                listaSuma.set(i, suma);
                return;
            }
        }
    }
    public void exchangeMoney(double suma, double cursValutar, String email,
                              String valuta1, String valuta2) throws InsufficientAmountException {
        for (int i = 0; i < listaSuma.size(); i++) {
            if (listaEmailuri.get(i).equals(email) && listaValuta.get(i).equals(valuta1)) {
                // Exchange from valuta1 to valuta2
                double sum_intermed = listaSuma.get(i) + suma;
                listaSuma.set(i, sum_intermed);
            }

            calculateMoney(suma, cursValutar, email, valuta2, i);
        }
    }

    private static void calculateMoney(double suma, double cursValutar, String email, String valuta2, int i) throws InsufficientAmountException {
        if (listaEmailuri.get(i).equals(email) && listaValuta.get(i).equals(valuta2)) {
            // Exchange from valuta2 to valuta1
            double suma1 = suma * cursValutar;
            if (suma1 > listaSuma.get(i)) {
                throw new InsufficientAmountException("Insufficient amount in account " + valuta2 + " for exchange");
            }
            if (suma1 > 0.5 * listaSuma.get(i)) {
                double a = listaSuma.get(i);
                a -= 0.01 * suma1;
                listaSuma.set(i, a);
            }
            double sum_intermed = listaSuma.get(i) - suma1;
            listaSuma.set(i, sum_intermed);
        }
    }

    public void transferMoney(String email, String friendEmail, String currency, double amount) throws InsufficientAmountException, UnauthorizedTransferException {
        Utilizator utilizator = Utilizator.getInstance();
        int ok = 0;
        int index1 = 0, index2 = 0;

        for (int i = 0; i < utilizator.getSizePrieteni(); i++) {
            if (email.equals(utilizator.getEmail(i)) && friendEmail.equals(utilizator.getEmailFriend(i))) {
                ok = 1;
            }
        }

        if (ok == 0) {
            throw new UnauthorizedTransferException("You are not allowed to transfer money to " + friendEmail);
        } else {
            for (int i = 0; i < listaValuta.size(); i++) {
                if (listaEmailuri.get(i).equals(email)) {
                    index1 = i;
                }
                if (listaEmailuri.get(i).equals(friendEmail)) {
                    index2 = i;
                }
            }
        }

        finalAmount(currency, amount, index1, index2);
    }

    private static void finalAmount(String currency, double amount, int index1, int index2) throws InsufficientAmountException {
        double sumaSursa = listaSuma.get(index1);
        double sumaDestinatie = listaSuma.get(index2);

        if (sumaSursa < amount) {
            throw new InsufficientAmountException("Insufficient amount in account " + currency + " for transfer");
        }

        sumaSursa = sumaSursa - amount;
        listaSuma.set(index1, sumaSursa);
        sumaDestinatie = sumaDestinatie + amount;
        listaSuma.set(index2, sumaDestinatie);
    }

    public void afisare(String email){
        Actiuni a1 = Actiuni.getInstance();
        int ok = 0;
        String a = "{\"stocks\":[";
        for(int i = 0; i < a1.getSize(); i++){
            if(a1.getEmail(i).equals(email)){
                a = a + "{\"stockName\":";
                a = a + "\"" + a1.getNumeCompanie(i);
                a = a + "\"," + "\"amount\":";
                a = a + a1.getAmount(i);
                a = a + "},";
                ok = 1;
            }
        }
        if(ok == 1){
            a = a.substring(0, a.length()-1);
        }
        a = a + "],";
        a = a + "\"accounts\":[";

        for(int i = 0; i < listaValuta.size(); i++){
            if(email.contains(listaEmailuri.get(i))){
                a = a + "{\"currencyName\":";
                a = a + "\"" + listaValuta.get(i);
                // System.out.println(listaValuta.get(i) + " " + listaSuma.get(i));
                a = a + "\"," + "\"amount\":";
                String formattedValue = String.format("%.2f", listaSuma.get(i));
                a = a + "\"" + formattedValue;
                a = a + "\"},";
            }
        }
        String b = a.substring(0, a.length() - 1);
        b = b  + "]}";
        System.out.println(b);
    }
    public void delete(){
        listaSuma.clear();
        listaValuta.clear();
        listaEmailuri.clear();
        listaCompanii.clear();

    }
}