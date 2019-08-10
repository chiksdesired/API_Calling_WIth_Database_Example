package info.androidhive.materialdesign.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tops on 4/26/2017.
 */

public class ApiClient {


    public static final String BASE_URL_GET = "https://api.androidhive.info/";
   //public static final String BASE_URL_GET = "https://stark-spire-93433.herokuapp.com/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = null;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_GET)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
