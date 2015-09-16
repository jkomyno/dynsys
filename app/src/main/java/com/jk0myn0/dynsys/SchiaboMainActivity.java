package com.jk0myn0.dynsys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class SchiaboMainActivity extends Activity {

    Button calcBtn;
    EditText x0Txt;
    EditText rTxt;
    EditText xStartTxt;
    EditText xEndTxt;
    SeekBar precisionSeekBar;
    SeekBar precisionSeekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schiabo_main_activity);

        //seekBar

        precisionSeekBar = (SeekBar) findViewById(R.id.seekBar_precision);

        precisionSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                final Toast toastSeekBar = Toast.makeText(SchiaboMainActivity.this, "Precision: " + progressChanged, Toast.LENGTH_LONG);
                toastSeekBar.show();

                // trick to set toast duration
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toastSeekBar.cancel();
                    }
                }, 500);

            }
        });

        precisionSeekBar2 = (SeekBar) findViewById(R.id.seekBar_precision2);

        precisionSeekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                final Toast toastSeekBar = Toast.makeText(SchiaboMainActivity.this, "Precision: " + progressChanged, Toast.LENGTH_LONG);
                toastSeekBar.show();

                // trick to set toast duration
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toastSeekBar.cancel();
                    }
                }, 500);

            }
        });

        // creazione listener per calcBtn

        calcBtn = (Button) findViewById(R.id.btn_calc);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // parsing input da EditText a Double

                x0Txt = (EditText) findViewById(R.id.txt_x0);
                rTxt = (EditText) findViewById(R.id.txt_r);
                xStartTxt = (EditText) findViewById(R.id.txt_xStart);
                xEndTxt = (EditText) findViewById(R.id.txt_xEnd);

                final String x0Str = x0Txt.getText().toString();
                final String rStr = rTxt.getText().toString();
                final String xStartStr = xStartTxt.getText().toString();
                final String xEndStr = xEndTxt.getText().toString();

                final Toast toastCalc = Toast.makeText(SchiaboMainActivity.this, "Ok", Toast.LENGTH_LONG);
                toastCalc.show();

                // trick to set toast duration
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toastCalc.cancel();
                    }
                }, 500);

                Intent intent = new Intent(getApplicationContext(), GraficoEquazioneLogistica.class);

                Bundle bundle = new Bundle();
                bundle.putString("x0", x0Str);
                bundle.putString("r", rStr);
                bundle.putString("xStart", xStartStr);
                bundle.putString("xEnd", xEndStr);
                bundle.putInt("precision", precisionSeekBar.getProgress());
                bundle.putInt("precision2", precisionSeekBar2.getProgress());

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


    }
}