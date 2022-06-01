package com.company;

public class Pracownik extends Osoba {

    private String imieManagera;
    private String nazwiskoManagera;

    public Pracownik(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                     double stawkaGodzinowa, String imie, String nazwisko, String status, String imieManagera, String nazwiskoManagera, Manager manager) {
        super(idPracownika, tygWyplataBrutto, tygWyplataNetto, liczbaPrzepracowanychGodzin, stawkaGodzinowa, imie, nazwisko, status);
        this.imieManagera = imieManagera;
        this.nazwiskoManagera = nazwiskoManagera;
        manager.dodajPracownika(this); //dodanie pracownikow do danego managera
    }


    public String IdImieNazwiskoPracownika(){
        return getImie() + " " + getNazwisko() + ", " + getIdPracownika();
    }

    public void SprawdzWyplate(){
        ObliczWyplate();
        double wyplataTygB = getTygWyplataBrutto();
        double wyplataTygN = getTygWyplataNetto();
        System.out.println("Tygodniowa wyplata brutto wynosi: " + wyplataTygB + "\nTygodniowa wyplata netto wynosi: " + wyplataTygN);

    }

    //Premia,SprawdzSwojGrafik - pracownik bedzie korzystal z funkcji ktora zostala napisana w klasie Osoba,SprawdzWyplate,PodniesStawke
}
