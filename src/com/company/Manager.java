package com.company;

public class Manager extends Osoba {
    private int liczbaPracownikow;

    public Manager(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                   double stawkaGodzinowa, String imie, String nazwisko, String status, int liczbaPracownikow) {
        super(idPracownika, tygWyplataBrutto, tygWyplataNetto, liczbaPrzepracowanychGodzin, stawkaGodzinowa, imie, nazwisko, status);
        this.liczbaPracownikow = liczbaPracownikow;
    }

    //ustalGrafik,zmodyfikujGrafik,Premia,SprawdzSwojGrafik,SprawdzWyplate,PodniesStawke,WyplacWynagrodzenie
}
