package com.company;

public class Grafik {

    private String[][] tygodniowySzablon = new String[4][8]; //3 - mozliwe zmiany w ciagu dnia + wiersz na dzien tygodnia,
                                                            // 7 - liczba dni tygodnia + wiersz na godzine

    private class Ranking{          //klasa wewnetrzna
      private String[][] statystyki;
        public Ranking(int liczbaPracownikow) {
            statystyki = new String[liczbaPracownikow][3];
        }
    }

}
