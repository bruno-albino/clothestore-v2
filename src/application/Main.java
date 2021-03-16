package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		SystemFacade systemFacade = new SystemFacade(primaryStage, "LaunchScreen", "ClotheStore");
		systemFacade.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
