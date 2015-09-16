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

public class GraficoSerieTemporali extends Activity {

    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_serie_temporali);

        Bundle extras = getIntent().getExtras();

        String funzione = extras.getString("funzione").length() > 0 ? extras.getString("funzione") : "3.2*x(1-x)";
        String xFunzioneStr = extras.getString("xFunzione");
        String firstIterationStr = extras.getString("firstIteration");
        String nIterationsStr = extras.getString("nIterations");

        double xFunzione = xFunzioneStr.length() > 0 ? Double.parseDouble(xFunzioneStr) : 0.5;
        final int firstIteration = firstIterationStr.length() > 0 ? Integer.parseInt(firstIterationStr) : 25;
        final int nIterations = nIterationsStr.length() > 0 ? Integer.parseInt(nIterationsStr) : 50;

        plot = (XYPlot) findViewById(R.id.serieTemporaliPlot);
        Number[] valorix = new Number[nIterations];
        Number[] valoriy = new Number[nIterations];

        for (int i = 1; i < (firstIteration + nIterations); i++) {

            Expression e = new ExpressionBuilder(funzione).variables("x").build().setVariable("x", xFunzione);

            xFunzione = (float) e.evaluate();
            if (i >= firstIteration) {
                valorix[(i - firstIteration)] = xFunzione;
                valoriy[(i - firstIteration)] = (i - firstIteration + 1);
            }
        }

        XYSeries series1 = new SimpleXYSeries(Arrays.asList(valoriy), Arrays.asList(valorix), "Serie Temporali");
        //XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers),Arrays.asList(series1Numbers), "Series2");

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.rgb(0, 200, 0), Color.rgb(0, 200, 0), 00000000, null);
        //LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.rgb(0, 100, 0),Color.rgb(0, 100, 0),00000000,null);

        plot.addSeries(series1, series1Format);
        //plot.addSeries(series2, series2Format);

        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);


    }
}
