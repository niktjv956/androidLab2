package com.jv.battery.batteryinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar poziomNaladowaniaProgress;
    private TextView poziomNaladowania;
    private TextView stan;
    private TextView zrodłoZasilania;
    private TextView dostepnosc;
    private TextView poziom;
    private TextView status;
    private TextView technologia;
    private TextView temperatura;
    private TextView napiecie;

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("JV", "Battery Info - battery changed receiver");

            int batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int state = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
            int powerSource = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean availability = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
            int level = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int statusTmp = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            String technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);

            poziomNaladowaniaProgress.setProgress(batteryLevel);
            poziomNaladowania.setText("Poziom naładowania: " + batteryLevel);
            stan.setText("Stan: " + state);
            zrodłoZasilania.setText("Źródło zasilania: " + powerSource);
            dostepnosc.setText("Dostępność: " + availability);
            poziom.setText("Poziom: " + level);
            status.setText("Status: " + statusTmp);
            technologia.setText("Technologia: " + technology);
            temperatura.setText("Temperatura: " + temperature);
            napiecie.setText("Napięcie: " + voltage);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poziomNaladowaniaProgress = findViewById(R.id.batteryLevelProgress);
        poziomNaladowania = findViewById(R.id.poziomNaladowania);
        stan = findViewById(R.id.stan);
        zrodłoZasilania = findViewById(R.id.zrodloZasilania);
        dostepnosc = findViewById(R.id.dostepnosc);
        poziom = findViewById(R.id.poziom);
        status = findViewById(R.id.status);
        technologia = findViewById(R.id.technologia);
        temperatura = findViewById(R.id.temperatura);
        napiecie = findViewById(R.id.napiecie);

//        registerReceiver(batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(batteryInfoReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

}

// wylaczyc ladowanie i zmienic poziom baterii na 5, a potem na 0%
