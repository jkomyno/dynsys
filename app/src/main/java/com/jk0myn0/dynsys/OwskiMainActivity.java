package com.jk0myn0.dynsys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OwskiMainActivity extends Activity {

    private Button calcBtn;
    private EditText funzione;
    private EditText xFunzione;
    private EditText firstIteration;
    private EditText nIterations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owski_main_activity);

        // creazione listener per calcBtn

        calcBtn = (Button) findViewById(R.id.btn_calc2);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // parsing input da EditText a Double

                funzione = (EditText) findViewById(R.id.funzione2Txt);
                xFunzione = (EditText) findViewById(R.id.xFunzione2Txt);
                firstIteration = (EditText) findViewById(R.id.firstIterationTxt);
                nIterations = (EditText) findViewById(R.id.nIterations2Txt);

                String funzioneStr = funzione.getText().toString();
                String xFunzioneStr = xFunzione.getText().toString();
                String firstIterationStr = firstIteration.getText().toString();
                String nIterationsStr = nIterations.getText().toString();

                Intent intent = new Intent(getApplicationContext(), GraficoSerieTemporali.class);

                Bundle bundle = new Bundle();
                bundle.putString("funzione", funzioneStr);
                bundle.putString("xFunzione", xFunzioneStr);
                bundle.putString("firstIteration", firstIterationStr);
                bundle.putString("nIterations", nIterationsStr);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}