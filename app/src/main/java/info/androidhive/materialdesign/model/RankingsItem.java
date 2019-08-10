package info.androidhive.materialdesign.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class RankingsItem{

	@SerializedName("ranking")
	private String ranking;

	@SerializedName("products")
	private List<ProductsItem> products;

	public void setRanking(String ranking){
		this.ranking = ranking;
	}

	public String getRanking(){
		return ranking;
	}

	public void setProducts(List<ProductsItem> products){
		this.products = products;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}

	@Override
 	public String toString(){
		return 
			"RankingsItem{" + 
			"ranking = '" + ranking + '\'' + 
			",products = '" + products + '\'' + 
			"}";
		}
}