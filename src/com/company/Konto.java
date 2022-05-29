package com.company;

public class Konto {

    private String imieW;
    private String nazwiskoW;
    private int nrKonta;

    public Konto(String imieW, String nazwiskoW, int nrKonta) {
        this.imieW = imieW;
        this.nazwiskoW = nazwiskoW;
        this.nrKonta = nrKonta;
    }

    public String getImieW() {
        return imieW;
    }

    public String getNazwiskoW() {
        return nazwiskoW;
    }

    public int getNrKonta() {
        return nrKonta;
    }

}
