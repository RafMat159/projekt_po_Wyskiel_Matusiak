package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Grafik grafik = new Grafik();
        Manager managerGrafiku = new Manager(1000, 0,0,0,23.50, 0, "Jan", "Kowalski", "pracownik");
        Pracownik pracownik1 = new Pracownik(2001, 0, 0 , 0, 20, 0,"Adam", "Nowak", "student", "Jan", "Kowalski",managerGrafiku);
        Pracownik pracownik2 = new Pracownik(2002, 0, 0 , 0, 19.70, 0,"Aleksandra", "Ptak", "student", "Jan", "Kowalski",managerGrafiku);
        Pracownik pracownik3 = new Pracownik(2003, 0, 0 , 0, 20.70, 0,"Katarzyna", "Kot", "pracownik", "Jan", "Kowalski",managerGrafiku);
        Pracownik pracownik4 = new Pracownik(2004, 0, 0 , 0, 20, 0,"Filip", "Kosa", "pracownik", "Jan", "Kowalski",managerGrafiku);
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
                    System.out.println("Pracownik, "+ pracownik1.getImie()+ " " + pracownik1.getNazwisko() + ", id:" + pracownik1.getIdPracownika());
                    do {
                        pracownik1.menu(grafik);
                        System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
                        prawda = in.nextInt();
                    } while (prawda == 1);
                } else if (id == 2002) {
                    System.out.println("Pracownik, "+ pracownik2.getImie()+ " " + pracownik2.getNazwisko() + ", id:" + pracownik2.getIdPracownika());
                    do {
                        pracownik2.menu(grafik);
                        System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
                        prawda = in.nextInt();
                    } while (prawda == 1);
                }
                else if (id == 2003) {
                    System.out.println("Pracownik, "+ pracownik2.getImie()+ " " + pracownik2.getNazwisko() + ", id:" + pracownik2.getIdPracownika());
                    do {
                        pracownik3.menu(grafik);
                        System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
                        prawda = in.nextInt();
                    } while (prawda == 1);
                }
                else if (id == 2004) {
                    System.out.println("Pracownik, "+ pracownik2.getImie()+ " " + pracownik2.getNazwisko() + ", id:" + pracownik2.getIdPracownika());
                    do {
                        pracownik4.menu(grafik);
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