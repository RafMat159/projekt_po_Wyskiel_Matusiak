package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager extends Osoba {
    private List<String> listaPracownikow = new ArrayList<>();

    public Manager(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                   double stawkaGodzinowa, String imie, String nazwisko, String status) {
        super(idPracownika, tygWyplataBrutto, tygWyplataNetto, liczbaPrzepracowanychGodzin, stawkaGodzinowa, imie, nazwisko, status);
    }

    public void dodajPracownika(String imie, String nazwisko, int idPodwladnego){
        listaPracownikow.add(imie + " " + nazwisko + ", " + idPodwladnego);
    }

    public void ustalGrafik(Grafik grafik){
        Scanner in = new Scanner(System.in);
        System.out.println("Pracownicy: ");//Wyswietlenie listy pracownikow
        for(int i = 0; i < listaPracownikow.size(); i++){
            System.out.println(listaPracownikow.get(i));
        }
        for(int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 3; j++){
                System.out.print("Wybierz pracownika dla zmiany: ");
                if(i == 1) System.out.print("poniedzialek, ");
                if(i == 2) System.out.print("wtorek, ");
                if(i == 3) System.out.print("sroda, ");
                if(i == 4) System.out.print("czwartek, ");
                if(i == 5) System.out.print("piatek, ");
                if(i == 6) System.out.print("sobota, ");
                if(i == 7) System.out.print("niedziela, ");

                if(j == 1) System.out.println("6.00-14.00");
                if(j == 2) System.out.println("14.00-22.00");
                if(j == 3) System.out.println("22.00-6.00");

                int id = in.nextInt();

                if(id < 2000 || id > (2000+listaPracownikow.size())) {
                    System.out.println("Nie istnieje pracownik o takim ID!");
                    j--;
                }
                else
                    grafik.uzupelnijGrafik(i, j, Integer.toString(id));
            }
        }
    }

    public void zmodyfikujGrafik(Grafik grafik){
        System.out.println("Wybierz dzień do edycji:\n1.Poniedzialek\n2.Wtorek\n3.Sroda\n4.Czwartek\n5.Piatek\n6.Sosbota\n7.Niedziela");
        Scanner in = new Scanner(System.in);
        int dzien = in.nextInt();
        System.out.println("Wybierz zmianę:\n1.6.00-14.00\n2.14.00-22.00\n3.22.00-6.00");
        int zmiana = in.nextInt();
        System.out.println("Wybierz pracownika");
        for(int i = 0; i < listaPracownikow.size(); i++){
            System.out.println(listaPracownikow.get(i));
        }
        int id = in.nextInt();
        boolean prawda = true;
        while(prawda) {
            if (id < 2000 || id > (2000 + listaPracownikow.size())) {
                System.out.println("Nie istnieje pracownik o takim ID!\nWybierz pracownika o poprawnym id:");
                id = in.nextInt();
            }
            else
                prawda = false;
        }
        grafik.uzupelnijGrafik(dzien, zmiana, Integer.toString(id));
    }

    @Override
    public void SprawdzSwojGrafik(Grafik grafik){
        grafik.wyswietlGrafik();    //manager ma dostep do calego grafiku danego tygodnia
    }

    //ustalGrafik,zmodyfikujGrafik,Premia,SprawdzSwojGrafik,SprawdzWyplate,PodniesStawke,WyplacWynagrodzenie
}