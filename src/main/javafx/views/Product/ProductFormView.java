package main.javafx.views.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.javafx.controllers.CategoriesController;
import main.javafx.controllers.MaterialsController;
import main.javafx.controllers.ProductsController;
import main.javafx.controllers.ProvidersController;
import main.javafx.controllers.SizesController;
import main.javafx.models.CategoriesModel;
import main.javafx.models.MaterialsModel;
import main.javafx.models.ProductsModel;
import main.javafx.models.ProvidersModel;
import main.javafx.models.SizesModel;
import main.javafx.utils.SuccessMessage;
import main.javafx.utils.WarningMessage;

public class ProductFormView implements Initializable{
	private ProductsController productsController;
	private MaterialsController materialsController;
	private CategoriesController categoriesController;
	private SizesController sizesController;
	private ProvidersController providersController;
	
	private boolean newProduct = true;
	private ProductsModel product;
	
    @FXML
    private TextField txtDescription;
    
    @FXML
    private Button btnSave;

    @FXML
    private TextField txtPrice;

    @FXML
    private ComboBox<CategoriesModel> cbCategories;
    
    @FXML
    private ComboBox<ProvidersModel> cbProviders;

    @FXML
    private ComboBox<MaterialsModel> cbMaterials;

    @FXML
    private ComboBox<SizesModel> cbSizes;

    @FXML
    private Button btnCancel;
    
    private List<MaterialsModel> materials = new ArrayList<>();
    private ObservableList<MaterialsModel> obsMaterials;
    
    private List<CategoriesModel> categories = new ArrayList<>();
    private ObservableList<CategoriesModel> obsCategories;

    private List<SizesModel> sizes = new ArrayList<>();
    private ObservableList<SizesModel> obsSizes;
    
    private List<ProvidersModel> providers = new ArrayList<>();
    private ObservableList<ProvidersModel> obsProviders;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.productsController = new ProductsController();
		this.categoriesController = new CategoriesController();
		this.materialsController = new MaterialsController();
		this.sizesController = new SizesController();
		this.providersController = new ProvidersController();	
		this.initialLoad();
	}
	
	public void initialLoad() {
		this.loadMaterials();
		this.loadSizes();
		this.loadCategories();
		this.loadProviders();
	}
	
	public void loadCategories() {
    	try {
    		
			this.categories = this.categoriesController.index();
			this.obsCategories = FXCollections.observableArrayList(this.categories);
			this.cbCategories.setItems(obsCategories);
			
			if(!this.newProduct) {
				for(CategoriesModel category : categories) {
					if(category.getId() == this.product.getCategoryId()) {
						this.cbCategories.setValue(category);
					}
				}				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			WarningMessage.show("Ocorreu um erro ao buscar as categorias. Por favor, tente novamente");
		}
    }
	
	private void loadMaterials() {
		try {
    		
			this.materials = this.materialsController.index();
			this.obsMaterials = FXCollections.observableArrayList(this.materials);
			this.cbMaterials.setItems(obsMaterials);

			if(!this.newProduct) {
				for(MaterialsModel material : materials) {
					if(material.getId() == this.product.getMaterialId()) {
						this.cbMaterials.setValue(material);
					}
				}				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao buscar os materiais. Por favor, tente novamente");
		}
    }
	
	private void loadSizes() {
		try {
    		
			this.sizes = this.sizesController.index();
			this.obsSizes = FXCollections.observableArrayList(this.sizes);
			this.cbSizes.setItems(obsSizes);
			
			if(!this.newProduct) {
				for(SizesModel size : sizes) {
					if(size.getId() == this.product.getSizeId()) {
						this.cbSizes.setValue(size);
					}
				}				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao buscar as medidas. Por favor, tente novamente");
		}
    }
	
	private void loadProviders() {
		try {
    		
			this.providers = this.providersController.index();
			this.obsProviders = FXCollections.observableArrayList(this.providers);
			this.cbProviders.setItems(obsProviders);
			
			if(!this.newProduct) {
				for(ProvidersModel provider : providers) {
					if(provider.getId() == this.product.getProviderId()) {
						this.cbProviders.setValue(provider);
					}
				}				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao buscar os fornecedores. Por favor, tente novamente");
		}
    }
	
    @FXML
    void handleCancel() {
    	Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleSubmit() {
    	if(this.newProduct) {
    		create();
    	} else {
    		update();
    	}
    }
    
    void create() {
    	this.product = new ProductsModel();
    	CategoriesModel category = this.cbCategories.getSelectionModel().getSelectedItem();
    	MaterialsModel material = this.cbMaterials.getSelectionModel().getSelectedItem();
    	ProvidersModel provider = this.cbProviders.getSelectionModel().getSelectedItem();
    	SizesModel sizes = this.cbSizes.getSelectionModel().getSelectedItem();
    	
    	this.product.setDescription(this.txtDescription.getText());
    	this.product.setCategoryId(category.getId());
    	this.product.setMaterialId(material.getId());
    	this.product.setProviderId(provider.getId());
    	this.product.setSizeId(sizes.getId());
    	this.product.setPrice(Float.parseFloat(this.txtPrice.getText()));
    	    	
    	try {
			this.productsController.create(product);
			SuccessMessage.show("Produto cadastrado com sucesso");
			this.handleCancel();
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao cadastrar o produto. Por favor, tente novamente");
		}
    }
    
    void update() {
    	CategoriesModel category = this.cbCategories.getSelectionModel().getSelectedItem();
    	MaterialsModel material = this.cbMaterials.getSelectionModel().getSelectedItem();
    	ProvidersModel provider = this.cbProviders.getSelectionModel().getSelectedItem();
    	SizesModel sizes = this.cbSizes.getSelectionModel().getSelectedItem();
    	
    	this.product.setDescription(this.txtDescription.getText());
    	this.product.setCategoryId(category.getId());
    	this.product.setMaterialId(material.getId());
    	this.product.setProviderId(provider.getId());
    	this.product.setSizeId(sizes.getId());
    	this.product.setPrice(Float.parseFloat(this.txtPrice.getText()));
    	
    	try {
			this.productsController.update(this.product);
			SuccessMessage.show("Produto atualizada com sucesso");
			this.handleCancel();
		} catch (Exception e) {
			e.printStackTrace();
			WarningMessage.show("Ocorreu um erro ao atualizar o produto. Por favor, tente novamente");
		}
    }

	
	public void initData(ProductsModel product) {
		this.product = product;
		this.newProduct = false;
		this.initialLoad();
		
		this.txtPrice.setText(String.valueOf(product.getPrice()));
		this.txtDescription.setText(this.product.getDescription());
		this.btnSave.setText("Atualizar");
	}

}
