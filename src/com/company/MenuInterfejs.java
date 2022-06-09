package com.company;

import java.io.FileNotFoundException;
/**
 * Interfejs MenuInterfejs udostępniający funkcje menu.
 * */
public interface MenuInterfejs {
    /**
     * Funkcja umozliwajaca wyswietlenie menu danej osoby
     * @param grafik grafik ustalany przez managera
     * */
    void menu(Grafik grafik) throws FileNotFoundException;
}