package com.example.palmz.tempview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle result = getIntent().getExtras();
        String celsius = result.getString("Celsius");
        String fahrenheit = result.getString("Fahrenheit");
        String kelvin = result.getString("Kelvin");

        TextView tvCelsius = (TextView) findViewById(R.id.tvCelsius);
        TextView tvFahrenheit = (TextView) findViewById(R.id.tvFahrenheit);
        TextView tvKelvin = (TextView) findViewById(R.id.tvKelvin);

        tvCelsius.setText(celsius + " °C");
        tvFahrenheit.setText(fahrenheit + " °F");
        tvKelvin.setText(kelvin + " °K");
    }
}
