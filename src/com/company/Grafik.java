package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

/**
 * Obiekt <code>Grafik</code> okresla grafik i jego modyfikacje
 */
public class Grafik implements Serializable {

    /**Zmienna statyczna zawierajaca informacje o mozliwych zmianach w ciagu dnia i dodatkowy wiersz na dzien tygodnia*/
    private static final int wiersze = 4;
    /**Zmienna statyczna zawierajaca informacje o ilości dni tygodnia i dodatkowy wiersz na dzien godziny*/
    private  static final int kolumny = 8;
    /**Tablica typu String przechowująca szablon tygodnia dla grafiku*/
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

    /**
     * Obiekt <code>Ranking</code> okresla ranking dla grafiku. Klasa Ranking jako klasa wewnętrzna.
     */
    private class Ranking{
        /**Tablica typu String przechowująca statystyyki dotyczące rankingu*/
        private String[][] statystyki;
        public Ranking(int liczbaPracownikow) {
            statystyki = new String[liczbaPracownikow][4];
            statystyki[0][0] = "idPracownika";
            statystyki[0][1] = "liczba godzin";
            statystyki[0][2] = "kwota[PLN]";
            statystyki[0][3] = "stawka";
        }

        /**
         * Metoda pozwalajaca uzupelnic ranking
         * @param listaPracownikow lista pracowników dla których ma zostać uzupelniony ranking
         */
        private void uzupelnijRanking(List<Pracownik> listaPracownikow){
            int dlugosc = listaPracownikow.size()+1;
            for(int i=0; i <listaPracownikow.size();i++){
                statystyki[i+1][0] = Integer.toString(listaPracownikow.get(i).getIdPracownika());
                statystyki[i+1][1] = Double.toString(listaPracownikow.get(i).getLiczbaPrzepracowanychGodzin());
                statystyki[i+1][2] = Double.toString(listaPracownikow.get(i).getTygWyplataBrutto());
                statystyki[i+1][3] = Double.toString(listaPracownikow.get(i).getStawkaGodzinowa());
            }
            sortuj(dlugosc); //sortowanie rankingu
        }

        /**
         * Metoda sortujaca tablice statystyk rankingu wedlug ilosci przepracowanych godzin
         * @param dlugosc ilosc pracownikow
         */
        private void sortuj(int dlugosc){   //Bubble sort
            String zamianaId;
            String zamianaGodzin;
            String zamianaKwoty;
            String zmianaStawki;
            for(int i = 0; i <dlugosc-1;++i){
                for (int j = 1;j<dlugosc-i-1;++j){
                    if(Double.parseDouble(statystyki[j+1][1]) > Double.parseDouble(statystyki[j][1])) {
                        zamianaId = statystyki[j][0];
                        zamianaGodzin = statystyki[j][1];
                        zamianaKwoty = statystyki[j][2];
                        zmianaStawki = statystyki[j][3];
                        statystyki[j][0] = statystyki[j+1][0];
                        statystyki[j][1] = statystyki[j+1][1];
                        statystyki[j][2] = statystyki[j+1][2];
                        statystyki[j][3] = statystyki[j+1][3];
                        statystyki[j+1][0] = zamianaId;
                        statystyki[j+1][1] = zamianaGodzin;
                        statystyki[j+1][2] = zamianaKwoty;
                        statystyki[j+1][3] = zmianaStawki;
                    }
                }
            }
        }

        /**
         * Metoda wyswietlajaca ranking w konsoli
         */
        private void wyswietlRanking(){
            System.out.printf("%15s  %15s %15s",statystyki[0][0],statystyki[0][1],statystyki[0][2] + "\n");
            for(int i = 1; i < statystyki.length; i++){
                double tmp = Double.parseDouble(statystyki[i][2]);
                System.out.printf("%15s  %15s",statystyki[i][0],statystyki[i][1]);
                System.out.printf("%15.2f", tmp);
                System.out.println("");
            }
        }

        /**
         * Metoda pozwalajaca na aktualizacje danych rankingu
         * @param pracownik pracownik, dla którego zaistniały zmiany
         */
        private void zaktualizujRanking(Pracownik pracownik){
            for(int i=1;i< statystyki.length;i++){
                if(statystyki[i][0].equals(Integer.toString(pracownik.getIdPracownika()))) {
                    statystyki[i][0] = Integer.toString(pracownik.getIdPracownika());
                    statystyki[i][1] = Double.toString(pracownik.getLiczbaPrzepracowanychGodzin());
                    statystyki[i][2] = Double.toString(pracownik.getTygWyplataBrutto());
                    statystyki[i][3] = Double.toString(pracownik.getStawkaGodzinowa());
                }
            }
            sortuj(statystyki.length);
        }

        /**
         * Metoda zwracajaca id pracownika, który jest na pierwszym miejscu w rankingu
         * @return id pracownika
         */
        private int pobierzIdPierwszegoMiejsca(){
            return Integer.parseInt(statystyki[1][0]);
        }

        /**
         * Metoda zapisujaca grafik i ranking fo pliku tekstowego txt
         * @throws FileNotFoundException
         */
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

        /**
         * Metoda zwracajaca ilosc godzin przepracowanych godzin przez pracownika o podanym id
         * @param id id pracownika dla ktorego chcemy pobrac informacje o przepracowanych godzinach
         * @return ilosc przepracowanych godzin
         */
        private double pobierzDaneOGodzinach(int id){
            double wartosc = 0;
            for(int i = 0; i < statystyki.length; i++ ){
                if(Integer.toString(id).equals(statystyki[i][0])){
                    wartosc = Double.parseDouble(statystyki[i][1]);
                    break;
                }
            }
            return wartosc;
        }

        /**
         * Metoda zwracajaca wysokosc wyplaty brutto dla pracownika o podanym id
         * @param id id pracownika dla ktorego chcemy pobrac informacje o wyplacie brutto
         * @return wysokosc wyplaty brutto
         */
        private double pobierzDaneOWyplacie(int id){
            double wartosc = 0;
            for(int i = 0; i < statystyki.length; i++ ){
                if(Integer.toString(id).equals(statystyki[i][0]))
                    wartosc = Double.parseDouble(statystyki[i][2]);
            }
            return wartosc;
        }

        /**
         * Metoda zwracajaca wysokosc stawki godzinowej dla pracownika o podanym id
         * @param id id pracownika dla ktorego chcemy pobrac informacje o wysokosci stawki godzinowej
         * @return wysokosc stawki godzinowej
         */
        private double pobierzDaneOStawce(int id){
            double wartosc = 0;
            for(int i = 0; i < statystyki.length; i++ ){
                if(Integer.toString(id).equals(statystyki[i][0]))
                    wartosc = Double.parseDouble(statystyki[i][3]);
            }
            return wartosc;
        }
    }

    /**
     * Metoda uzupelniajaca ranking dla listy pracownikow
     * @param listaPracownikow lista pracownikow dla ktorych ma zostac uzupelniony ranking
     */
    public void uzupelnijRanking(List<Pracownik> listaPracownikow){
        ranking = new Ranking((listaPracownikow.size()+1));
        ranking.uzupelnijRanking(listaPracownikow);
    }

    /**
     * Metoda pozwalająca na wyswietlenie rankingu
     */
    public void wyswietlRanking(){
        ranking.wyswietlRanking();
    }

    /**
     * Metoda pozwalajaca na pobranie id pracownika zajmujacego pierwsze miejsce w rankingu
     * @return id pracownika
     */
    public int pobierzIdPierwszegoMiejsca(){
        return ranking.pobierzIdPierwszegoMiejsca();
    }

    /**
     * Metoda pozwalajaca na zaktualizowanie rankingu
     * @param pracownik pracownik dla którego zaistniały zmiany
     */
    public void zaktualizujRanking(Pracownik pracownik){
        ranking.zaktualizujRanking(pracownik);
    }

    /**
     * Metoda zwracajaca szablon dla grafiku tygodniowego
     * @return szablon grafiku
     */
    public String[][] getTygodniowySzablon() {
        return tygodniowySzablon;
    }

    /**
     * Metoda uzupelniajaca grafik w danej komórce
     * @param dzien dzien, dla którego uzupelniamy grafik - pozycja kolumny
     * @param zmiana zmiana, dla ktorej uzupelniamy grafik - pozycja wiersza
     * @param id id pracownika, którego wpisujemy na daną zmianę w konkretnym dniu
     */
    public void uzupelnijGrafik(int dzien, int zmiana, String id){
        tygodniowySzablon[zmiana][dzien] = id;
    }

    /**
     * Metoda pozwalająca na wyświetlenie grafiku
     */
    public void wyswietlGrafik(){
        for(int i = 0; i < wiersze; i++){
            System.out.printf("%25s  %15s  %15s  %15s  %15s  %15s  %15s %15s",tygodniowySzablon[i][0],tygodniowySzablon[i][1],tygodniowySzablon[i][2],tygodniowySzablon[i][3],tygodniowySzablon[i][4],tygodniowySzablon[i][5],tygodniowySzablon[i][6],tygodniowySzablon[i][7] + "\n");
        }
    }

    /**
     * Metoda zwracająca ranking
     * @return ranking
     */
    public Ranking getRanking() {
        return ranking;
    }

    /**
     * Metoda ustalajaca ranking
     * @param ranking ranking, który zostanie przypisany do referencji
     */
    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    /**
     * Metoda powalająca na zapis grafiku i rankingu do pliku tekstowego txt
     * @throws FileNotFoundException
     */
    public void zapiszDoPliku() throws FileNotFoundException {
        ranking.zapiszDoPliku();
    }

    /**
     * Metoda pozwalajaca na zwrocenie ilosci godzin przepracowanych godzin przez pracownika o podanym id
     * @param id id pracownika dla ktorego chcemy pobrac informacje o przepracowanych godzinach
     * @return ilosc przepracowanych godzin
     */
    public double pobierzDaneOGodzinach(int id){
        return ranking.pobierzDaneOGodzinach(id);
    }

    /**
     * Metoda pozwalajaca na zwrocenie wysokosci wyplaty brutto dla pracownika o podanym id
     * @param id id pracownika dla ktorego chcemy pobrac informacje o wyplacie brutto
     * @return wysokosc wyplaty brutto
     */
    public double pobierzDaneOWyplacie(int id){
        return ranking.pobierzDaneOWyplacie(id);
    }

    /**
     * Metoda pozwalajaca na zwrocenie wysokosci stawki godzinowej dla pracownika o podanym id
     * @param id id pracownika dla ktorego chcemy pobrac informacje o wysokosci stawki godzinowej
     * @return wysokosc stawki godzinowej
     */
    public double pobierzDaneOStawce(int id){
        return ranking.pobierzDaneOStawce(id);
    }
}