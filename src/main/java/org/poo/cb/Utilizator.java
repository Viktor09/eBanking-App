package org.poo.cb;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Utilizator {
    private String email;
    private String nume;
    private String prenume;
    private String adresa;
    private static ArrayList<String> emailUsers = new ArrayList<String>();
    private static ArrayList<String> emailFriends = new ArrayList<String>();
    private static ArrayList<String> emailuri = new ArrayList<String>();
    private static ArrayList<String> adrese = new ArrayList<String>();
    private static ArrayList<String> numeLista = new ArrayList<String>();
    private static ArrayList<String> prenumeLista = new ArrayList<String>();
    private static Utilizator instance;
    private Utilizator() {
    }
    public static Utilizator getInstance(){
        if(instance == null){
            instance = new Utilizator();
        }
        return instance;
    }
    public void createUtilizator(String email, String nume, String prenume,
                      String adresa) {
        int ok = 0;
        try {
            ok = verificareExistentaUtilizator(email);
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        if(ok == 0){
            this.email = email;
            this.adresa = adresa;
            this.nume = nume;
            this.prenume = prenume;

            adaugarePrieteni(email, nume, prenume, adresa);
        }
    }

    private static int verificareExistentaUtilizator(String email) throws UserAlreadyExistsException {
        for (String i : emailuri) {
            if (i.contains(email)) {
                throw new UserAlreadyExistsException("User with " + email + " already exists");
            }
        }
        return 0;
    }

    private static void adaugarePrieteni(String email, String nume, String prenume, String adresa) {
        emailuri.add(email);
        adrese.add(adresa);
        numeLista.add(nume);
        prenumeLista.add(prenume);
    }

    public void adaugaPrienteni(String emailUser, String emailFriend) {
        try {
            try {
                if (verificare(emailUser, emailFriend)) return;
            } catch (FriendAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }

            if (!verificareInLista(emailUser)) return;

            if (!verificareInLista(emailFriend)) return;

            adaugareListe(emailUser, emailFriend);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            // Handle the case where a user doesn't exist
        }
    }

    private static boolean verificareInLista(String emailUser) throws UserNotFoundException {
        for (String storedEmail : emailuri) {
            if (storedEmail.contains(emailUser)) {
                return true; // User found in the list
            }
        }

        throw new UserNotFoundException("User with " + emailUser + " doesn't exist");
    }


    private static void adaugareListe(String emailUser, String emailFriend) {
        emailUsers.add(emailUser);
        emailFriends.add(emailFriend);
        emailUsers.add(emailFriend);
        emailFriends.add(emailUser);
    }

    private static boolean verificare(String emailUser, String emailFriend) throws FriendAlreadyExistsException {
        for (int i = 0; i < emailUsers.size(); i++) {
            if (emailUser.contains(emailUsers.get(i)) &&
                    emailFriend.contains(emailFriends.get(i))) {
                throw new FriendAlreadyExistsException("User with " + emailFriend + " is already a friend");
            }
        }
        return false;
    }

    public void afisare(String email){
        try {
            validitateAfisare(email);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        for(int i = 0; i < emailuri.size(); i++){
            if(emailuri.get(i).contains(email)){
                String intermediar = getIntermediar(i);
                String finala = getFinala(intermediar, i);
                System.out.println(finala);
                return;
            }
        }
    }

    @NotNull
    private static String getFinala(String intermediar, int i) {
        String finala = intermediar;
        finala = finala + "\"," + "\"friends\":" + "[";
        for(int j = 0 ; j < emailUsers.size(); j++){
            if(emailuri.get(i).contains(emailUsers.get(j))){
                finala = finala + "\"" +  emailFriends.get(j) + "\"";
            }
        }
        finala = finala + "]}";
        return finala;
    }

    @NotNull
    private static String getIntermediar(int i) {
        String intermediar = "{" +"\"email\":" +"\""+emailuri.get(i) + "\","
                + "\"firstname\":" +"\"" + numeLista.get(i) + "\","
                + "\"lastname\":" +"\""  + prenumeLista.get(i)+ "\","
                + "\"address\":" +"\"" + adrese.get(i);
        return intermediar;
    }

    private static void validitateAfisare(String email) throws UserNotFoundException {
        int ok = 0;
        for (int i = 0; i < emailuri.size(); i++) {
            if (emailuri.get(i).equals(email)) {
                ok = 1;
            }
        }

        if (ok == 0) {
            throw new UserNotFoundException("User with " + email + " doesn't exist");
        }
    }


    public void delete(){
        emailuri.clear();
        adrese.clear();
        numeLista.clear();
        prenumeLista.clear();
        emailUsers.clear();
        emailFriends.clear();

    }
    public int getSizePrieteni(){
        return emailUsers.size();
    }
    public String getEmail(int i){
        return emailUsers.get(i);
    }
    public String getEmailFriend(int i){
        return emailFriends.get(i);
    }

}
