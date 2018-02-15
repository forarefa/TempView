package com.example.palmz.tempview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edtTemp = (EditText) findViewById(R.id.edtTemp);
        final Spinner spnUnit = (Spinner) findViewById(R.id.spnUnit);
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        final TextView tvResult = (TextView) findViewById(R.id.tvResult);

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tempUnit));

        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnUnit.setAdapter(unitAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                if (edtTemp.getText().toString().trim().equals(""))
                    edtTemp.setError("ใส่อุณหภูมิที่ต้องการคำนวณ");
                else {
                    Temperature temp = new Temperature(Double.parseDouble(edtTemp.getText().toString()), spnUnit.getSelectedItem().toString());
                    tvResult.setText("Result:\n" + temp.getCelsius() + " Celsius\n" + temp.getFahrenheit() + " Fahrenheit\n" + temp.getKelvin() + " Kelvin");
                }
            }

        });


    }

}
