package com.exam.chillhub;

import com.exam.chillhub.controllers.ErrorViewController;
import com.exam.chillhub.database.AccountDB;
import com.exam.chillhub.enums.View;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

public class ChillhubApplication extends Application {
    public static void launch(Class<? extends Application> cls, String[] args) {
        Application.launch(cls, args);
    }

    /**
     * Open a resource from the resources directory.
     *
     * @param name The name of the resource to open.
     * @return An InputStream over the resource, or null if it doesn't exist.
     */
    public static InputStream openResource(String name) {
        try {
            var res = getResource(name);
            if (res == null)
                return null;
            return res.openStream();
        } catch (IOException e) {
            throw new RuntimeException("Could not load resource");
        }
    }

    /**
     * Get a resource from the resources directory.
     *
     * @param name The name of the resource to get.
     * @return An URL to the resource or null if it doesn't exist.
     */
    public static URL getResource(String name) {
        return ChillhubApplication.class.getResource(name);
    }

    public static void showError(String title, String message, String error) {
        var loaded = View.ErrorView.load();
        ErrorViewController controller = loaded.loader().getController();
        controller.setTitle(title);
        controller.setMessage(message);
        controller.setError(error);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene((Parent) loaded.node()));
        stage.show();
    }

    @Override
    public void start(Stage stage) {
        var node = View.MainView.load().node();
        Scene scene = new Scene((Parent) node);
        stage.setTitle("Chillhub");
        stage.setScene(scene);
        stage.show();
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            showError("Fejl", "En fejl forhindrede operationen i at udføres", sw.toString());
        });
    }

    @Override
    public void stop() {
        // When window is closed, this makes sure to save all accounts before closing the program
        AccountDB.getInstance().saveAccounts();
    }
}
