package main.javafx.views.Client;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import main.javafx.controllers.ClientsController;
import main.javafx.models.AddressesModel;
import main.javafx.models.ClientsModel;
import main.javafx.utils.SuccessMessage;
import main.javafx.utils.WarningMessage;

public class ClientFormView implements Initializable {
	private ClientsController clientsController;
	private boolean newClient = true;
	private ClientsModel clientsModel;
	
    @FXML
    private Label lblCPF;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtName;

    @FXML
    private Label lblName;

    @FXML
    private Label lblSurName;

    @FXML
    private TextField txtSurName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtStreetNumber;
    
    @FXML
    private TextField txtCity;
    
    @FXML
    private TextField txtState;

    @FXML
    private TextField txtZipCode;

    @FXML
    private TextField txtNeighborHood;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    private ToggleGroup typeOfPeople;
    
    @FXML
    private ToggleGroup sexGroup;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		this.clientsModel = new ClientsModel();
    	this.clientsController = new ClientsController();
	}

    @FXML
    void handleCancel() {
    	Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleChangeTypeOfPerson() {
    	RadioButton button = (RadioButton) this.typeOfPeople.getSelectedToggle();
    	
    	if(button.getText().contains("Jurídica")) {
    		this.lblCPF.setText("CNPJ");
    		this.lblName.setText("Razão Social");
    		this.lblSurName.setText("Nome Fantasia");
    		
    		this.txtCPF.setPromptText("XX.XXX.XXX-XXXXX.XX");
    		this.txtName.setPromptText("Razão Social");
    		this.txtName.setPromptText("Nome Fantasia");
    	} else {
    		this.lblCPF.setText("CPF");
    		this.lblName.setText("Nome");
    		this.lblSurName.setText("Sobrenome");
    		
    		this.txtCPF.setPromptText("XXX.XXX.XXX-XX");
    		this.txtName.setPromptText("Nome");
    		this.txtName.setPromptText("Sobrenome");
    	}
    }

    @FXML
    void handleSubmit() {
    	
    	if(this.newClient) {
    		create();
    	} else {
    		update();
    	}
    }
    
    void create() {
    	RadioButton sexRadio = (RadioButton) this.sexGroup.getSelectedToggle();
    	AddressesModel address = new AddressesModel();
    	
    	address.setCity(this.txtCity.getText());
    	address.setNeighborhood(this.txtNeighborHood.getText());
    	address.setState(this.txtState.getText());
    	address.setStreet(this.txtStreet.getText());
    	address.setStreetNumber(this.txtStreetNumber.getText());
    	address.setZipCode(this.txtZipCode.getText());
    	
    	clientsModel.setAddresses(address);
    	clientsModel.setPhone(this.txtPhone.getText());
    	clientsModel.setCpfCnpj(this.txtCPF.getText());
    	clientsModel.setSex(sexRadio.getText().contains("Masculino") ? 'M' : 'F');
    	clientsModel.setName(this.txtName.getText());
    	clientsModel.setTradingName(this.txtSurName.getText());
    	
    	try {
    		this.clientsController.create(clientsModel);
			SuccessMessage.show("Cliente cadastrado com sucesso");
			this.handleCancel();
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao cadastrar o cliente. Por favor, tente novamente");
		}
    }
    
    void update() {
    	RadioButton sexRadio = (RadioButton) this.sexGroup.getSelectedToggle();
    	
    	clientsModel.getAddresses().setCity(this.txtCity.getText());
    	clientsModel.getAddresses().setNeighborhood(this.txtNeighborHood.getText());
    	clientsModel.getAddresses().setState(this.txtState.getText());
    	clientsModel.getAddresses().setStreet(this.txtStreet.getText());
    	clientsModel.getAddresses().setStreetNumber(this.txtStreetNumber.getText());
    	clientsModel.getAddresses().setZipCode(this.txtZipCode.getText());
    	
    	clientsModel.setPhone(this.txtPhone.getText());
    	clientsModel.setCpfCnpj(this.txtCPF.getText());
    	clientsModel.setSex(sexRadio.getText().contains("Masculino") ? 'M' : 'F');
    	clientsModel.setName(this.txtName.getText());
    	clientsModel.setTradingName(this.txtSurName.getText());
    	
    	try {
			this.clientsController.update(clientsModel);
			SuccessMessage.show("Cliente atualizado com sucesso");
			this.handleCancel();
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao atualizar o cliente. Por favor, tente novamente");
		}
    }
	
	public void initData(ClientsModel client) {
		this.newClient = false;
		this.clientsModel = client;
		this.btnSave.setText("Atualizar");
		
		this.txtCity.setText(client.getAddresses().getCity());
		this.txtCPF.setText(client.getCpfCnpj());
		this.txtName.setText(client.getName());
		this.txtNeighborHood.setText(client.getAddresses().getNeighborhood());
		this.txtPhone.setText(client.getPhone());
		this.txtState.setText(client.getAddresses().getState());
		this.txtStreet.setText(client.getAddresses().getStreet());
		this.txtStreetNumber.setText(client.getAddresses().getStreetNumber());
		this.txtSurName.setText(client.getTradingName());
		this.txtZipCode.setText(client.getAddresses().getZipCode());
		
		Iterator<Toggle> it = this.sexGroup.getToggles().iterator();
		String sex = client.getSex() == 'M' ? "Masculino" : "Feminino";
		
		while(it.hasNext()) {
			RadioButton radio = (RadioButton) it.next();
			
			if(radio.getText().equalsIgnoreCase(sex)) {
				radio.setSelected(true);
			} else {
				radio.setSelected(false);
			}
		}
	}

}
