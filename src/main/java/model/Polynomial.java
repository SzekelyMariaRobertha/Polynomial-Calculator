package model;

import java.util.List;

public class Polynomial {

    List<Monomiale> monomiales;

    public Polynomial(List<Monomiale> monomiales) {
        this.monomiales = monomiales;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Monomiale monomiale : monomiales) {
            if (monomiale.getCoef() == 0) {
                s.append("+0");
            } else if (monomiale.getDeg() != 0) {
                if (monomiale.getCoef() > 0) {
                    s.append("+").append(String.format("%.2f", monomiale.getCoef())).append("*x^").append(monomiale.getDeg());
                } else {
                    s.append(String.format("%.2f", monomiale.getCoef())).append("*x^").append(monomiale.getDeg());
                }
            } else {
                if (monomiale.getCoef() > 0) {
                    s.append("+").append(String.format("%.2f", monomiale.getCoef()));
                } else {

                    s.append(String.format("%.2f", monomiale.getCoef()));
                }
            }
        }
        return s.toString();
    }

    public List<Monomiale> getMonomiales() {
        return monomiales;
    }

    public void setMonomiales(List<Monomiale> monomiales) {
        this.monomiales = monomiales;
    }
}
