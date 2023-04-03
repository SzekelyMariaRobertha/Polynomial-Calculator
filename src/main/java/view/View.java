package view;

import model.Model;
import model.Monomiale;
import model.Polynomial;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel title = new JLabel("~Polynomial calculator~");
    private JLabel pol01 = new JLabel("First polynomial:");
    private JTextField show_pol01 = new JTextField();
    private JLabel pol02 = new JLabel("Second polynomial:");
    private JTextField show_pol02 = new JTextField();
    private JLabel result = new JLabel("Result:");
    private JTextField show_result = new JTextField();
    private JButton multiplication = new JButton("Multiply");
    private JButton substraction = new JButton("Substract");
    private JButton division = new JButton("Divide");
    private JButton derivation = new JButton("Derivate");
    private JButton addition = new JButton("Add");
    private JButton integration = new JButton("Integrate");
    private JButton clearOne = new JButton("CE");
    private int lines = 3;
    private int columns = 6;
    private JButton[][] matrix = new JButton[lines][columns];
    private ArrayList<String> arr = new ArrayList<>(Arrays.asList("1", "2", "3", "0", "+", "-", "4", "5", "6", "*", "^", ".", "7", "8", "9", "x", "C", ""));
    private Model model;

    public View(Model m) {
        JOptionPane.showMessageDialog(null, "RULES\n\nFor an appropriate use of this polynomial calculator, DO NOT FORGET to follow the following rules:\n" +
                "1. For EACH monomial of the polynomial, the coefficient (INT/DOUBLE) (accompanied by its sign) and its degree (INT) will be explicitly given\n" +
                "2. Correctly write the FORMAT of the polynomial [ (sign)coefficient*x^degree(sign)coefficient... ]\n" +
                "3. For DERIVATION and INTEGRATION, be careful that the polynomial is listed in the FIRST box\n");
        model = m;
        model.setResult(Model.INITIAL_VALUE);

        show_result.setText(model.getResult());
        show_result.setEditable(false);
        //show_pol01.setEditable(false);
        //show_pol02.setEditable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 80, 350, 530);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setFocusable(true);
        contentPane.setRequestFocusEnabled(true);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(153, 255, 153));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.grabFocus();

        title.setForeground(new Color(0, 51, 0));
        title.setFont(new Font("Impact", Font.PLAIN, 30));
        title.setBounds(20, 10, 400, 30);
        contentPane.add(title);

        pol01.setForeground(new Color(0, 51, 0));
        pol01.setFont(new Font("Impact", Font.PLAIN, 18));
        pol01.setBounds(20, 60, 400, 30);
        contentPane.add(pol01);

        show_pol01.setForeground(Color.LIGHT_GRAY);
        show_pol01.setFont(new Font("Impact", Font.PLAIN, 18));
        show_pol01.setBounds(20, 90, 300, 30);
        contentPane.add(show_pol01);

        pol02.setForeground(new Color(0, 51, 0));
        pol02.setFont(new Font("Impact", Font.PLAIN, 18));
        pol02.setBounds(20, 120, 400, 30);
        contentPane.add(pol02);

        show_pol02.setForeground(Color.LIGHT_GRAY);
        show_pol02.setFont(new Font("Impact", Font.PLAIN, 18));
        show_pol02.setBounds(20, 150, 300, 30);
        contentPane.add(show_pol02);

        result.setForeground(new Color(0, 51, 0));
        result.setFont(new Font("Impact", Font.PLAIN, 18));
        result.setBounds(20, 180, 400, 30);
        contentPane.add(result);

        show_result.setForeground(Color.LIGHT_GRAY);
        show_result.setFont(new Font("Impact", Font.PLAIN, 15));
        show_result.setBounds(20, 210, 300, 30);
        contentPane.add(show_result);

        multiplication.setFont(new Font("Impact", Font.PLAIN, 20));
        multiplication.setBackground(new Color(204, 204, 255));
        multiplication.setBounds(20, 250, 150, 30);
        contentPane.add(multiplication);

        substraction.setFont(new Font("Impact", Font.PLAIN, 20));
        substraction.setBackground(new Color(204, 204, 255));
        substraction.setBounds(170, 250, 150, 30);
        contentPane.add(substraction);

        division.setFont(new Font("Impact", Font.PLAIN, 20));
        division.setBackground(new Color(204, 204, 255));
        division.setBounds(20, 280, 150, 30);
        contentPane.add(division);

        derivation.setFont(new Font("Impact", Font.PLAIN, 20));
        derivation.setBackground(new Color(204, 204, 255));
        derivation.setBounds(170, 280, 150, 30);
        contentPane.add(derivation);

        addition.setFont(new Font("Impact", Font.PLAIN, 20));
        addition.setBackground(new Color(204, 204, 255));
        addition.setBounds(20, 310, 150, 30);
        contentPane.add(addition);

        integration.setFont(new Font("Impact", Font.PLAIN, 20));
        integration.setBackground(new Color(204, 204, 255));
        integration.setBounds(170, 310, 150, 30);
        contentPane.add(integration);

        clearOne.setFont(new Font("Impact", Font.PLAIN, 20));
        clearOne.setBackground(new Color(204, 204, 255));
        clearOne.setBounds(20, 455, 55, 30);
        contentPane.add(clearOne);

        int k = 0;
        int y = 350;
        for (int i = 0; i < 3; i++) {
            int x = 20;
            for (int j = 0; j < 6; j++) {
                JButton button = new JButton(arr.get(k));
                button.setFont(new Font("Impact", Font.PLAIN, 20));
                button.setBackground(new Color(204, 204, 255));
                button.setBounds(x, y, 45, 30);
                matrix[i][j] = button;
                contentPane.add(button);
                x += 50;
                k++;
            }
            y += 35;
        }
    }

    public Polynomial getUserInput(int i) {
        List<StringBuilder> parts;
        if (i == 1) {
            parts = model.getParts(show_pol01);
        } else {
            parts = model.getParts(show_pol02);
        }
        List<Monomiale> monomiales = new ArrayList<>();
        Polynomial polynomial = new Polynomial(monomiales);

        for (int j = 0; j < parts.size(); ) {
            monomiales.add(new Monomiale(Double.parseDouble("" + parts.get(j)), Integer.parseInt("" + parts.get(j + 2))));
            j += 3;
        }

        polynomial.setMonomiales(monomiales);
        return polynomial;
    }

    public void addMultiplicateListener(ActionListener a) {
        multiplication.addActionListener(a);
    }

    public void addSubstractListener(ActionListener a) {
        substraction.addActionListener(a);
    }

    public void addDivideListener(ActionListener a) {
        division.addActionListener(a);
    }

    public void addDerivateListener(ActionListener a) {
        derivation.addActionListener(a);
    }

    public void addAddListener(ActionListener a) {
        addition.addActionListener(a);
    }

    public void addIntegrateListener(ActionListener a) {
        integration.addActionListener(a);
    }

    public void addClearListener(ActionListener a) {
        matrix[2][4].addActionListener(a);
    }

    public void addClearOneListener(ActionListener a ) { clearOne.addActionListener(a);}

    public void addMatrixListener(ActionListener a) {
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j].addActionListener(a);
            }
        }
    }

    public void reset() {
        show_result.setText(Model.INITIAL_VALUE);
        show_pol01.setText(null);
        show_pol02.setText(null);
    }

    public void setResult(String result) {
        show_result.setText(result);
    }

    public JButton[][] getMatrix() {
        return matrix;
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

    public JTextField getShow_pol01() {
        return show_pol01;
    }

    public JTextField getShow_pol02() {
        return show_pol02;
    }
}
