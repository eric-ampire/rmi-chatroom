package com.ericampire.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {
    private ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;

    public static void main(String[] args) {
        Application.launch(args);
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Main.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage stage) throws Exception {
        fxmlLoader.setLocation(getClass().getResource("/view/main.fxml"));
        rootNode = fxmlLoader.load();

        stage.setTitle("Chat-Room");
        stage.setScene(new Scene(rootNode));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }
}
