package com.company;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Klasa Manager umozliwia tworzenie obiektow, ktore zarzadzaja przypisywaniem danych pracownikow do grafiku oraz pozwala na przyznawanie wynagrodzenia poszczegolnym pracownikom.
 * */
public class Manager extends Osoba implements MenuInterfejs, Serializable {
    private final String NAZWA_PLIKU_GRAFIKU = "grafik.xml";
    private final List<Pracownik> listaPracownikow = new ArrayList<>(); //KOMPOZYCJA
    public Manager(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                   double stawkaGodzinowa, double wysokoscPremii, String imie, String nazwisko, String status) {
        super(idPracownika, tygWyplataBrutto, tygWyplataNetto, liczbaPrzepracowanychGodzin, stawkaGodzinowa, wysokoscPremii, imie, nazwisko, status);
    }

    public void dodajPracownika(Pracownik pracownik){
        listaPracownikow.add(pracownik);
    }

    @Override
    public void menu(Grafik grafik) throws FileNotFoundException {
        System.out.println("Wybierz dzialanie:\n1.Ustal Grafik\n2.Zmodyfikuj grafik\n3.Sprawdz grafik\n4.Wyplac wynagrodzenie\n5.Podnies stawke\n6.Premia\n7.Wyswietl Ranking\n8.Zapisz grafik do pliku");
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

        switch(wybor){
            case 1:
                ustalGrafik(grafik);
                ObslugaXML.grafikToXml(grafik, NAZWA_PLIKU_GRAFIKU);
                break;
            case 2:
                if(PierwszeUruchomienie.czyToPierwszeUruchomienie(grafik))
                    System.out.println("Grafik nie jest jeszcze ustalony, aby zmodyfikowac grafik nalezy go najpierw utworzyc.");
                else{
                zmodyfikujGrafik(grafik);
                ObslugaXML.grafikToXml(grafik, NAZWA_PLIKU_GRAFIKU);
            }
                break;
            case 3:
                sprawdzGrafik(grafik);
                break;
            case 4:
                DayOfWeek dzienTygodnia = DayOfWeek.from(LocalDate.now());
                wyplacWynagrodzenie(dzienTygodnia.getValue());
                break;
            case 5:
                System.out.println("Wybierz pracownika ktoremu chcesz podniesc stawke:");
                for(int i = 0; i < listaPracownikow.size(); i++){
                    System.out.println(listaPracownikow.get(i).idImieNazwiskoPracownika());
                }
                int id = -1;
                czyPoprawne = false;
                while(!czyPoprawne){
                    try {
                        id = Integer.parseInt(in.nextLine());
                    }
                    catch(NumberFormatException e){
                        System.out.println("Niepoprawne dane! Podaj ID pracownika");
                    }

                    czyPoprawne = id != -1;
                }

                if (id <= 2000 || id > (2000 + listaPracownikow.size())) {
                    System.out.println("Nie istnieje pracownik o takim ID!\nWybierz pracownika o poprawnym id:");
                    id = -1;
                    czyPoprawne = false;
                    while(!czyPoprawne){
                        try {
                            id = Integer.parseInt(in.nextLine());
                        }
                        catch(NumberFormatException e){
                            System.out.println("Niepoprawne dane! Podaj ID pracownika");
                        }

                        czyPoprawne = id != -1;
                    }
                }
                int index = znajdzId(id);
                podniesStawke(listaPracownikow.get(index), grafik);
                ObslugaXML.grafikToXml(grafik, NAZWA_PLIKU_GRAFIKU);
                break;
            case 6:
                if(PierwszeUruchomienie.czyToPierwszeUruchomienie(grafik))
                    System.out.println("Grafik nie jest jeszcze ustalony, aby nadac premie pracownikowi nalezy najpierw utworzyc grafik.");
                else {
                    int id2 = grafik.pobierzIdPierwszegoMiejsca();
                    int index2 = znajdzId(id2);
                    premia(listaPracownikow.get(index2));
                }
                break;
            case 7:
                if(PierwszeUruchomienie.czyToPierwszeUruchomienie(grafik))
                    System.out.println("Grafik nie jest jeszcze ustalony, aby wyswietlic ranking nalezy najpierw utowrzyc grafik.");
                else {
                    sprawdzRanking(grafik);
                }
                break;
            case 8:
                if(PierwszeUruchomienie.czyToPierwszeUruchomienie(grafik))
                    System.out.println("Grafik nie jest jeszcze ustalony, aby zapisac grafik do pliku nalezy najpierw utowrzyc grafik.");
                else {
                    grafik.zapiszDoPliku();
                }
                break;
            default:
                System.out.println("Nie mozesz wykonac tego dzialania.");

        }
    }

    private void ustalGrafik(Grafik grafik){
        Scanner in = new Scanner(System.in);
        System.out.println("Pracownicy: ");//Wyswietlenie listy pracownikow
        for(int i = 0; i < listaPracownikow.size(); i++){
            System.out.println(listaPracownikow.get(i).idImieNazwiskoPracownika());
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

                int id = -1;
                boolean czyPoprawne = false;
                while(!czyPoprawne){
                    try {
                        id = Integer.parseInt(in.nextLine());
                    }
                    catch(NumberFormatException e){
                        System.out.println("Niepoprawne dane! Podaj ID pracownika");
                    }

                    czyPoprawne = id != -1;
                }


                if(id <= 2000 || id > (2000+listaPracownikow.size())) {
                    System.out.println("Nie istnieje pracownik o takim ID!");
                    j--;
                }
                else {
                    grafik.uzupelnijGrafik(i, j, Integer.toString(id));
                    int index = znajdzId(id);
                    listaPracownikow.get(index).setLiczbaPrzepracowanychGodzin(listaPracownikow.get(index).getLiczbaPrzepracowanychGodzin()+8.0); //zwiekszanie liczby przepracowanych godzin
                    obliczWyplate(listaPracownikow.get(index));
                }
            }
        }

        try {
            grafik.uzupelnijRanking(listaPracownikow);//uzupelnienie rankingu
        }
        catch(Exception e){
            System.out.println("Nie udalo sie uzupelnic ranking!");
        }
    }

    private void zmodyfikujGrafik(Grafik grafik){
        System.out.println("Wybierz dzień do edycji:\n1.Poniedzialek\n2.Wtorek\n3.Sroda\n4.Czwartek\n5.Piatek\n6.Sobota\n7.Niedziela");
        Scanner in = new Scanner(System.in);
        int dzien = -1;
        boolean czyPoprawne = false;
        while(!czyPoprawne){
            try {
                dzien = Integer.parseInt(in.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Wybierz poprawny dzien");
                continue;
            }
            if(dzien < 1 || dzien > 7) {
                System.out.println("Wybierz poprawny dzien");
                dzien = -1;
            }

            czyPoprawne = dzien != -1;
        }


        System.out.println("Wybierz zmianę:\n1.6.00-14.00\n2.14.00-22.00\n3.22.00-6.00");
        int zmiana = -1;
        czyPoprawne = false;
        while(!czyPoprawne){
            try {
                zmiana = Integer.parseInt(in.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Wybierz poprawną zmiane");
                continue;
            }
            if(zmiana < 1 || zmiana > 3) {
                System.out.println("Wybierz poprawną zmiane");
                zmiana = -1;
            }

            czyPoprawne = zmiana != -1;
        }

        System.out.println("Wybierz pracownika");
        for(int i = 0; i < listaPracownikow.size(); i++){
            System.out.println(listaPracownikow.get(i).idImieNazwiskoPracownika());
        }

        int id = -1;
        czyPoprawne = false;
        while(!czyPoprawne){
            try {
                id = Integer.parseInt(in.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Niepoprawne dane! Podaj ID pracownika");
            }

            czyPoprawne = id != -1;
        }

        boolean prawda = true;
        while(prawda) {
            if (id < 2000 || id > (2000 + listaPracownikow.size())) {
                System.out.println("Nie istnieje pracownik o takim ID!\nWybierz pracownika o poprawnym id:");
                id = -1;
                czyPoprawne = false;
                while(!czyPoprawne){
                    try {
                        id = Integer.parseInt(in.nextLine());
                    }
                    catch(NumberFormatException e){
                        System.out.println("Niepoprawne dane! Podaj ID pracownika");
                    }

                    czyPoprawne = id != -1;
                }
            }
            else {
                prawda = false;
                int index = znajdzId(id);
                String[][] tabGrafik = grafik.getTygodniowySzablon();
                int indexStarego = znajdzId(Integer.parseInt(tabGrafik[zmiana][dzien]));
                listaPracownikow.get(indexStarego).setLiczbaPrzepracowanychGodzin(listaPracownikow.get(indexStarego).getLiczbaPrzepracowanychGodzin()-8.0); //zmniejszenie liczby przepracowanych godzin
                obliczWyplate(listaPracownikow.get(indexStarego));
                grafik.zaktualizujRanking(listaPracownikow.get(indexStarego));
                listaPracownikow.get(index).setLiczbaPrzepracowanychGodzin(listaPracownikow.get(index).getLiczbaPrzepracowanychGodzin()+8.0); //zwiekszanie liczby przepracowanych godzin
                obliczWyplate(listaPracownikow.get(index));
                grafik.zaktualizujRanking(listaPracownikow.get(index));
            }
        }
        grafik.uzupelnijGrafik(dzien, zmiana, Integer.toString(id));
    }

    private int znajdzId(int idPracownika){ //odszukanie idPracownika dla ktorego zmieniona zostanie liczba przepracowanych godzin
        int i = 0;
        while(idPracownika != listaPracownikow.get(i).getIdPracownika()){
            i++;
        }
        return i;
    }

    @Override
    public void sprawdzGrafik(Grafik grafik){
        grafik.wyswietlGrafik();    //manager ma dostep do calego grafiku danego tygodnia
    }


    private void wyplacWynagrodzenie(int czyNiedziela){
        if(czyNiedziela == 7){
            for(int i=0;i<listaPracownikow.size();i++){
                System.out.println("Wyplata wynagrodzenia:\nimie: " + listaPracownikow.get(i).getImie() + "\nnazwisko: "+ listaPracownikow.get(i).getNazwisko() + "\nnrKonta:" + listaPracownikow.get(i).getKonto().getNrKonta());
                if(listaPracownikow.get(i).getStatus().equals("student"))
                    System.out.println("kwota: " + listaPracownikow.get(i).getTygWyplataBrutto());
                else
                    System.out.println("kwota: " + listaPracownikow.get(i).getTygWyplataNetto());
            }
        }else
            System.out.println("Wyplata nie mozliwa do zrealizowania, do dnia wyplaty pozostalo " + (7 - czyNiedziela) + " dni.");
    }

    private void podniesStawke(Pracownik p1, Grafik grafik){
        System.out.println("O ile chcesz podeniesc stawke godzinowa: ");
        Scanner in = new Scanner(System.in);
        double stawka = -1;
        boolean czyPoprawne = false;
        while(!czyPoprawne){
            try {
                stawka = Double.parseDouble(in.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Niepoprawne dane! Podaj wartosc liczbowa");
            }

            czyPoprawne = stawka != -1;
        }

        p1.setStawkaGodzinowa(p1.getStawkaGodzinowa() + stawka);
        obliczWyplate(p1);
        grafik.zaktualizujRanking(p1);
    }

    private void premia(Pracownik p1){
        System.out.println("Podaj wysokosc premii: ");
        Scanner in = new Scanner(System.in);
        double kwota = -1;
        boolean czyPoprawne = false;
        while(!czyPoprawne){
            try {
                kwota = Double.parseDouble(in.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Niepoprawne dane! Podaj wartosc liczbowa");
            }

            czyPoprawne = kwota != -1;
        }

        p1.setWysokoscPremii(kwota);
        obliczWyplate(p1);
    }

    private void obliczWyplate(Pracownik p1){
        p1.setTygWyplataBrutto(p1.getLiczbaPrzepracowanychGodzin()*p1.getStawkaGodzinowa() + p1.getWysokoscPremii());
        if(p1.getStatus().equals("student"))
            p1.setTygWyplataNetto(p1.getTygWyplataBrutto());
        else
            p1.setTygWyplataNetto(p1.getTygWyplataBrutto() - p1.getTygWyplataBrutto()*0.23);
    }

    public List<Pracownik> getListaPracownikow() {
        return listaPracownikow;
    }

}