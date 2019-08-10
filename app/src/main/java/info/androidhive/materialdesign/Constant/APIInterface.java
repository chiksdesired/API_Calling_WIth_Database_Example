package info.androidhive.materialdesign.Constant;



import info.androidhive.materialdesign.model.Contact;
import info.androidhive.materialdesign.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tops on 4/26/2017.
 * List of web services, we will use in whole project.
 */

public interface APIInterface {
//users/signup


    @GET("contacts")
    Call<Contact> GetContacts();

    @GET("json")
    Call<Product> GetProducts();


  

}
