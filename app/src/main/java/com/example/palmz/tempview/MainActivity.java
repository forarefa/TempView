package com.example.palmz.tempview;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    String inputType = "Celsius";

    private  String [] mDrawerTitle = {"Home", "Contact"};
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView mListView;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                mDrawerLayout,
                R.string.open_drawer,
                R.string.close_drawer);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = findViewById(R.id.drawer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1,mDrawerTitle );
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) mListView.getItemAtPosition(position);

                mDrawerLayout.closeDrawers();
                Toast.makeText(getApplicationContext(),
                        "Go" + " to " + itemValue + " !!!!!", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        final EditText edtTemp = (EditText) findViewById(R.id.edtTemp);
        // final Spinner spnUnit = (Spinner) findViewById(R.id.spnUnit);
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        /*ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tempUnit));

        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnUnit.setAdapter(unitAdapter);*/

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTemp.getText().toString().trim().equals(""))
                    edtTemp.setError("ใส่อุณหภูมิที่ต้องการคำนวณ");
                else {
                    Temperature temp = new Temperature(Double.parseDouble(edtTemp.getText().toString()), inputType);
                    Intent i = new Intent(getApplicationContext(), ResultActivity.class);
                    i.putExtra("Celsius", String.valueOf(temp.getCelsius()));
                    i.putExtra("Fahrenheit", String.valueOf(temp.getFahrenheit()));
                    i.putExtra("Kelvin", String.valueOf(temp.getKelvin()));
                    startActivity(i);
                }
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        switch (item.getItemId()) {
            case R.id.mnuNew:
                Toast.makeText(this, "Sub Menu 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mnuOpen:
                Toast.makeText(this, "Sub Menu 2", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeInput(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rbtnCelsius:
                if (checked)
                    inputType = "Celsius";
                break;
            case R.id.rbtnFahrenheit:
                if (checked)
                    inputType = "Fahrenheit";
                break;
            case R.id.rbtnKelvin:
                if (checked)
                    inputType = "Kelvin";
                break;
        }
    }

    public void showNotification() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/palmz.haxker"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Temperature Calculator")
                        .setContentText("Click here to contact us!")
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);
    }

    public void showDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog, null);
        Button btnContact = (Button) mView.findViewById(R.id.btnContact);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });

    }

    public void contactUs(View view) {
        showDialog();
    }
}
