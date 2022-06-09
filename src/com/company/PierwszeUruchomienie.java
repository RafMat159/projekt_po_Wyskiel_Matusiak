package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PierwszeUruchomienie {
    private static final String MANAGER = "manager.xml";
    public static Grafik test1(){
        Grafik grafik = new Grafik();
        return grafik;
    }
    public static Manager test2(){
        Manager managerGrafiku = new Manager(1000, 0,0,0,23.50, 0, "Jan", "Kowalski", "pracownik");
        ObslugaXML.managerToXml(managerGrafiku, MANAGER);
        return managerGrafiku;
    }
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

    public static List<Osoba> zamien(Osoba managerGrafiku){
        List<Osoba> listaPracownikow1 = new ArrayList<>();
        List<Pracownik> listaPracownikow2 = managerGrafiku.getListaPracownikow();
        for(int i = 0; i < listaPracownikow2.size();i++){
            listaPracownikow1.add(listaPracownikow2.get(i));
        }
        return listaPracownikow1;
    }

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

    public static int funkcjaWyboru(Scanner in, int prawda){
        prawda = -1;
        boolean czyPoprawne = false;
        while(!czyPoprawne){
            try {
                prawda = Integer.parseInt(in.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Wybierz jedna z dwoch opcji");
                continue;
            }
            if(prawda > 1 || prawda < 0) {
                System.out.println("Wybierz jedna z dwoch opcji");
                prawda = -1;
            }
            czyPoprawne = prawda != -1;
        }
        return prawda;
    }

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
