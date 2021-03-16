package main.javafx.views.Products;

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
import javafx.stage.Stage;
import main.javafx.controllers.ProductsController;
import main.javafx.models.ProductsModel;
import main.javafx.utils.SceneManager;
import main.javafx.utils.WarningMessage;
import main.javafx.views.Product.ProductFormView;

public class ProductsView implements Initializable {
	private ProductsController productsController;
	private ProductsModel product;
//	private SaleFormController saleFormController;
	private boolean saleProductSelection = false;

    @FXML
    private Button btnCreateNewProduct;

    @FXML
    private TextField txtQuery;

    @FXML
    private Label lblSelectProducts;
    
    @FXML
    private ListView<ProductsModel> lvProducts;
    
    private List<ProductsModel> products = new ArrayList<>();
    private ObservableList<ProductsModel> obsMaterials;
    

	@FXML
    void handleCreateNewProduct() {
		SceneManager sceneManager = new SceneManager("Product/ProductForm.fxml");
		sceneManager.showAndWait();
		this.loadProducts();
    }
	
	@FXML
    void handleSearchProduct() {
		List<ProductsModel> products;
		if(txtQuery.getText().isEmpty()) {
			products = this.products;
		} else {
			products = new ArrayList<ProductsModel>();
			Iterator<ProductsModel> it =  this.products.iterator();
			
			while(it.hasNext()) {
				ProductsModel product = it.next();
				if(product.getDescription().toLowerCase().contains(txtQuery.getText().toLowerCase())) {
					products.add(product);
				}
			}
		}
		
				
		this.obsMaterials = FXCollections.observableArrayList(products);
		this.lvProducts.setItems(obsMaterials);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.product = new ProductsModel();
		this.productsController = new ProductsController();
		this.loadProducts();
		this.createClickHandler();
	}
	
	private void loadProducts() {
		try {
    		
			this.products = this.productsController.index();
			this.obsMaterials = FXCollections.observableArrayList(this.products);
			this.lvProducts.setItems(obsMaterials);
			
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao buscar os produtos. Por favor, tente novamente");
		}
    }
	
	@FXML
    void handleClose() {
    	Stage stage = (Stage) this.btnCreateNewProduct.getScene().getWindow();
        stage.close();
    }
	
	public void createClickHandler() {
		this.lvProducts.setOnMouseClicked(new EventHandler<MouseEvent>() {
    	    @Override
    	    public void handle(MouseEvent click) {

    	        if (click.getClickCount() == 2) {
    	           ProductsModel product = lvProducts.getSelectionModel().getSelectedItem();
    	           try {
//    	        	   if(saleProductSelection) {
//    	        		   	saleFormController.addProduct(product);
//   	        				handleClose();
//    	        	   } else {    	        		   
    	        		   handleEditProduct(product);
//    	        	   }
				} catch (IOException e) {
					e.printStackTrace();
				}
    	        }
    	    }
    	});
	}
	
	void handleEditProduct(ProductsModel product) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fileInputStream = new FileInputStream(new File("src/main/javafx/views/Product/ProductForm.fxml"));
		Stage stage = new Stage();
		
		try {
			stage.setScene(new Scene(loader.load(fileInputStream)));
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
	
		ProductFormView view = loader.getController();
		view.initData(product);
		
		stage.showAndWait();
		this.loadProducts();
	}

	public void initProductSelection() {
//		this.lblSelectProducts.setVisible(true);
//		this.saleFormController = saleFormController;
//		this.saleProductSelection = true;
	}


}
