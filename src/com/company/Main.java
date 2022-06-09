package com.company;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        /**Zmienna finalna przechowująca nazwę pliku xml dla grafiku*/
        final String NAZWA_PLIKU_GRAFIKU = "grafik.xml";
        /**Zmienna finalna przechowująca nazwę pliku xml dla managera*/
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
        PierwszeUruchomienie.przywrocenieDanych(grafik, listaPracownikow);

        int wybor = 1;
        int logowanie = 1;
        do {
            System.out.println("Podaj swoje ID");
            Scanner in = new Scanner(System.in);
            int id = -1;
            try {
                id = Integer.parseInt(in.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Niepoprawne dane! Podaj swoje ID");
            }
            if (id == 1000) {
                System.out.println("Manager, Jan Kowalski, id:1000");
                do {
                    managerGrafiku.menu(grafik);
                    System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
                    wybor = PierwszeUruchomienie.funkcjaWyboru(in,wybor);

                } while (wybor == 1);
            } else {
                boolean znaleziono = false;
                for (int i = 0; i < listaPracownikow.size(); i++) {
                    if (id == listaPracownikow.get(i).getIdPracownika()) {
                        znaleziono = true;
                        System.out.println("Pracownik, " + listaPracownikow.get(i).getImie() + " " + listaPracownikow.get(i).getNazwisko() + ", id:" + listaPracownikow.get(i).getIdPracownika());
                        do {
                            listaPracownikow.get(i).menu(grafik);
                            System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
                            wybor = PierwszeUruchomienie.funkcjaWyboru(in,wybor);

                        } while (wybor == 1);
                    }
                }
                if(!znaleziono){
                    System.out.println("Nie ma pracownika o podanym ID!");
                }
            }
            System.out.println("Czy chcesz się zalogowac? (1-tak, 0-nie)");
            logowanie = PierwszeUruchomienie.funkcjaWyboru(in,logowanie);
        } while (logowanie == 1);
    }

}