package main.javafx.views.Sales;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.javafx.controllers.PaymentConditionsController;
import main.javafx.controllers.PaymentFormsController;
import main.javafx.controllers.SalesController;
import main.javafx.models.PaymentConditionsModel;
import main.javafx.models.PaymentFormsModel;
import main.javafx.models.SalesModel;
import main.javafx.utils.SceneManager;
import main.javafx.utils.WarningMessage;

public class SalesView implements Initializable {
	private SalesController salesController;
	private PaymentConditionsController paymentConditionsController;
	private PaymentFormsController paymentFormsController;

	@FXML
    private Button btnNewSale;

    @FXML
    private TextField txtQuery;

    @FXML
    private ListView<SalesModel> lvSales;

    @FXML
    private ComboBox<PaymentConditionsModel> cbConditionsOfPayment;

    @FXML
    private ComboBox<PaymentFormsModel> cbFormsOfPayment;
    
    private List<SalesModel> sales = new ArrayList<>();
    private ObservableList<SalesModel> obsSales;
    
    private List<PaymentFormsModel> paymentForms = new ArrayList<>();
    private ObservableList<PaymentFormsModel> obsFormsOfPayment;
    
    private List<PaymentConditionsModel> paymentConditions = new ArrayList<>();
    private ObservableList<PaymentConditionsModel> obsConditionsOfPayment;
    

	@FXML
    void handleCreateNewSale() {
		SceneManager sceneManager = new SceneManager("Sale/FXMLSalesForm.fxml");
		sceneManager.showAndWait();
		this.loadSales();
    }
	
	@FXML
    void handleSearchSale() {
		List<SalesModel> sales;	
		
		if(txtQuery.getText().isEmpty()) {
			sales = this.sales;
		} else {
			sales = new ArrayList<SalesModel>();
			Iterator<SalesModel> it =  this.sales.iterator();
			
			while(it.hasNext()) {
				SalesModel sale = it.next();
				
				if(sale.getClient().getTradingName().toLowerCase().contains(txtQuery.getText().toLowerCase())) {
					sales.add(sale);
				}
			}
		}
		
				
		this.obsSales = FXCollections.observableArrayList(sales);
		this.lvSales.setItems(obsSales);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.salesController = new SalesController();
		this.paymentFormsController = new PaymentFormsController();
		this.paymentConditionsController = new PaymentConditionsController();
		this.loadSales();
		this.loadConditionsOfPayment();
		this.loadFormsOfPayment();
		this.createClickHandler();
	}
	
	private void loadSales() {
		try {
    		
			this.sales = this.salesController.index();
			this.obsSales = FXCollections.observableArrayList(this.sales);
			this.lvSales.setItems(obsSales);
			
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao buscar as vendas. Por favor, tente novamente");
		}
    }
	
	private void loadConditionsOfPayment() {
		try {
    		
			this.paymentConditions = this.paymentConditionsController.index();
			this.obsConditionsOfPayment = FXCollections.observableArrayList(this.paymentConditions);
			this.cbConditionsOfPayment.setItems(obsConditionsOfPayment);
			
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao buscar as condições de pagamento. Por favor, tente novamente");
		}
    }
	
	private void loadFormsOfPayment() {
		try {
    		
			this.paymentForms = this.paymentFormsController.index();
			this.obsFormsOfPayment = FXCollections.observableArrayList(this.paymentForms);
			this.cbFormsOfPayment.setItems(obsFormsOfPayment);
			
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao buscar as formas de pagamento. Por favor, tente novamente");
		}
    }
	
	public void createClickHandler() {
		this.lvSales.setOnMouseClicked(new EventHandler<MouseEvent>() {
    	    @Override
    	    public void handle(MouseEvent click) {

    	        if (click.getClickCount() == 2) {
    	           SalesModel item = lvSales.getSelectionModel().getSelectedItem();
    	           try {
					handleEditSale(item);
				} catch (IOException e) {
					e.printStackTrace();
				}
    	        }
    	    }
    	});
	}
	
	void handleEditSale(SalesModel sale) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fileInputStream = new FileInputStream(new File("src/main/javafx/views/Sale/SaleForm.fxml"));
		Stage stage = new Stage();
		
		try {
			stage.setScene(new Scene(loader.load(fileInputStream)));
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
	
		//ProviderFormController controller = loader.getController();
		//controller.initData(sale);
		
		//stage.show();
	}



}
