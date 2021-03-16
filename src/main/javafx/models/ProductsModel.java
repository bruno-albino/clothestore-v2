package main.javafx.models;

public class ProductsModel {
	private Integer id;
//	private Materials materials;
//	private Categories categories;
//	private Sizes sizes;
//	private Providers providers;
	
	private int materialId;
	private int categoryId;
	private int sizeId;
	private int providerId;
	
	private String description;
	private float price;
	
//	public Materials getMaterials() {
//		return materials;
//	}
//	
//	public void setMaterials(Materials materials) {
//		this.materials = materials;
//	}
//	
//	public Categories getCategories() {
//		return categories;
//	}
//	
//	public void setCategories(Categories categories) {
//		this.categories = categories;
//	}
//	
//	public Sizes getSizes() {
//		return sizes;
//	}
//	
//	public void setSizes(Sizes sizes) {
//		this.sizes = sizes;
//	}
//	
//	public Providers getProviders() {
//		return providers;
//	}
//
//	public void setProviders(Providers providers) {
//		this.providers = providers;
//	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String toString() {
		return getDescription();
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
