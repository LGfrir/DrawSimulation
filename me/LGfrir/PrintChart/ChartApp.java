package me.LGfrir.PrintChart;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;

public class ChartApp extends Application {
    private static ArrayList<Double> data;
    private static String xLabel;
    private static String yLabel;
    private static int startI;
    private static ArrayList<String> text;
    private static int divider;
    private static ArrayList<String> st;
    public static void initializeSuccessPercentageChart(ArrayList<Double> d, ArrayList<String> ST, String x, String y, int I, int di, ArrayList<String> L) {
        data = d;
        xLabel = x;
        yLabel = y;
        startI = I;
        text = L;
        divider = di;
        st = ST;
        launch(); // 启动 JavaFX UI
    }

    @Override
    public void start(Stage stage) {
        ChartGenerator.showSuccessPercentageChart(stage, data,st, xLabel, yLabel,startI,divider,text);
    }
}
