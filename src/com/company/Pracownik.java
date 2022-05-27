package com.company;

public class Pracownik extends Osoba {

    private String imieManagera;
    private String nazwiskoManagera;

    public Pracownik(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                     double stawkaGodzinowa, String imie, String nazwisko, String status, String imieManagera, String nazwiskoManagera) {
        super(idPracownika, tygWyplataBrutto, tygWyplataNetto, liczbaPrzepracowanychGodzin, stawkaGodzinowa, imie, nazwisko, status);
        this.imieManagera = imieManagera;
        this.nazwiskoManagera = nazwiskoManagera;
    }

    //Premia,SprawdzSwojGrafik,SprawdzWyplate,PodniesStawke
}
