package com.phindulo.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private Button checkIn;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        pieChart = (PieChart) findViewById(R.id.dashboard_queue_graph);
        checkIn = findViewById(R.id.dashboard_checkin);

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(1, "Cutting"));
        entries.add(new PieEntry(8, "In App"));
        entries.add(new PieEntry(2, "Queue"));


        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(500, 500);
        pieChart.setExtraOffsets(-10,0,-10,-10);
        pieChart.setDrawEntryLabels(false);

        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(14f);
        pieData.setValueTextColor(ColorTemplate.rgb("#FFFFFF"));
        pieChart.setData(pieData);

        Legend legend = pieChart.getLegend();
        legend.setTextSize(16f);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setTextColor(ColorTemplate.rgb("#FFFFFF"));
        legend.setDrawInside(false);

        pieChart.setDescription(null);
        pieChart.setHoleColor(ColorTemplate.COLOR_NONE);
        pieChart.invalidate(); // refresh
    }

}
