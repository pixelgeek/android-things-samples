package com.example.upm.androidthings.driversamples;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.upm.androidthings.driverlibrary.BoardDefaults;

import mraa.mraa;

public class SpiDisplayActivity extends Activity {

    private static final String TAG = "SpiDisplayActivity";

    upm_ssd1351.SSD1351 display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spi_display);

        BoardDefaults bd = new BoardDefaults(this.getApplicationContext());
        int spiIndex = -1;

        switch (bd.getBoardVariant()) {
            case BoardDefaults.DEVICE_EDISON_ARDUINO:
                spiIndex = mraa.getSpiLookup(getString(R.string.Display_Edison_Arduino));
                break;
            case BoardDefaults.DEVICE_EDISON_SPARKFUN:
                spiIndex = mraa.getSpiLookup(getString(R.string.Display_Edison_Sparkfun));
                break;
            case BoardDefaults.DEVICE_JOULE_TUCHUCK:
                spiIndex = mraa.getSpiLookup(getString(R.string.Display_Joule_Tuchuck));
                break;
            default:
                throw new IllegalStateException("Unknown Board Variant: " + bd.getBoardVariant());
        }

        display = new upm_ssd1351.SSD1351(0,1,2);
        AsyncTask.execute(displayTask);

    }
}
