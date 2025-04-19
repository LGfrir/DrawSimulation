package me.LGfrir.PrintChart;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

public class ChartApp extends Application {
    private static final CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void start(Stage primaryStage) {
        // 初始化 JavaFX Toolkit，但不显示窗口
        latch.countDown(); // 通知主线程 JavaFX 初始化完成
    }

    public static void launchJavaFX() {
        new Thread(() -> Application.launch(ChartApp.class), "JavaFX-Launcher-Thread").start();
    }

    public static void waitForLaunch() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
