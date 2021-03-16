package main.javafx.models;

public class SizesModel {
	private Integer id;
	private String size;
	private float width;
	private float height;
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	
	public String toString() {
		String name = this.getSize() + " | Altura: " + this.getHeight() +  " | Largura: " + this.getWidth();
		return name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
