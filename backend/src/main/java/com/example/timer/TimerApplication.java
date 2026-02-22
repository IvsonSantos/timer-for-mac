package com.example.timer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TimerApplication extends Application {

    private static ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        Application.launch(TimerApplication.class, args);
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(TimerApplication.class);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setAlwaysOnTop(true);

        WebView webView = new WebView();
        webView.getEngine().load("http://localhost:8080");

        // Transparency for WebView
        webView.setPageFill(Color.TRANSPARENT);

        // Disable scrollbars and context menu
        webView.setContextMenuEnabled(false);

        Scene scene = new Scene(webView, 300, 300);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.show();

        // Dragging support
        final Delta dragDelta = new Delta();
        scene.setOnMousePressed(mouseEvent -> {
            dragDelta.x = primaryStage.getX() - mouseEvent.getScreenX();
            dragDelta.y = primaryStage.getY() - mouseEvent.getScreenY();
        });
        scene.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() + dragDelta.x);
            primaryStage.setY(mouseEvent.getScreenY() + dragDelta.y);
        });
    }

    @Override
    public void stop() {
        springContext.stop();
        Platform.exit();
    }

    private static class Delta {
        double x, y;
    }
}
