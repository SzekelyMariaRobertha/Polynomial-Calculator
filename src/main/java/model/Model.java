package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {

    public static final String INITIAL_VALUE = "0";

    private String result;

    public Model() {
        reset();
    }

    public void reset() {
        result = INITIAL_VALUE;
    }

    public void multiply(Polynomial p1, Polynomial p2) {
        List<Monomiale> monomialeP1 = p1.getMonomiales();
        List<Monomiale> monomialeP2 = p2.getMonomiales();
        List<Monomiale> monomialeRes = new ArrayList<>();

        for (Monomiale monomiale : monomialeP1) {
            for (Monomiale value : monomialeP2) {
                double coef = monomiale.getCoef() * value.getCoef();
                int grad = monomiale.getDeg() + value.getDeg();
                monomialeRes.add(new Monomiale(coef, grad));
            }
        }

        for (int i = 0; i < monomialeRes.size() - 1; i++) {
            for (int j = i + 1; j < monomialeRes.size(); j++) {
                if (monomialeRes.get(i).getDeg() == monomialeRes.get(j).getDeg()) {
                    monomialeRes.get(i).setCoef(monomialeRes.get(i).getCoef() + monomialeRes.get(j).getCoef());
                    monomialeRes.remove(j);
                }
            }
        }
        Polynomial res = new Polynomial(monomialeRes);
        Collections.sort(res.getMonomiales());
        result = res.toString();
    }

    public void substract(Polynomial p1, Polynomial p2) {
        List<Monomiale> monomialeList = new ArrayList<>();
        int i = 0;

        for (Monomiale m : p1.getMonomiales()) {
            int contor = 0;
            while (i < p2.getMonomiales().size()) {
                if (m.getDeg() == p2.getMonomiales().get(i).getDeg()) {
                    monomialeList.add(new Monomiale(m.getCoef() - p2.getMonomiales().get(i).getCoef(), m.getDeg()));
                    p2.getMonomiales().remove(i);
                    contor++;
                    i++;
                    break;
                }
                i++;
            }
            if (i == p2.getMonomiales().size()) {
                i = 0;
            }
            if (contor == 0) {
                monomialeList.add(new Monomiale(m.getCoef(), m.getDeg()));
            }
        }

        monomialeList.addAll(p2.getMonomiales());
        Polynomial res = new Polynomial(monomialeList);
        Collections.sort(res.getMonomiales());
        result = res.toString();
    }

    public void divide(Polynomial p1, Polynomial p2) {

    }

    public void derivate(Polynomial p) {
        List<Monomiale> monomialeList = new ArrayList<>();

        for (Monomiale m : p.getMonomiales()) {
            if (m.getDeg() == 1) {
                monomialeList.add(new Monomiale(m.getCoef(), 0));
            } else {
                int grad = m.getDeg() - 1;
                monomialeList.add(new Monomiale(m.getCoef() * m.getDeg(), grad));
            }
        }
        Polynomial res = new Polynomial(monomialeList);
        Collections.sort(res.getMonomiales());
        result = res.toString();
    }

    public void addd(Polynomial p1, Polynomial p2) {
        List<Monomiale> monomialeList = new ArrayList<>();
        int i = 0;

        for (Monomiale m : p1.getMonomiales()) {
            int contor = 0;
            while (i < p2.getMonomiales().size()) {
                if (m.getDeg() == p2.getMonomiales().get(i).getDeg()) {
                    monomialeList.add(new Monomiale(m.getCoef() + p2.getMonomiales().get(i).getCoef(), m.getDeg()));
                    p2.getMonomiales().remove(i);
                    contor++;
                    i++;
                    break;
                }
                i++;
            }
            if (i == p2.getMonomiales().size()) {
                i = 0;
            }
            if (contor == 0) {
                monomialeList.add(new Monomiale(m.getCoef(), m.getDeg()));
            }
        }

        monomialeList.addAll(p2.getMonomiales());
        Polynomial res = new Polynomial(monomialeList);
        Collections.sort(res.getMonomiales());
        result = res.toString();
    }

    public void integrate(Polynomial p) {
        List<Monomiale> monomialeList = new ArrayList<>();

        for (Monomiale m : p.getMonomiales()) {
            monomialeList.add(new Monomiale(m.getCoef() / (m.getDeg() + 1), m.getDeg() + 1));
        }
        Polynomial res = new Polynomial(monomialeList);
        Collections.sort(res.getMonomiales());
        result = res.toString();
    }

    public List<StringBuilder> getParts(JTextField show_pol) {
        List<StringBuilder> characters = new ArrayList<>();
        for (int i = 0; i < show_pol.getText().length(); i++) {
            characters.add(new StringBuilder("" + show_pol.getText().charAt(i)));
        }
        int i = 0;
        if (characters.get(0).charAt(0) != '-' && characters.get(0).charAt(0) != '+') {
            JOptionPane.showMessageDialog(null, "You didn't give a sign for the first term");
        } else {
            while (i < characters.size()) {
                boolean ok = false;
                int j = 0;
                if (characters.get(i).charAt(j) == '-' || characters.get(i).charAt(j) == '+') {
                    i++;
                    while ((characters.get(i + 1).charAt(j) >= '0' && characters.get(i + 1).charAt(j) <= '9') || characters.get(i + 1).charAt(j) == '.') {
                        characters.get(i).append(characters.get(i + 1));
                        characters.remove(characters.get(i + 1));
                    }
                    characters.get(i - 1).append(characters.get(i));
                    characters.remove(characters.get(i));
                    ok = true;
                }
                if (characters.get(i).charAt(j) == '*' || characters.get(i).charAt(j) == '^') {
                    characters.remove(characters.get(i));
                    ok = true;
                }
                if (!ok) {
                    i++;
                }
            }
        }
        return characters;
    }

    public String getResult() {
        return result.toString();
    }

    public void setResult(String result) {
        this.result = result;
    }
}
