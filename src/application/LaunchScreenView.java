package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.javafx.utils.SceneManager;

public class LaunchScreenView {

	@FXML
    private Button btClients;

    @FXML
    private Button btnSales;

    @FXML
    private Button btnProducts;

    @FXML
    private Button btnProviders;

    @FXML
    void handleOpenProducts() {
    	openScene("Products/Products.fxml");
    }

    @FXML
    void handleOpenProviders() {
    	openScene("Providers/Providers.fxml");
    }

    @FXML
    void handleOpenSales() {
    	openScene("Sales/Sales.fxml");
    }

	
    @FXML
    void handleOpenClients() {
    	openScene("Clients/Clients.fxml");
    }
    
    
    void openScene(String path) {
    	SceneManager sceneManager = new SceneManager(path);
    	sceneManager.showAndWait();
    }

	
}
