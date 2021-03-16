package main.javafx.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WarningMessage {
	public static void show(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("ClotheStore");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
}
