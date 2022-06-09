package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Obiekt <code>PierwszeUruchomienie</code> odpowiada za inicjalizację obiektów z ustawieniami początkowymi
 */
public class PierwszeUruchomienie {
    /**Zmienna finalna przechowująca nazwę pliku xml*/
    private static final String MANAGER = "manager.xml";

    /**
     * Metoda tworząca referencję do obiektu grafiku
     * @return grafik
     */
    public static Grafik test1(){
        Grafik grafik = new Grafik();
        return grafik;
    }

    /**
     * Metoda tworząca managera i zapisująca go do pliku XML
     * @return manager grafiku
     */
    public static Manager test2(){
        Manager managerGrafiku = new Manager(1000, 0,0,0,23.50, 0, "Jan", "Kowalski", "pracownik");
        ObslugaXML.managerToXml(managerGrafiku, MANAGER);
        return managerGrafiku;
    }

    /**
     * Metoda tworząca pracowników i zapisująca ich do pliku XML
     * @param managerGrafiku referencja do managera
     * @return lista zawierająca utworzonych pracowników
     */
    public static List<Osoba> test3(Osoba managerGrafiku){
        List<Osoba> listaPracownikow = new ArrayList<>();
        if(managerGrafiku instanceof Manager) {
            Osoba pracownik1 = new Pracownik(2001, 0, 0, 0, 20, 0, "Adam", "Nowak", "student", "Jan", "Kowalski", (Manager) managerGrafiku);//upcasting
            listaPracownikow.add((Pracownik) pracownik1);
            Osoba pracownik2 = new Pracownik(2002, 0, 0, 0, 19.70, 0, "Aleksandra", "Ptak", "student", "Jan", "Kowalski", (Manager) managerGrafiku);//upcasting
            listaPracownikow.add((Pracownik) pracownik2);
            Osoba pracownik3 = new Pracownik(2003, 0, 0, 0, 20.70, 0, "Katarzyna", "Kot", "pracownik", "Jan", "Kowalski", (Manager) managerGrafiku);//upcasting
            listaPracownikow.add((Pracownik) pracownik3);
            Osoba pracownik4 = new Pracownik(2004, 0, 0, 0, 20, 0, "Filip", "Kosa", "pracownik", "Jan", "Kowalski", (Manager) managerGrafiku);//upcasting
            listaPracownikow.add((Pracownik) pracownik4);
            ObslugaXML.managerToXml((Manager) managerGrafiku, MANAGER);
        }
        return listaPracownikow;
    }

    /**
     * Metoda odpowiedzialna za zamianę listy obiektów Pracownik na listę obiektów Osoba
     * @param managerGrafiku referencja do managera
     * @return lista pracownikow
     */
    public static List<Osoba> zamien(Osoba managerGrafiku){
        List<Osoba> listaPracownikow1 = new ArrayList<>();
        List<Pracownik> listaPracownikow2 = managerGrafiku.getListaPracownikow();
        for(int i = 0; i < listaPracownikow2.size();i++){
            listaPracownikow1.add(listaPracownikow2.get(i));
        }
        return listaPracownikow1;
    }

    /**
     * Metoda sprawdzająca czy program jest uruchomiony po raz pierwszy. Sprawdza czy grafik został utworzony.
     * @param grafik G
     * @return true - grafik jest już utworzony, kolejne uruchomienie programu, false - grafik nie jest utworzony, pierwsze uruchomienie programu
     */
    public static boolean czyToPierwszeUruchomienie(Grafik grafik){
        String[][] tabGrafik = grafik.getTygodniowySzablon();
        for (int i=0; i < tabGrafik.length; i++){
            for(int j=0; j < tabGrafik[i].length;j++){
                if(tabGrafik[i][j] == null)
                    return true;
            }
        }
        return false;
    }

    /**
     * Metoda sprawdzajaca poprawnosc dla funkcji wyboru miedzy dwiema ustalonymi opcjami
     * @param in wejscie do wprowadzania danych z konsoli
     * @param wybor wartosc liczbowa odpowiadajaca wyborowi
     * @return poprawny wybor
     */
    public static int funkcjaWyboru(Scanner in, int wybor){
        wybor = -1;
        boolean czyPoprawne = false;
        while(!czyPoprawne){
            try {
                wybor = Integer.parseInt(in.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Wybierz jedna z dwoch opcji");
                continue;
            }
            if(wybor > 1 || wybor < 0) {
                System.out.println("Wybierz jedna z dwoch opcji");
                wybor = -1;
            }
            czyPoprawne = wybor != -1;
        }
        return wybor;
    }

    /**
     * Metoda przywracająca wartosci ilosci przepracownych godzin, wyplaty brutto i stawki godzinowej dla pracownikow
     * @param grafik ustalony grafik
     * @param listaPracownikow lista pracowników, dla których należy przywrócić ustawienia
     */
    public static void przywrocenieDanych(Grafik grafik, List<Osoba> listaPracownikow){
        double godziny = 0;
        double wyplata = 0;
        double stawka = 0;
        for(int i = 0; i < listaPracownikow.size(); i++){
            godziny = grafik.pobierzDaneOGodzinach(listaPracownikow.get(i).getIdPracownika());
            stawka = grafik.pobierzDaneOStawce(listaPracownikow.get(i).getIdPracownika());
            wyplata = grafik.pobierzDaneOWyplacie(listaPracownikow.get(i).getIdPracownika());
            listaPracownikow.get(i).setLiczbaPrzepracowanychGodzin(godziny);
            listaPracownikow.get(i).setStawkaGodzinowa(stawka);
            listaPracownikow.get(i).setTygWyplataBrutto(wyplata);

            if(listaPracownikow.get(i).getStatus().equals("student"))
                listaPracownikow.get(i).setTygWyplataNetto(wyplata);
            else
                listaPracownikow.get(i).setTygWyplataNetto(wyplata - (wyplata * 0.23));
        }
    }


}