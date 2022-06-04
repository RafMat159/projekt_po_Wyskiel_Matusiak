package com.company;

import java.util.Scanner;

public class Pracownik extends Osoba implements MenuInterfejs{

    private String imieManagera;
    private String nazwiskoManagera;

    public Pracownik(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                     double stawkaGodzinowa, String imie, String nazwisko, String status, String imieManagera, String nazwiskoManagera, Manager manager) {
        super(idPracownika, tygWyplataBrutto, tygWyplataNetto, liczbaPrzepracowanychGodzin, stawkaGodzinowa, imie, nazwisko, status);
        this.imieManagera = imieManagera;
        this.nazwiskoManagera = nazwiskoManagera;
        manager.dodajPracownika(this); //dodanie pracownikow do danego managera
    }


    public String idImieNazwiskoPracownika(){
        return getImie() + " " + getNazwisko() + ", " + getIdPracownika();
    }

    @Override
    public void menu(Grafik grafik){
        System.out.println("Wybierz dzialanie:\n1. Sprawdz swoj grafik\n2. Sprawdz swoja wyplate");
        Scanner in = new Scanner(System.in);
        int dzialanie = in.nextInt();
        switch (dzialanie){
            case 1:
                sprawdzGrafik(grafik);
                break;
            case 2:
                sprawdzWyplate();
                break;
            default:
                System.out.println("Nie mozesz wykonac tego dzialania");
        }
    }

    public void sprawdzWyplate(){
        double wyplataTygB = Math.round(getTygWyplataBrutto());
        double wyplataTygN = Math.round(getTygWyplataNetto());
        System.out.println("Tygodniowa wyplata brutto wynosi: " + wyplataTygB + "\nTygodniowa wyplata netto wynosi: " + wyplataTygN);

    }

}