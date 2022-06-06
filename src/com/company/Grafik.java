package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Grafik {

    private static final int wiersze = 4; //3 - mozliwe zmiany w ciagu dnia + wiersz na dzien tygodnia
    private  static final int kolumny = 8; // 7 - liczba dni tygodnia + wiersz na godzine
    private String[][] tygodniowySzablon = new String[wiersze][kolumny];
    private Ranking ranking;

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

    class Ranking{          //klasa wewnetrzna
        private String[][] statystyki;
        public Ranking(int liczbaPracownikow) {
            statystyki = new String[liczbaPracownikow][3];
            statystyki[0][0] = "idPracownika";
            statystyki[0][1] = "liczba godzin";
            statystyki[0][2] = "kwota[PLN]";

        }

        private void uzupelnijRanking(List<Pracownik> listaPracownikow){
            int dlugosc = listaPracownikow.size()+1;
            for(int i=0; i <listaPracownikow.size();i++){
                statystyki[i+1][0] = Integer.toString(listaPracownikow.get(i).getIdPracownika());
                statystyki[i+1][1] = Double.toString(listaPracownikow.get(i).getLiczbaPrzepracowanychGodzin());
                statystyki[i+1][2] = Double.toString(listaPracownikow.get(i).getTygWyplataBrutto());
            }
            sortuj(dlugosc); //sortowanie rankingu
        }

        private void sortuj(int dlugosc){   //Bubble sort
            String zamianaId;
            String zamianaGodzin;
            String zamianaKwoty;
            for(int i = 0; i <dlugosc-1;++i){
                for (int j = 1;j<dlugosc-i-1;++j){
                    if(Double.parseDouble(statystyki[j+1][1]) > Double.parseDouble(statystyki[j][1])) {
                        zamianaId = statystyki[j][0];
                        zamianaGodzin = statystyki[j][1];
                        zamianaKwoty = statystyki[j][2];
                        statystyki[j][0] = statystyki[j+1][0];
                        statystyki[j][1] = statystyki[j+1][1];
                        statystyki[j][2] = statystyki[j+1][2];
                        statystyki[j+1][0] = zamianaId;
                        statystyki[j+1][1] = zamianaGodzin;
                        statystyki[j+1][2] = zamianaKwoty;

                    }
                }
            }
        }
        private void wyswietlRanking(){
            System.out.printf("%15s  %15s %15s",statystyki[0][0],statystyki[0][1],statystyki[0][2] + "\n");
            for(int i = 1; i < statystyki.length; i++){
                double tmp = Double.parseDouble(statystyki[i][2]);
                System.out.printf("%15s  %15s",statystyki[i][0],statystyki[i][1]);
                System.out.printf("%15.2f", tmp);
                System.out.println("");
            }
        }

        private void zaktualizujRanking(Pracownik pracownik){
            for(int i=1;i< statystyki.length;i++){
                if(statystyki[i][0].equals(Integer.toString(pracownik.getIdPracownika()))) {
                    statystyki[i][0] = Integer.toString(pracownik.getIdPracownika());
                    statystyki[i][1] = Double.toString(pracownik.getLiczbaPrzepracowanychGodzin());
                    statystyki[i][2] = Double.toString(pracownik.getTygWyplataBrutto());
                }
            }
            sortuj(statystyki.length);
        }

        private int pobierzIdPierwszegoMiejsca(){
            return Integer.parseInt(statystyki[1][0]);
        }

        public void zapiszDoPliku() throws FileNotFoundException {
            PrintWriter plik = new PrintWriter("grafik.txt");
            plik.println("GRAFIK TYGODNIOWY");
            for(int i = 0; i < wiersze; i++){
                plik.printf("%25s  %15s  %15s  %15s  %15s  %15s  %15s %15s",tygodniowySzablon[i][0],tygodniowySzablon[i][1],tygodniowySzablon[i][2],tygodniowySzablon[i][3],tygodniowySzablon[i][4],tygodniowySzablon[i][5],tygodniowySzablon[i][6],tygodniowySzablon[i][7] + "\n");
            }
            plik.println("RANKING");
            plik.printf("%15s  %15s %15s",statystyki[0][0],statystyki[0][1],statystyki[0][2] + "\n");
            for(int i = 1; i < statystyki.length; i++){
                double tmp = Double.parseDouble(statystyki[i][2]);
                plik.printf("%15s  %15s",statystyki[i][0],statystyki[i][1]);
                plik.printf("%15.2f", tmp);
                plik.println("");
            }
            plik.close();
        }
    }
    public void uzupelnijRanking(List<Pracownik> listaPracownikow){
        ranking = new Ranking((listaPracownikow.size()+1));
        ranking.uzupelnijRanking(listaPracownikow);
    }
    public void wyswietlRanking(){
        ranking.wyswietlRanking();
    }

    public int pobierzIdPierwszegoMiejsca(){
        return ranking.pobierzIdPierwszegoMiejsca();
    }

    public void zaktualizujRanking(Pracownik pracownik){
        ranking.zaktualizujRanking(pracownik);
    }

    public String[][] getTygodniowySzablon() {
        return tygodniowySzablon;
    }

    public void uzupelnijGrafik(int dzien, int zmiana, String id){
        tygodniowySzablon[zmiana][dzien] = id;
    }

    public void wyswietlGrafik(){
        for(int i = 0; i < wiersze; i++){
            System.out.printf("%25s  %15s  %15s  %15s  %15s  %15s  %15s %15s",tygodniowySzablon[i][0],tygodniowySzablon[i][1],tygodniowySzablon[i][2],tygodniowySzablon[i][3],tygodniowySzablon[i][4],tygodniowySzablon[i][5],tygodniowySzablon[i][6],tygodniowySzablon[i][7] + "\n");
        }
    }


    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public void zapiszDoPliku() throws FileNotFoundException {
        ranking.zapiszDoPliku();
    }

}