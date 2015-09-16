package com.jk0myn0.dynsys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SapoMainActivity extends Activity {

    private EditText funzione;
    private EditText xFunzione;
    private EditText nIterations;
    private EditText endRange;
    private Button pulsante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sapo_main_activity);

        funzione = (EditText) findViewById(R.id.funzioneTxt);
        xFunzione = (EditText) findViewById(R.id.xFunzioneTxt);
        nIterations = (EditText) findViewById(R.id.nIterationsTxt);
        endRange = (EditText) findViewById(R.id.endRangeTxt);

        // creazione listener per pulsante

        pulsante = (Button) findViewById(R.id.bottoneDiCalcolo);
        pulsante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String funzioneStr = funzione.getText().toString();
                final String xFunzioneStr = xFunzione.getText().toString();
                final String nIterationsStr = nIterations.getText().toString();
                final String endRangeStr = endRange.getText().toString();

                Intent intent = new Intent(getApplicationContext(), GraficoFunzione.class);

                Bundle bundle = new Bundle();
                bundle.putString("funzione", funzioneStr);
                bundle.putString("xFunzione", xFunzioneStr);
                bundle.putString("nIterations", nIterationsStr);
                bundle.putString("endRange", endRangeStr);

                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
    }


}
