package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        final String NAZWA_PLIKU_GRAFIKU = "grafik.xml";
        final String NAZWA_PLIKU_MANAGERA = "manager.xml";
        Grafik grafik;
        Osoba managerGrafiku;
        List<Osoba> listaPracownikow;
//        grafik = PierwszeUruchomienie.test1();  //wywolywane przy pierwszym wywołaniu programu
//        managerGrafiku = PierwszeUruchomienie.test2();  //wywolywane przy pierwszym wywołaniu programu
//        listaPracownikow = PierwszeUruchomienie.test3(managerGrafiku);  //wywolywane przy pierwszym wywołaniu programu

        managerGrafiku = ObslugaXML.xmlToManager(NAZWA_PLIKU_MANAGERA); //wywolywane przy kolejnych uruchomieniach programu
        listaPracownikow = PierwszeUruchomienie.zamien(managerGrafiku);
        grafik = ObslugaXML.xmlToGrafik(NAZWA_PLIKU_GRAFIKU); //wywolywane przy kolejnych uruchomieniach programu

        int prawda;
        int logowanie;
        do {
            System.out.println("Podaj swoje ID");
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            if (id < 2000) {
                System.out.println("Manager, Jan Kowalski, id:1000");
                do {
                    managerGrafiku.menu(grafik);
                    System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
                    prawda = in.nextInt();
                } while (prawda == 1);
            } else {
                if (id == 2001) {
                    System.out.println("Pracownik, "+ listaPracownikow.get(0).getImie()+ " " + listaPracownikow.get(0).getNazwisko() + ", id:" + listaPracownikow.get(0).getIdPracownika());
                    do {
                        listaPracownikow.get(0).menu(grafik);
                        System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
                        prawda = in.nextInt();
                    } while (prawda == 1);
                } else if (id == 2002) {
                    System.out.println("Pracownik, "+ listaPracownikow.get(1).getImie()+ " " + listaPracownikow.get(1).getNazwisko() + ", id:" + listaPracownikow.get(1).getIdPracownika());
                    do {
                        listaPracownikow.get(1).menu(grafik);
                        System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
                        prawda = in.nextInt();
                    } while (prawda == 1);
                }
                else if (id == 2003) {
                    System.out.println("Pracownik, "+ listaPracownikow.get(2).getImie()+ " " +listaPracownikow.get(2).getNazwisko() + ", id:" + listaPracownikow.get(2).getIdPracownika());
                    do {
                        listaPracownikow.get(2).menu(grafik);
                        System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
                        prawda = in.nextInt();
                    } while (prawda == 1);
                }
                else if (id == 2004) {
                    System.out.println("Pracownik, "+ listaPracownikow.get(3).getImie()+ " " + listaPracownikow.get(3).getNazwisko() + ", id:" + listaPracownikow.get(3).getIdPracownika());
                    do {
                        listaPracownikow.get(3).menu(grafik);
                        System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
                        prawda = in.nextInt();
                    } while (prawda == 1);
                }
            }
            System.out.println("Czy chcesz się zalogowac? (1-tak, 0-nie)");
            logowanie = in.nextInt();
        } while (logowanie == 1);
    }

}