package com.company;

import java.util.Random;

public abstract class Osoba {
    private int idPracownika;
    private double tygWyplataBrutto;
    private double tygWyplataNetto;
    private double liczbaPrzepracowanychGodzin;
    private double stawkaGodzinowa;
    private String imie;
    private String nazwisko;
    private String status;
    private Konto konto; //KOMPOZYCJA

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

        Random r = new Random();
        konto  = new Konto(imie,nazwisko,r.nextInt(1000000000,Integer.MAX_VALUE));
    }

    @Override
    public String toString(){
        return "idPracownika: " + idPracownika + "\nimie: " + imie + "\nnazwisko: " + nazwisko + "status: " + status
                + "\ntygodniowa wyplata brutto: " + tygWyplataBrutto + "\ntygodniowa wyplata netto: "
                + tygWyplataNetto + "\nstawka godzinowa: " + stawkaGodzinowa + "\nliczba przepracowanych godzin" + liczbaPrzepracowanychGodzin;
    }

    public void SprawdzSwojGrafik(Grafik grafik){
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

    public void ObliczWyplate(){
        tygWyplataBrutto = liczbaPrzepracowanychGodzin*stawkaGodzinowa;
        if(status.equals("student"))
            tygWyplataNetto = tygWyplataBrutto;
        else
            tygWyplataNetto = tygWyplataBrutto*0.23;
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
}
