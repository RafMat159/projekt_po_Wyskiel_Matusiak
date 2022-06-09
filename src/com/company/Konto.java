package com.company;
/**
 * Klasa Konto posiada dane dotyczace konta okreslonego pracownika.
 * */
public class Konto {
    /**Zmienna przechowujaca imie wlasiciela konta*/
    private String imieW;
    /**Zmienna przechowujaca nazwisko wlasciciela konta*/
    private String nazwiskoW;
    /**Zmienna przechowujaca numer konta*/
    private int nrKonta;

    public Konto(String imieW, String nazwiskoW, int nrKonta) {
        this.imieW = imieW;
        this.nazwiskoW = nazwiskoW;
        this.nrKonta = nrKonta;
    }

    public String getImieW() {
        return imieW;
    }

    public String getNazwiskoW() {
        return nazwiskoW;
    }

    public int getNrKonta() {
        return nrKonta;
    }

}
