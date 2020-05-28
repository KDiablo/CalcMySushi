package d.diablo.calcmysushi;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.math.BigDecimal;

@Root
public class Restaurant implements Serializable {


    @Element
    private String _name;
    @Element
    private int _color1 = -2763809;
    @Element
    private int _color2 = -2763809;
    @Element
    private int _color3 = -2763809;
    @Element
    private int _color4 = -2763809;
    @Element
    private int _color5 = -2763809;
    @Element
    private int _color6 = -2763809;
    @Element
    private double _price1 = 0.0;
    @Element
    private double _price2 = 0.0;
    @Element
    private double _price3 = 0.0;
    @Element
    private double _price4 = 0.0;
    @Element
    private double _price5 = 0.0;
    @Element
    private double _price6 = 0.0;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_color1() {
        return _color1;
    }

    public void set_color1(int _color1) {
        this._color1 = _color1;
    }

    public int get_color2() {
        return _color2;
    }

    public void set_color2(int _color2) {
        this._color2 = _color2;
    }

    public int get_color3() {
        return _color3;
    }

    public void set_color3(int _color3) {
        this._color3 = _color3;
    }

    public int get_color4() {
        return _color4;
    }

    public void set_color4(int _color4) {
        this._color4 = _color4;
    }

    public int get_color5() {
        return _color5;
    }

    public void set_color5(int _color5) {
        this._color5 = _color5;
    }

    public int get_color6() {
        return _color6;
    }

    public void set_color6(int _color6) {
        this._color6 = _color6;
    }

    public double get_price1() {
        return _price1;
    }

    public void set_price1(double _price1) {
        this._price1 = _price1;
    }

    public double get_price2() {
        return _price2;
    }

    public void set_price2(double _price2) {
        this._price2 = _price2;
    }

    public double get_price3() {
        return _price3;
    }

    public void set_price3(double _price3) {
        this._price3 = _price3;
    }

    public double get_price4() {
        return _price4;
    }

    public void set_price4(double _price4) {
        this._price4 = _price4;
    }

    public double get_price5() {
        return _price5;
    }

    public void set_price5(double _price5) {
        this._price5 = _price5;
    }

    public double get_price6() {
        return _price6;
    }

    public void set_price6(double _price6) {
        this._price6 = _price6;
    }
}
