package main.javafx.views.Clients;

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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.javafx.controllers.ClientsController;
import main.javafx.models.ClientsModel;
import main.javafx.utils.SceneManager;
import main.javafx.utils.WarningMessage;
import main.javafx.views.Client.ClientFormView;

public class ClientsView implements Initializable {
	ClientsModel clientsModel;
	ClientsController clientsController;

	@FXML
	private AnchorPane paneClients;

	@FXML
	private ListView<ClientsModel> lvClients;

	@FXML
	private Button btNewClient;

	@FXML
	private TextField txtQuery;

	@FXML
	private Label lblSelectClient;

	private List<ClientsModel> clients = new ArrayList<>();
	private ObservableList<ClientsModel> obsClients;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.clientsModel = new ClientsModel();
		this.clientsController = new ClientsController();

		this.loadClients();
		this.createClickHandler();
	}

	private void loadClients() {
		try {

			this.clients = this.clientsController.index();
			this.obsClients = FXCollections.observableArrayList(this.clients);
			this.lvClients.setItems(obsClients);

		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao buscar os clientes. Por favor, tente novamente");
		}
	}

	@FXML
	void handleClose() {
		Stage stage = (Stage) this.btNewClient.getScene().getWindow();
		stage.close();
	}

	public void createClickHandler() {
		this.lvClients.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2) {
					ClientsModel client = lvClients.getSelectionModel().getSelectedItem();
					try {

//    	        	if(saleClientSelection) {
//    	        		saleFormController.setClient(client);
//    	        		handleClose();
//    	        	} else {
						handleEditClient(client);

//    	        	}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	@FXML
	void handleCreateNewClient() {
		SceneManager sceneManager = new SceneManager("Client/ClientForm.fxml");
		sceneManager.showAndWait();
		this.loadClients();
	}

	@FXML
	void handleSearchClients() {
		List<ClientsModel> clients;
		if (txtQuery.getText().isEmpty()) {
			clients = this.clients;
		} else {
			clients = new ArrayList<ClientsModel>();
			Iterator<ClientsModel> it = this.clients.iterator();

			while (it.hasNext()) {
				ClientsModel client = it.next();
				if (client.getName().toLowerCase().contains(txtQuery.getText().toLowerCase())) {
					clients.add(client);
				}
			}
		}

		this.obsClients = FXCollections.observableArrayList(clients);
		this.lvClients.setItems(obsClients);
	}

	void handleEditClient(ClientsModel client) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fileInputStream = new FileInputStream(new File("src/main/javafx/views/Client/ClientForm.fxml"));
		Stage stage = new Stage();

		try {
			stage.setScene(new Scene(loader.load(fileInputStream)));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		ClientFormView clientsView = loader.getController();
		clientsView.initData(client);

		stage.showAndWait();
		this.loadClients();
	}

	public void initClientSelection() {
		this.lblSelectClient.setVisible(true);
//		this.saleFormController = saleFormController;
//		this.saleClientSelection = true;
	}
}
