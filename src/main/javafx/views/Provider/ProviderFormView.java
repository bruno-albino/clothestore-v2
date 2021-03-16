package main.javafx.views.Provider;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.javafx.controllers.ClientsController;
import main.javafx.controllers.ProvidersController;
import main.javafx.models.AddressesModel;
import main.javafx.models.ClientsModel;
import main.javafx.models.ProvidersModel;
import main.javafx.utils.SuccessMessage;
import main.javafx.utils.WarningMessage;

public class ProviderFormView implements Initializable {
	private ProvidersController providersController;
	private boolean newProvider = true;
	private ProvidersModel provider;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    
    @FXML
    private TextField txtCnpj;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtTradingName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtStreetNumber;

    @FXML
    private TextField txtZipCode;

    @FXML
    private TextField txtNeighborhood;

    @FXML
    private TextField txtState;

    @FXML
    private TextField txtCity;
    
    @FXML
    void handleCancel() {
    	Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleSubmit() {
    	
    	if(this.newProvider) {
    		create();
    	} else {
    		update();
    	}
    }
    
    void create() {
    	AddressesModel address = new AddressesModel();
    	
    	address.setCity(this.txtCity.getText());
    	address.setNeighborhood(this.txtNeighborhood.getText());
    	address.setState(this.txtState.getText());
    	address.setStreet(this.txtStreet.getText());
    	address.setStreetNumber(this.txtStreetNumber.getText());
    	address.setZipCode(this.txtZipCode.getText());
    	
    	provider.setAddresses(address);
    	provider.setPhone(this.txtPhone.getText());
    	provider.setCnpj(this.txtCnpj.getText());
    	provider.setCompanyName(this.txtCompanyName.getText());
    	provider.setTradingName(this.txtTradingName.getText());
    	
    	try {
			this.providersController.create(provider);
			main.javafx.utils.SuccessMessage.show("Fornecedor cadastrado com sucesso");
			this.handleCancel();
		} catch (Exception e) {
			e.printStackTrace();
			main.javafx.utils.WarningMessage.show("Ocorreu um erro ao cadastrar o fornecedor. Por favor, tente novamente");
		}
    }
    
    void update() {
    	provider.getAddresses().setCity(this.txtCity.getText());
    	provider.getAddresses().setNeighborhood(this.txtNeighborhood.getText());
    	provider.getAddresses().setState(this.txtState.getText());
    	provider.getAddresses().setStreet(this.txtStreet.getText());
    	provider.getAddresses().setStreetNumber(this.txtStreetNumber.getText());
    	provider.getAddresses().setZipCode(this.txtZipCode.getText());
    	
    	provider.setPhone(this.txtPhone.getText());
    	provider.setCnpj(this.txtCnpj.getText());
    	provider.setCompanyName(this.txtCompanyName.getText());
    	provider.setTradingName(this.txtTradingName.getText());
    	
    	try {
			this.providersController.update(provider);
			SuccessMessage.show("Fornecedor atualizado com sucesso");
			this.handleCancel();
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao atualizar o fornecedor. Por favor, tente novamente");
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.provider = new ProvidersModel();
    	this.providersController = new ProvidersController();
	}
	
	public void initData(ProvidersModel provider) {
		this.newProvider = false;
		this.provider = provider;
		this.btnSave.setText("Atualizar");
		
		this.txtCity.setText(provider.getAddresses().getCity());
		this.txtCnpj.setText(provider.getCnpj());
		this.txtCompanyName.setText(provider.getCompanyName());
		this.txtTradingName.setText(provider.getTradingName());
		this.txtNeighborhood.setText(provider.getAddresses().getNeighborhood());
		this.txtPhone.setText(provider.getPhone());
		this.txtState.setText(provider.getAddresses().getState());
		this.txtStreet.setText(provider.getAddresses().getStreet());
		this.txtStreetNumber.setText(provider.getAddresses().getStreetNumber());
		this.txtZipCode.setText(provider.getAddresses().getZipCode());
		
	}

}
