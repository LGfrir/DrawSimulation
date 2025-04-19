package me.LGfrir.PrintChart;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;

public class ChartGenerator {

    /**
     * 显示成功概率分布图，左侧75%为图表，右侧25%为多行文字显示，每个 ArrayList 的元素对应一行文字。
     *
     * @param stage      应用窗口
     * @param data       图表数据
     * @param xLabel     x轴标签
     * @param yLabel     y轴标签
     * @param startI     数据起始索引（用于生成 x轴标签）
     * @param textLines  每个元素对应一行文字
     */
    public static void showSuccessPercentageChart(Stage stage, ArrayList<Double> data, ArrayList<String> ST, String xLabel, String yLabel, int startI, int divider, ArrayList<String> textLines) {
        stage.setTitle("柱状图");

        // 创建坐标轴
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);
        // 调整 x 轴刻度字体大小
        xAxis.setStyle("-fx-font-size: 14px;");
        yAxis.setStyle("-fx-font-size: 14px;");

        // 创建柱状图
        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("成功概率分布图");
        // 设置初始间距，可以根据需求调整
        barChart.setCategoryGap(10);
        barChart.setBarGap(5);

        // 添加数据系列
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(yLabel);
        for (int i = 0; i < data.size(); i++) {
            String category;
            if(ST == null) category = startI + i * divider + "";

            else category =  (ST.get(i).charAt(0) == '0' ? "-" : (char)(ST.get(i).charAt(0) - 1) ) +(ST.get(i).substring(1, 3));
            series.getData().add(new XYChart.Data<>(category, data.get(i)));
        }
        barChart.getData().add(series);

        // 创建右侧文字区域，将 ArrayList 中每个字符串转为一个 Text 节点并加入 VBox
        VBox textBox = new VBox(5);  // 行间距5像素
        for (String line : textLines) {
            Text text = new Text(line);
            // 设置右侧文字较大
            text.setStyle("-fx-font-size: 18px;");
            textBox.getChildren().add(text);
        }
        StackPane textPane = new StackPane(textBox);

        // 使用 SplitPane 横向分割，左侧图表，右侧文字
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.getItems().addAll(barChart, textPane);
        // 设置左侧占75%，右侧占25%
        splitPane.setDividerPositions(0.85);

        Scene scene = new Scene(splitPane, 1000, 750);
        stage.setScene(scene);
        stage.show();

        // 添加窗口宽度监听，根据宽度动态调整柱状图的间距
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            double width = newVal.doubleValue();
            // 如果窗口宽度较窄，减小间距使柱子更宽；宽度大时使用默认间距
            if (width < 800) {
                barChart.setCategoryGap(4);
                barChart.setBarGap(2);
            } else {
                barChart.setCategoryGap(9);
                barChart.setBarGap(5);
            }
        });

        // 延迟执行，等待图表渲染完成后更新柱子上标签
        Platform.runLater(() -> {
            Node plotArea = barChart.lookup(".chart-plot-background");
            if (plotArea != null && plotArea.getParent() instanceof Pane) {
                Pane plotPane = (Pane) plotArea.getParent();
                updateLabels(plotPane, series); // 初次绘制

                // chart 尺寸变化监听，更新标签位置
                barChart.widthProperty().addListener((obs, oldVal, newVal) -> updateLabels(plotPane, series));
                barChart.heightProperty().addListener((obs, oldVal, newVal) -> updateLabels(plotPane, series));

                // scene 尺寸变化监听（全屏或窗口变化时）
                Scene sceneRef = barChart.getScene();
                if (sceneRef != null) {
                    sceneRef.widthProperty().addListener((obs, oldVal, newVal) ->
                            Platform.runLater(() -> updateLabels(plotPane, series))
                    );
                    sceneRef.heightProperty().addListener((obs, oldVal, newVal) ->
                            Platform.runLater(() -> updateLabels(plotPane, series))
                    );
                }
            }
        });
    }

    // 更新图表上每个柱子对应的文本标签
    private static void updateLabels(Pane plotPane, XYChart.Series<String, Number> series) {
        // 清除旧标签（保留柱状图节点）
        plotPane.getChildren().removeIf(node -> node instanceof Text);
        // 遍历数据点，计算并添加新标签
        for (XYChart.Data<String, Number> dataPoint : series.getData()) {
            Node barNode = dataPoint.getNode();
            if (barNode != null) {
                Bounds barBoundsScene = barNode.localToScene(barNode.getBoundsInLocal());
                Bounds barBoundsInPlot = plotPane.sceneToLocal(barBoundsScene);
                double labelX = barBoundsInPlot.getMinX() + barBoundsInPlot.getWidth() / 2;
                double labelY = barBoundsInPlot.getMinY() - 5;
                Text label = new Text(String.format("%.1f", dataPoint.getYValue().doubleValue()) + "%");
                // 调整标签字体大小、加粗
                label.setStyle("-fx-font-size: 12px;");
                label.setTranslateX(labelX - label.prefWidth(-1) / 2);
                label.setTranslateY(labelY - label.prefHeight(-1));
                plotPane.getChildren().add(label);
            }
        }
    }
}
