package main.javafx.views.Providers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.javafx.controllers.ProvidersController;
import main.javafx.models.ProvidersModel;
import main.javafx.views.Provider.ProviderFormView;

public class ProvidersView implements Initializable {
	private ProvidersController providersController;
	private ProvidersModel providersModel;

    @FXML
    private Button btnNewProvider;

    @FXML
    private ListView<ProvidersModel> lvProviders;

    @FXML
    private Button btnCreateNewProduct;

    @FXML
    private TextField txtQuery;
    
    private List<ProvidersModel> providers = new ArrayList<>();
    private ObservableList<ProvidersModel> obsProviders;
    

	@FXML
    void handleCreateNewProvider() {
		main.javafx.utils.SceneManager sceneManager = new main.javafx.utils.SceneManager("Provider/ProviderForm.fxml");
		sceneManager.showAndWait();
		this.loadProviders();
    }
	
	@FXML
    void handleSearchProvider() {
		List<ProvidersModel> providers;
		if(txtQuery.getText().isEmpty()) {
			providers = this.providers;
		} else {
			providers = new ArrayList<ProvidersModel>();
			Iterator<ProvidersModel> it =  this.providers.iterator();
			
			while(it.hasNext()) {
				ProvidersModel provider = it.next();
				if(provider.getTradingName().toLowerCase().contains(txtQuery.getText().toLowerCase())) {
					providers.add(provider);
				}
			}
		}
		
				
		this.obsProviders = FXCollections.observableArrayList(providers);
		this.lvProviders.setItems(obsProviders);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.providersModel = new ProvidersModel();
		this.providersController = new ProvidersController();
		this.loadProviders();
		this.createClickHandler();
	}
	
	private void loadProviders() {
		try {
    		
			this.providers = this.providersController.index();
			this.obsProviders = FXCollections.observableArrayList(this.providers);
			this.lvProviders.setItems(obsProviders);
			
		} catch (Exception e) {
			e.printStackTrace();
			main.javafx.utils.WarningMessage.show("Ocorreu um erro ao buscar os fornecedores. Por favor, tente novamente");
		}
    }
	
	public void createClickHandler() {
		this.lvProviders.setOnMouseClicked(new EventHandler<MouseEvent>() {
    	    @Override
    	    public void handle(MouseEvent click) {

    	        if (click.getClickCount() == 2) {
    	           ProvidersModel item = lvProviders.getSelectionModel().getSelectedItem();
    	           try {
					handleEditProvider(item);
				} catch (IOException e) {
					e.printStackTrace();
				}
    	        }
    	    }
    	});
	}
	
	void handleEditProvider(ProvidersModel provider) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fileInputStream = new FileInputStream(new File("src/main/javafx/views/Provider/ProviderForm.fxml"));
		Stage stage = new Stage();
		
		try {
			stage.setScene(new Scene(loader.load(fileInputStream)));
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
	
		ProviderFormView view = loader.getController();
		view.initData(provider);
		stage.showAndWait();
		this.loadProviders();
	}



}
