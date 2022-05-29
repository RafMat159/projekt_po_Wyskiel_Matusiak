package com.company;

public class Grafik {

    private String[][] tygodniowySzablon = new String[4][8]; //3 - mozliwe zmiany w ciagu dnia + wiersz na dzien tygodnia,
                                                            // 7 - liczba dni tygodnia + wiersz na godzine


    public Grafik() {
        tygodniowySzablon[0][0] = "zmiana/dzien tygodnia";
        tygodniowySzablon[0][1] = "poniedzialek";
        tygodniowySzablon[0][2] = "wtorek";
        tygodniowySzablon[0][3] = "sroda";
        tygodniowySzablon[0][4] = "czwartek";
        tygodniowySzablon[0][5] = "piatek";
        tygodniowySzablon[0][6] = "sobota";
        tygodniowySzablon[0][7] = "niedziela";
        tygodniowySzablon[1][0] = "6:00-14:00";
        tygodniowySzablon[2][0] = "14:00-22:00";
        tygodniowySzablon[3][0] = "22:00-6:00";
    }

    private class Ranking{          //klasa wewnetrzna
      private String[][] statystyki;
        public Ranking(int liczbaPracownikow) {
            statystyki = new String[liczbaPracownikow][3];
        }
    }

    public String[][] getTygodniowySzablon() {
        return tygodniowySzablon;
    }



    //wydrukujTransakcje

}
