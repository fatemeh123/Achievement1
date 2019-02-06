package com.example.hero.achievement;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.hero.achievement.model.DatabaseAddProject;
import com.example.hero.achievement.modeltwo.DatabaseModelChart;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChartDialogActivity extends AppCompatActivity {



    List<DatabaseModelChart> chartList;              //vase delete kardan rahat tare
    private SQLiteDBHelper sqLiteDBHelper;                              //mikhaym list haye zakhire shodaro beigirm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_dialog);

        Intent intent=getIntent();
        String subjectName = intent.getStringExtra("subject");

        chartList = new ArrayList<>();
        sqLiteDBHelper = new SQLiteDBHelper(this);

        chartList = sqLiteDBHelper.getSessionDateAndHour(subjectName);
        //Toast.makeText(ChartDialogActivity.this, subjectName,Toast.LENGTH_LONG).show();


        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();

        for (int i = 0; i < chartList.size(); i++){

            data.add(new ValueDataEntry(
                    chartList.get(i).getDate(),
                    chartList.get(i).getDuration()
            ));

        }

        Column column = cartesian.column(data);
        column.tooltip()
                .titleFormat("h {%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("h {%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Title");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("h {%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("row 1");
        cartesian.yAxis(0).title("row 2");

        anyChartView.setChart(cartesian);







    }
}