package com.company;

public class Pracownik extends Osoba {

    private String imieManagera;
    private String nazwiskoManagera;

    public Pracownik(int idPracownika, double tygWyplataBrutto, double tygWyplataNetto, double liczbaPrzepracowanychGodzin,
                     double stawkaGodzinowa, String imie, String nazwisko, String status, String imieManagera, String nazwiskoManagera, Manager manager) {
        super(idPracownika, tygWyplataBrutto, tygWyplataNetto, liczbaPrzepracowanychGodzin, stawkaGodzinowa, imie, nazwisko, status);
        this.imieManagera = imieManagera;
        this.nazwiskoManagera = nazwiskoManagera;
        manager.dodajPracownika(imie,nazwisko,idPracownika); //dodanie pracownikow do danego managera
    }

    public void SprawdzWyplate(){
        ObliczWyplate();
        double wyplataTygB = getTygWyplataBrutto();
        double wyplataTygN = getTygWyplataNetto();
        System.out.println("Tygodniowa wyplata brutto wynosi: " + wyplataTygB + "\nTygodniowa wyplata netto wynosi: " + wyplataTygN);

    }
    //pasuje jakos ogarnac liczbe przepracowanych godzin danego pracownika, a dokladnie w jakim momencie ja zmieniac, czy w momencie wpisywania osoby do grafiku zmieniac
    //wartosc tej zmiennej i pozniej podczas modyfikacji grafiku przez managera aktualizowac te zmienna, czy w momencie sprawdzania przez pracownika swojego grafiku(chociaz
    // to jest chyba troche gorsze rozwiazanie)

    //Premia,SprawdzSwojGrafik - pracownik bedzie korzystal z funkcji ktora zostala napisana w klasie Osoba,SprawdzWyplate,PodniesStawke
}
