package com.company;

import java.util.Scanner;

/**
 * Obiekt <code>Pracownik</code> przechowuje informacje o pracowniku
 */
public class Pracownik extends Osoba implements MenuInterfejs{

    /**Zmienna przechowująca imie managera dla pracownika*/
    private final String imieManagera;
    /**Zmienna przechowująca nazwisko managera dla pracownika*/
    private final String nazwiskoManagera;

    public Pracownik(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                     double stawkaGodzinowa, double wysokoscPremii, String imie, String nazwisko, String status, String imieManagera, String nazwiskoManagera, Manager manager) {
        super(idPracownika, tygWyplataBrutto, tygWyplataNetto, liczbaPrzepracowanychGodzin, stawkaGodzinowa, wysokoscPremii, imie, nazwisko, status);
        this.imieManagera = imieManagera;
        this.nazwiskoManagera = nazwiskoManagera;
        manager.dodajPracownika(this); //dodanie pracownikow do danego managera
    }


    /**
     * Metoda zwracająca imie, nazwisko i id pracownika
     * @return ciąg znaków z imieniem, nazwiskiem i id pracownika
     */
    public String idImieNazwiskoPracownika(){
        return getImie() + " " + getNazwisko() + ", " + getIdPracownika();
    }

    /**
     * Metoda menu pozwala na uruchomienie odpowiednich funkcji wskazanych przez pracownika w trakcie działania programu
     * @param grafik istniejący grafik
     */
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
                sprawdzWyplate();
                break;
            case 3:
                sprawdzRanking(grafik);
                break;
            default:
                System.out.println("Nie mozesz wykonac tego dzialania.");
        }
    }

    /**
     * Metoda pozwalająca na wyświetlenie danych o wypłacie brutto i netto pracownika
     */
    private void sprawdzWyplate(){

        System.out.print("\nTygodniowa wyplata brutto wynosi: ");
        System.out.printf("%5.2f", getTygWyplataBrutto());
        System.out.print("\nTygodniowa wyplata netto wynosi: ");
        System.out.printf("%5.2f", getTygWyplataNetto());
        System.out.println();
    }

}