package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Abstrakcyjna klasa Osoba po ktorej dziedzicza klasy Manager i Pracownik.
 * */
public abstract class Osoba implements MenuInterfejs{
    /**Zmienna przechowujaca id poszczegolnego pracownika*/
    private int idPracownika;
    /**Zmienna przechowujaca tygodniowa wyplate brutto danej osoby*/
    private double tygWyplataBrutto;
    /**Zmienna przechowujaca tygodniowa wyplate netto danej osoby*/
    private double tygWyplataNetto;
    /**Zmienna przechowujaca liczbe przepracowanych godzin danej osoby*/
    private double liczbaPrzepracowanychGodzin;
    /**Zmienna przechowujaca stawke godzinowa danej osoby*/
    private double stawkaGodzinowa;
    /**Zmienna przechowujaca wysokosc premii danej osoby*/
    private double wysokoscPremii;
    /**Zmienna przechowujaca imie danej osoby*/
    private String imie;
    /**Zmienna przechowujaca nazwisko danej osoby*/
    private String nazwisko;
    /**Zmienna przechowujaca status danej osoby (czy zwykly pracownik, czy student)*/
    private String status;
    /**Zmienna przechowujaca dane dotyczace konta danej osoby*/
    private Konto konto; //KOMPOZYCJA

    public Osoba(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                 double stawkaGodzinowa, double wysokoscPremii, String imie, String nazwisko, String status) {
        this.idPracownika = idPracownika;
        this.tygWyplataBrutto = tygWyplataBrutto;
        this.tygWyplataNetto = tygWyplataNetto;
        this.liczbaPrzepracowanychGodzin = liczbaPrzepracowanychGodzin;
        this.stawkaGodzinowa = stawkaGodzinowa;
        this.wysokoscPremii = wysokoscPremii;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.status = status;

        Random r = new Random();
        konto  = new Konto(imie,nazwisko,r.nextInt(1000000000,Integer.MAX_VALUE));
    }

    @Override
    public String toString(){
        return "idPracownika: " + idPracownika + "\nimie: " + imie + "\nnazwisko: " + nazwisko + "\nstatus: " + status
                + "\ntygodniowa wyplata brutto: " + tygWyplataBrutto + "\ntygodniowa wyplata netto: "
                + tygWyplataNetto + "\nstawka godzinowa: " + stawkaGodzinowa + "\nliczba przepracowanych godzin" + liczbaPrzepracowanychGodzin;
    }
    /**
     * Funkcja sluzy do sprawdzenia grafiku danej osoby.
     * @param grafik ustalany przez managera
     * */
    public void sprawdzGrafik(Grafik grafik){
        String[][] tabGrafik = grafik.getTygodniowySzablon();
        for(int i = 1; i < tabGrafik.length; i++){
            System.out.print(tabGrafik[i][0] + ": ");
            for (int j = 1;j<tabGrafik[i].length; j++){
                if(Integer.toString(idPracownika).equals(tabGrafik[i][j])){
                    System.out.print(tabGrafik[0][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public List<Pracownik> getListaPracownikow() {
        return new ArrayList<>() {
        };
    }

    public void sprawdzRanking(Grafik grafik){
        grafik.wyswietlRanking();
    }

    public int getIdPracownika() {
        return idPracownika;
    }

    public double getTygWyplataBrutto() {
        return tygWyplataBrutto;
    }

    public double getTygWyplataNetto() {
        return tygWyplataNetto;
    }

    public double getLiczbaPrzepracowanychGodzin() {
        return liczbaPrzepracowanychGodzin;
    }

    public double getStawkaGodzinowa() {
        return stawkaGodzinowa;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getStatus() {
        return status;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setTygWyplataBrutto(double tygWyplataBrutto) {
        this.tygWyplataBrutto = tygWyplataBrutto;
    }

    public void setTygWyplataNetto(double tygWyplataNetto) {
        this.tygWyplataNetto = tygWyplataNetto;
    }

    public void setLiczbaPrzepracowanychGodzin(double liczbaPrzepracowanychGodzin) {
        this.liczbaPrzepracowanychGodzin = liczbaPrzepracowanychGodzin;
    }

    public void setStawkaGodzinowa(double stawkaGodzinowa) {
        this.stawkaGodzinowa = stawkaGodzinowa;
    }

    public double getWysokoscPremii() {
        return wysokoscPremii;
    }

    public void setWysokoscPremii(double wysokoscPremii) {
        this.wysokoscPremii = wysokoscPremii;
    }
}