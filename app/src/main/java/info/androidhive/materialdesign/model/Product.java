package info.androidhive.materialdesign.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Product {

	@SerializedName("rankings")
	private List<RankingsItem> rankings;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public void setRankings(List<RankingsItem> rankings){
		this.rankings = rankings;
	}

	public List<RankingsItem> getRankings(){
		return rankings;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	@Override
 	public String toString(){
		return 
			"Product{" +
			"rankings = '" + rankings + '\'' + 
			",categories = '" + categories + '\'' + 
			"}";
		}
}