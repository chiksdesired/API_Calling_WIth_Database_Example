package info.androidhive.materialdesign.Constant;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


import java.util.ArrayList;

import info.androidhive.materialdesign.model.Contact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tops on 22-Jun-17.
 * Webservices calls associalted with MainActivity.java
 */

public class ApiHelper {
    public CustomProgressBar customProgressBar;
    WebserviceCallBack callBack;
    private APIInterface apiInterface;
    private APIInterface apiinterfacesplash;
    Context context;

    public ApiHelper(Context context) {
        this.context = context;
        customProgressBar = new CustomProgressBar(context);
        customProgressBar.setCancelable(false);
        apiInterface = ApiClient.getClient().create(APIInterface.class);

    }

    public void GetContact(final WebserviceCallBack callBack) {
        try {
            this.callBack = callBack;
            Call<Contact> response = apiInterface.GetContacts();
            response.enqueue(new Callback<Contact>() {
                @Override
                public void onResponse(Call<Contact> call, Response<Contact> response) {
                /*Process response*/
                    if (response.isSuccessful()) {
                    /*businesss logic*/
                        callBack.onResponse(response.body());
                    } else {
                        Toast.makeText(context, "Something went wrong from server", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Contact> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(context, "Something went wrong from server", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



 

}
