package main.javafx.models;

import java.util.Observable;

public class CategoriesModel extends Observable {
	private Integer id;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		setChanged();
		notifyObservers(description);
	}
	
	@Override
	public String toString() {
		return getDescription();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
