package com.company;

import java.util.Scanner;

public class Pracownik extends Osoba implements MenuInterfejs{

    private final String imieManagera;
    private final String nazwiskoManagera;

    public Pracownik(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                     double stawkaGodzinowa, double wysokoscPremii, String imie, String nazwisko, String status, String imieManagera, String nazwiskoManagera, Manager manager) {
        super(idPracownika, tygWyplataBrutto, tygWyplataNetto, liczbaPrzepracowanychGodzin, stawkaGodzinowa, wysokoscPremii, imie, nazwisko, status);
        this.imieManagera = imieManagera;
        this.nazwiskoManagera = nazwiskoManagera;
        manager.dodajPracownika(this); //dodanie pracownikow do danego managera
    }


    public String idImieNazwiskoPracownika(){
        return getImie() + " " + getNazwisko() + ", " + getIdPracownika();
    }

    @Override
    public void menu(Grafik grafik) {
        System.out.println("Wybierz dzialanie:\n1.Sprawdz swoj grafik\n2.Sprawdz swoja wyplate\n3.WyswietlRanking");
        Scanner in = new Scanner(System.in);
        int wybor = -1;
        boolean czyPoprawne = false;
        while(!czyPoprawne){
            try {
                wybor = Integer.parseInt(in.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Niepoprawne dane! Wybierz dzialanie");
            }

            czyPoprawne = wybor != -1;
        }

        switch (wybor){
            case 1:
                sprawdzGrafik(grafik);
                break;
            case 2:
                sprawdzWyplate(grafik);
                break;
            case 3:
                sprawdzRanking(grafik);
                break;
            default:
                System.out.println("Nie mozesz wykonac tego dzialania.");
        }
    }

    public void sprawdzWyplate(Grafik grafik){
        double wyplata = grafik.pobierzDaneOWyplacie(getIdPracownika());
        setTygWyplataBrutto(wyplata);
        System.out.print("\nTygodniowa wyplata brutto wynosi: ");
        System.out.printf("%5.2f", getTygWyplataBrutto());
        if(getStatus().equals("student"))
            setTygWyplataNetto(wyplata);
        else
            setTygWyplataNetto(wyplata - (wyplata * 0.23));
        System.out.print("\nTygodniowa wyplata netto wynosi: ");
        System.out.printf("%5.2f", getTygWyplataNetto());
        System.out.println();
    }

}