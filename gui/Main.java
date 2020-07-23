package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modules.Load;

public class Main extends Application {
	public static void main(String[] args) {
		try {
			Load.update();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Load.read(System.getProperty("user.dir") + "/data/inspections.cfi");
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		File main = new File(System.getProperty("user.dir") + "\\assets\\main.fxml");
		URL url = main.toURI().toURL();
		Parent root = FXMLLoader.load(url);
	    
        Scene scene = new Scene(root, 1280, 720);
    
        stage.setTitle("Chicago Food Inspector");
        stage.setScene(scene);
        stage.show();
	}
}
