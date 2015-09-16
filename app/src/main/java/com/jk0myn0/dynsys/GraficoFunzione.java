package com.jk0myn0.dynsys;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Arrays;

public class GraficoFunzione extends Activity {

    private XYPlot plot;
    private int inc;
    private double risultato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_funzione);

        Bundle extras = getIntent().getExtras();

        String funzione = extras.getString("funzione").length() > 0 ? extras.getString("funzione") : "3.2*x(1-x)";
        String xFunzioneStr = extras.getString("xFunzione");
        String nIterationsStr = extras.getString("nIterations");
        String endRangeStr = extras.getString("endRange");

        final double xFunzione = xFunzioneStr.length() > 0 ? Double.parseDouble(xFunzioneStr) : 0.3;
        final int nIterations = nIterationsStr.length() > 0 ? Integer.parseInt(nIterationsStr) : 50;
        final double endRange = endRangeStr.length() > 0 ? Double.parseDouble(endRangeStr) : 1;

        plot = (XYPlot) findViewById(R.id.graficoFunzione);

        Number[] valoriFunzionex = new Number[(int) (endRange * 100) + 1];
        Number[] valoriFunzioney = new Number[(int) (endRange * 100) + 1];
        Number[] valorix = new Number[(int) (endRange * 100) + 1];
        Number[] IterazioniGrafichex = new Number[(nIterations * 2) + 3];
        Number[] IterazioniGrafichey = new Number[(nIterations * 2) + 3];

        inc = 0;

        for (double i = 0; i < endRange; i = i + 0.01) {

            Expression e = new ExpressionBuilder(funzione).variables("x").build().setVariable("x", i);

            valoriFunzionex[inc] = e.evaluate();
            valoriFunzioney[inc] = i;
            valorix[inc] = i;
            inc++;
        }

        IterazioniGrafichex[0] = xFunzione;
        IterazioniGrafichey[0] = 0;

        double x0 = xFunzione;

        for (int i = 0; i <= nIterations; i++) {

            Expression ei = new ExpressionBuilder(funzione).variables("x").build().setVariable("x", x0);

            risultato = (double) ei.evaluate();

            IterazioniGrafichex[i * 2 + 1] = x0;
            IterazioniGrafichey[i * 2 + 1] = risultato;

            IterazioniGrafichex[i * 2 + 2] = risultato;
            IterazioniGrafichey[i * 2 + 2] = risultato;

            x0 = risultato;
        }

        XYSeries series1 = new SimpleXYSeries(Arrays.asList(valoriFunzioney), Arrays.asList(valoriFunzionex), "f(x)");
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(valorix), Arrays.asList(valorix), "y=x");
        XYSeries series3 = new SimpleXYSeries(Arrays.asList(IterazioniGrafichex), Arrays.asList(IterazioniGrafichey), "Intersezione");

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.rgb(0, 300, 0), 00000000, 00000000, null);
        LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.rgb(0, 200, 0), 00000000, 00000000, null);
        LineAndPointFormatter series3Format = new LineAndPointFormatter(Color.rgb(0, 100, 0), 00000000, 00000000, null);

        plot.addSeries(series1, series1Format);
        plot.addSeries(series2, series2Format);
        plot.addSeries(series3, series3Format);

        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);


    }
}
