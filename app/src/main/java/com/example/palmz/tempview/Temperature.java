package com.example.palmz.tempview;

/**
 * Created by MRPx on 16/2/2561.
 */

public class Temperature {

    private double celsius;

    public Temperature(double temp, String unit) {
       if (unit.equals("Fahrenheit"))
            this.celsius = (temp - 32) * 5 / 9;
       else if (unit.equals("Kelvin"))
            this.celsius = temp - 273.15;
       else // C
           this.celsius = temp;
    }

    public double getCelsius() {
        return Double.parseDouble(String.format("%.2f", this.celsius));
    }

    public double getFahrenheit() {
        return Double.parseDouble(String.format("%.2f", this.celsius * 1.8 + 32));
    }

    public double getKelvin() {
        return Double.parseDouble(String.format("%.2f", this.celsius + 273.15));
    }

}
