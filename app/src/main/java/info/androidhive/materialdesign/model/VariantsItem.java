package info.androidhive.materialdesign.model;


import com.google.gson.annotations.SerializedName;


public class VariantsItem{

	@SerializedName("color")
	private String color;

	@SerializedName("size")
	private int size;

	@SerializedName("price")
	private int price;

	@SerializedName("id")
	private int id;

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"VariantsItem{" + 
			"color = '" + color + '\'' + 
			",size = '" + size + '\'' + 
			",price = '" + price + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}