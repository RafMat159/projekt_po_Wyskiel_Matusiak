package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Grafik grafik = new Grafik();
        Manager managerGrafiku = new Manager(1000, 0,0,0,23.50, "Jan", "Kowalski", "pracownik");
        Pracownik pracownik1 = new Pracownik(2001, 0, 0 , 0, 20, "Adam", "Nowak", "student", "Jan", "Kowalski",managerGrafiku);
        Pracownik pracownik2 = new Pracownik(2002, 0, 0 , 0, 19.70, "Aleksandra", "Ptak", "student", "Jan", "Kowalski",managerGrafiku);
        Pracownik pracownik3 = new Pracownik(2003, 0, 0 , 0, 20.70, "Katarzyna", "Kot", "pracownik", "Jan", "Kowalski",managerGrafiku);
        Pracownik pracownik4 = new Pracownik(2004, 0, 0 , 0, 20, "Filip", "Kosa", "pracownik", "Jan", "Kowalski",managerGrafiku);
        int prawda;
        System.out.println("Manager, Jan Kowalski, id:1000");
        do{
            managerGrafiku.menu(grafik);
            System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
            Scanner in = new Scanner(System.in);
            prawda = in.nextInt();
        }while(prawda == 1);
        System.out.println("Pracownik, Adam Nowak, id:2001");
        do{
            pracownik1.menu(grafik);
            System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
            Scanner in = new Scanner(System.in);
            prawda = in.nextInt();
        }while(prawda == 1);
        System.out.println("Pracownik, Aleksandra Ptak, 2002");
        do{
            pracownik2.menu(grafik);
            System.out.println("Czy chcesz kontynuowac? (1-tak, 0-nie)");
            Scanner in = new Scanner(System.in);
            prawda = in.nextInt();
        }while(prawda == 1);

//        System.out.println("Podaj kod pracownika");
//        Scanner in = new Scanner(System.in);
//        int kod = in.nextInt();
//        if(kod >=1000 && kod <2000)
//        managerGrafiku.ustalGrafik(grafik);
//        grafik.wyswietlGrafik();
//        managerGrafiku.zmodyfikujGrafik(grafik);
//        grafik.wyswietlGrafik();
//        System.out.println("Sprawdzenie grafiku przez pracownika: Adam Nowak, id:2001 ");
//        pracownik1.sprawdzGrafik(grafik);
//        System.out.println("Sprawdzenie wyplaty przez pracownika: Adam Nowak, id:2001 ");
//        pracownik1.sprawdzWyplate();
//        managerGrafiku.wyplacWynagrodzenie(7);
    }
}
