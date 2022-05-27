package com.company;

public class Osoba {
    private int idPracownika;
    private double tygWyplataBrutto;
    private double tygWyplataNetto;
    private double liczbaPrzepracowanychGodzin;
    private double stawkaGodzinowa;
    private String imie;
    private String nazwisko;
    private String status;

    public Osoba(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                 double stawkaGodzinowa, String imie, String nazwisko, String status) {
        this.idPracownika = idPracownika;
        this.tygWyplataBrutto = tygWyplataBrutto;
        this.tygWyplataNetto = tygWyplataNetto;
        this.liczbaPrzepracowanychGodzin = liczbaPrzepracowanychGodzin;
        this.stawkaGodzinowa = stawkaGodzinowa;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.status = status;
    }

    @Override
    public String toString(){
        return "idPracownika: " + idPracownika + "\nimie: " + imie + "\nnazwisko: " + nazwisko + "status: " + status
                + "\ntygodniowa wyplata brutto: " + tygWyplataBrutto + "\ntygodniowa wyplata netto: "
                + tygWyplataNetto + "\nstawka godzinowa: " + stawkaGodzinowa + "\nliczba przepracowanych godzin" + liczbaPrzepracowanychGodzin;
    }

    //void SprawdzSwojGrafik(){}

}
