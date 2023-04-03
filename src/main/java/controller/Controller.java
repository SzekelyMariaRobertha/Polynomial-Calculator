package controller;

import model.Model;
import model.Polynomial;
import view.View;

import javax.swing.*;
import java.awt.event.*;

public class Controller {

    private Model model;
    private View view;
    private boolean ok;

    public Controller(Model m, View d) {
        model = m;
        view = d;

        d.addMultiplicateListener(new MultiplicateListener());
        d.addSubstractListener(new SubstractListener());
        d.addDivideListener(new DivideListener());
        d.addDerivateListener(new DerivateListener());
        d.addAddListener(new AddListener());
        d.addIntegrateListener(new IntegrateListener());
        d.addClearListener(new ClearListener());
        d.addMatrixListener(new AfisareListener());
        d.addClearOneListener(new ClearOneListener());
    }

    class MultiplicateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial input01 = view.getUserInput(1);
            Polynomial input02 = view.getUserInput(2);

            model.multiply(input01, input02);
            view.setResult(model.getResult());
        }
    }

    class SubstractListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial input01 = view.getUserInput(1);
            Polynomial input02 = view.getUserInput(2);

            model.substract(input01, input02);
            view.setResult(model.getResult());
        }
    }

    class DivideListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"This operation is not available");
        }
    }

    class DerivateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial input = view.getUserInput(1);
            model.derivate(input);
            view.setResult(model.getResult());
        }
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial input01 = view.getUserInput(1);
            Polynomial input02 = view.getUserInput(2);

            model.addd(input01, input02);
            view.setResult(model.getResult());
        }
    }

    class IntegrateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial input = view.getUserInput(1);
            model.integrate(input);
            view.setResult(model.getResult());
        }
    }

    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.reset();
            view.reset();
        }
    }

    class ClearOneListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!ok) {
                int l = view.getShow_pol01().getText().length();
                view.getShow_pol01().setText(view.getShow_pol01().getText().substring(0, l-1));
            }
            else {
                int l = view.getShow_pol02().getText().length();
                view.getShow_pol02().setText(view.getShow_pol02().getText().substring(0, l-1));
            }
        }
    }


    class AfisareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == (view.getMatrix())[2][5]) {
                ok = true;
            }
            if (e.getSource() == (view.getMatrix())[2][4]) {
                ok = false;
            }
            if (!ok) {
                for (int i = 0; i < view.getLines(); i++) {
                    for (int j = 0; j < view.getColumns(); j++) {
                        if (e.getActionCommand().equals((view.getMatrix())[i][j].getText())) {
                            view.getShow_pol01().setText(view.getShow_pol01().getText() + view.getMatrix()[i][j].getText());
                        }
                    }
                }
            } else {
                for (int i = 0; i < view.getLines(); i++) {
                    for (int j = 0; j < view.getColumns(); j++) {
                        if (e.getActionCommand().equals((view.getMatrix())[i][j].getText())) {
                            view.getShow_pol02().setText(view.getShow_pol02().getText() + view.getMatrix()[i][j].getText());
                        }
                    }
                }
            }
        }
    }
}
