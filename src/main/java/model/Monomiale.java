package model;

public class Monomiale implements Comparable<Monomiale> {

    private double coef;
    private int deg;

    public Monomiale(double coef, int deg) {
        this.coef = coef;
        this.deg = deg;
    }

    public int getDeg() {
        return deg;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    @Override
    public int compareTo(Monomiale monomiale) {
        int compare = String.valueOf(this.deg).compareTo(String.valueOf(monomiale.deg));
        return compare * (-1);
    }
}
