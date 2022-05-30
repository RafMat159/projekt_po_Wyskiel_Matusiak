package com.company;

public class Main {

    public static void main(String[] args) {
        Grafik grafik = new Grafik();
        Manager managerGrafiku = new Manager(1000, 0,0,0,23.50, "Jan", "Kowalski", "pracownik", 4);
        Pracownik pracownik1 = new Pracownik(2001, 0, 0 , 0, 20, "Adam", "Nowak", "student", "Jan", "Kowalski");
        Pracownik pracownik2 = new Pracownik(2002, 0, 0 , 0, 19.70, "Aleksandra", "Ptak", "student", "Jan", "Kowalski");
        Pracownik pracownik3 = new Pracownik(2003, 0, 0 , 0, 20.70, "Katarzyna", "Kot", "pracownik", "Jan", "Kowalski");
        Pracownik pracownik4 = new Pracownik(2004, 0, 0 , 0, 20, "Filip", "Kosa", "pracownik", "Jan", "Kowalski");
        managerGrafiku.ustalGrafik(grafik);
        grafik.wyswietlGrafik();
        managerGrafiku.zmodyfikujGrafik(grafik);
        grafik.wyswietlGrafik();
    }
}
