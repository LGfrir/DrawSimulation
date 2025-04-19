package me.LGfrir.PrintChart;

import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChartWindow {
    public static void showChart(ArrayList<Double> data,
                                 ArrayList<String> st,
                                 String xLabel,
                                 String yLabel,
                                 int startI,
                                 int divider,
                                 ArrayList<String> text) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            ChartGenerator.showSuccessPercentageChart(stage, data, st, xLabel, yLabel, startI, divider, text);
        });
    }
}
