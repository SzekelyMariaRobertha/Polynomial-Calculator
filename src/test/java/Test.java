import model.Model;
import model.Monomiale;
import model.Polynomial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;

class Test {

    public List<Monomiale> monomiales;
    public List<Monomiale> monomialess;
    public Polynomial polynomial = new Polynomial(null);
    public Polynomial polynomiall = new Polynomial(null);
    public Model model;

    @BeforeEach
    void setUp() {
        model = new Model();
        monomiales = new ArrayList<>();
        monomiales.add(new Monomiale(2, 2));
        monomiales.add(new Monomiale(-3, 1));
        monomiales.add(new Monomiale(1, 0));
        polynomial.setMonomiales(monomiales);

        monomialess = new ArrayList<>();
        monomialess.add(new Monomiale(5, 1));
        monomialess.add(new Monomiale(-1, 0));
        polynomiall.setMonomiales(monomialess);

    }

    @org.junit.jupiter.api.Test
    void multiply() {
        String s = "+10.00*x^3-17.00*x^2+8.00*x^1-1.00";
        model.multiply(this.polynomial, this.polynomiall);
        String res = model.getResult();
        assert(s.equals(res));
    }

    @org.junit.jupiter.api.Test
    void substract() {
        String s = "+2.00*x^2-8.00*x^1+2.00";
        model.substract(this.polynomial, this.polynomiall);
        String res = model.getResult();
        assert(s.equals(res));
    }

    @org.junit.jupiter.api.Test
    void derivate() {
        String s = "+4.00*x^1-3.00+0";
        model.derivate(this.polynomial);
        String res = model.getResult();
        assert(s.equals(res));
    }

    @org.junit.jupiter.api.Test
    void addd() {
        String s = "+2.00*x^2+2.00*x^1+0";
        model.addd(this.polynomial, this.polynomiall);
        String res = model.getResult();
        assert(s.equals(res));
    }

    @org.junit.jupiter.api.Test
    void integrate() {
        String s = "+0.67*x^3-1.50*x^2+1.00*x^1";
        model.integrate(this.polynomial);
        String res = model.getResult();
        assert(s.equals(res));
    }

    @AfterEach
    void tearDown() {
        polynomial = null;
        polynomiall = null;
        monomiales = null;
        monomialess = null;
        model = null;
    }
}