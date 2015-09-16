package com.jk0myn0.dynsys;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.Arrays;


public class GraficoEquazioneLogistica extends Activity {

    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_equazione_logistica);

        // get extras

        Bundle extras = getIntent().getExtras();

        String x0Str = extras.getString("x0");
        String rStr = extras.getString("r");
        String xStartStr = extras.getString("xStart");
        String xEndStr = extras.getString("xEnd");
        Integer precision = extras.getInt("precision", 50);
        Integer precision2 = extras.getInt("precision2", 50);

        final double x0 = x0Str.length() > 0 ? Double.parseDouble(x0Str) : 0.5;
        final double r = rStr.length() > 0 ? Double.parseDouble(rStr) : 3.2;
        final double xStart = xStartStr.length() > 0 ? Double.parseDouble(xStartStr) : 0;
        final double xEnd = xEndStr.length() > 0 ? Double.parseDouble(xEndStr) : 1;

        // calculate iterations based on precision value

        int nIterations;
        if (precision == 0) nIterations = 1;
        else nIterations = (precision * 10) + 1;
        //se il numero di iterazioni è 10^3, inc = 10^(-3)
        double esp = Math.log10(nIterations);
        double inc = Math.pow(10, -esp);

        // parabola & bisettrice

        Number[] valuesX = new Number[nIterations];
        Number[] valuesY = new Number[nIterations];

        double funzione;
        int arrayIndex = 0;
        for (double x = xStart; x <= xEnd; x += inc) {
            funzione = eq_log(x, r);
            valuesX[arrayIndex] = x;
            valuesY[arrayIndex] = funzione;
            arrayIndex++;
        }

        // equazione logistica

        int nIterations2;

        if (precision2 == 0) nIterations2 = 1;
        else nIterations2 = (precision2 * 10) + 1;

        Number[] valuesX0 = new Number[nIterations2];
        Number[] valuesY0 = new Number[nIterations2];

        double x;
        double y;

        x = x0;
        valuesX0[0] = x;
        valuesY0[0] = 0;

        for (int i = 1; i < nIterations2; i++) {
            if ((i % 2) != 0) { //se l'indice è dispari
                valuesX0[i] = x;
                valuesY0[i] = eq_log(x, r);
            } else { //se l'indice è pari
                x = eq_log(x, r);
                valuesY0[i] = x;
                valuesX0[i] = valuesY0[i];
            }
        }

        // graph plotting

        plot = (XYPlot) findViewById(R.id.equazioneLogisticaPlot);

        XYSeries parabola = new SimpleXYSeries(Arrays.asList(valuesX), Arrays.asList(valuesY), "Parabola");
        XYSeries bisettrice = new SimpleXYSeries(Arrays.asList(valuesX), Arrays.asList(valuesX), "Bisettrice");
        XYSeries equazioneLogistica = new SimpleXYSeries(Arrays.asList(valuesX0), Arrays.asList(valuesY0), "Equazione Logistica");

        LineAndPointFormatter parabolaFormat = new LineAndPointFormatter(Color.rgb(0, 200, 0), Color.argb(0, 0, 0, 0), Color.argb(65, 158, 227, 147), null);
        LineAndPointFormatter bisettriceFormat = new LineAndPointFormatter(Color.rgb(84, 198, 202), Color.argb(0, 0, 0, 0), Color.argb(65, 158, 227, 147), null);
        LineAndPointFormatter equazioneLogisticaFormat = new LineAndPointFormatter(Color.rgb(20, 198, 22), Color.argb(0, 0, 0, 0), Color.argb(65, 158, 227, 147), null);

        plot.addSeries(parabola, parabolaFormat);
        plot.addSeries(bisettrice, bisettriceFormat);
        plot.addSeries(equazioneLogistica, equazioneLogisticaFormat);

        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
    }

    private double eq_log(double x, double r) {
        return x * r * (1 - x);
    }
}
