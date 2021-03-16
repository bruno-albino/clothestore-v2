package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SystemFacade {
	Stage stage;
	String entryArchive = null;
	String titleName = null;
	
	public SystemFacade(Stage stage, String entryArchive, String titleName) {
		this.stage = stage;
		this.entryArchive = entryArchive;
		this.titleName = titleName;
	}
	
	public void start() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(entryArchive + ".fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene((scene));
		stage.show();
		stage.setTitle(titleName);
	}

}
