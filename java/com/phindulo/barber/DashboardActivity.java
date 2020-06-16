package com.phindulo.barber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.Timestamp;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {
    private Button checkIn;
    private PieChart pieChart;
    private List<QueueListItem> queueListItems;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /*Create recycler view list*/
        queueListItems = new ArrayList<>();
        queueListItems.add(new QueueListItem("Phindulo", "Position: 1", new Timestamp(40, 10)));
        queueListItems.add(new QueueListItem("Sedzani", "Position: Cutting", new Timestamp(20, 10)));
        queueListItems.add(new QueueListItem("Tondani", "Position: 2", new Timestamp(60, 10)));
        queueListItems.add(new QueueListItem("Rendani", "Position: 3", new Timestamp(80, 10)));

        /*Initialise recycler view*/
        recyclerView = findViewById(R.id.dashboard_queue_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*Initialise recycler view adapter*/
        QueueRecyclerViewAdapter queueRecyclerViewAdapter = new QueueRecyclerViewAdapter(queueListItems, DashboardActivity.this);

        /*Set recylcer view adapter*/
        recyclerView.setAdapter(queueRecyclerViewAdapter);

        /*Notify recycler view adaper data set changed*/
        queueRecyclerViewAdapter.notifyDataSetChanged();

        pieChart = (PieChart) findViewById(R.id.dashboard_queue_graph);
        checkIn = findViewById(R.id.dashboard_checkin);

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(1, "Cutting"));
        entries.add(new PieEntry(8, "In App"));
        entries.add(new PieEntry(2, "Queue"));


        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(500, 500);
        pieChart.setExtraOffsets(-10, 0, -10, -10);
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
