package main.javafx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SceneManager {
	private String FXMLView;
	private Stage stage;
	FXMLLoader loader;
	FileInputStream fileInputStream;
	Parent newScene;
	
	public SceneManager(String path) {
		this.FXMLView = path;
		this.loader = new FXMLLoader();
		this.stage = new Stage();
	}
	
	public void show() throws IOException {
		this.fileInputStream = new FileInputStream(new File("src/main/javafx/views/" + this.FXMLView));
	    this.newScene = this.loader.load(fileInputStream);

		if(this.stage == null) {
			this.setScene(newScene);
			this.stage.showAndWait();		
		} else if(this.stage.isShowing()) {
			this.stage.toFront();
		} else {
			this.setScene(newScene);
			this.stage.show();
		}
	}
	
	public void showAndWait() {
		try {
			this.fileInputStream = new FileInputStream(new File("src/main/javafx/views/" + this.FXMLView));
			this.newScene = loader.load(fileInputStream);
			
			if(this.stage == null) {
				this.setScene(newScene);
				this.stage.showAndWait();		
			} else if(this.stage.isShowing()) {
				this.stage.toFront();
			} else {
				this.setScene(newScene);
				this.stage.showAndWait();
			}
						

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(this.FXMLView);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ClotheStore");
			alert.setContentText("Ocorreu um erro ao abrir o arquivo. Por favor, tente novamente");
			alert.show();
		}
	}
	
	private void setScene(Parent scene) {
		Scene newScene = new Scene(scene);
		this.stage.setScene(newScene);
	}
	
}
