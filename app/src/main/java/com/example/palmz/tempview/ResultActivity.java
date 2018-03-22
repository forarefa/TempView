package com.example.palmz.tempview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    CardView cardCelsius;
    CardView cardFahrenheit;
    CardView cardKelvin;

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

        cardCelsius = (CardView) findViewById(R.id.cardCelsius);
        cardFahrenheit = (CardView) findViewById(R.id.cardFahrenheit);
        cardKelvin = (CardView) findViewById(R.id.cardKelvin);

        tvCelsius.setText(celsius + " °C");
        tvFahrenheit.setText(fahrenheit + " °F");
        tvKelvin.setText(kelvin + " °K");
    }

    public void changeResult(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.chkCelsius:
                if (!checked)
                    cardCelsius.setVisibility(View.GONE);
                else
                    cardCelsius.setVisibility(View.VISIBLE);
                break;
            case R.id.chkFahrenheit:
                if (!checked)
                    cardFahrenheit.setVisibility(View.GONE);
                else
                    cardFahrenheit.setVisibility(View.VISIBLE);
                break;
            case R.id.chkKelvin:
                if (!checked)
                    cardKelvin.setVisibility(View.GONE);
                else
                    cardKelvin.setVisibility(View.VISIBLE);
                break;
        }

    }
}
