package memoscorp.unam.mx.spaceappm5.data;

import memoscorp.unam.mx.spaceappm5.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alumno on 30/07/2016.
 */
public class Data {
    //La clase se genero con el objetivo poder crear instancias del objeto más rápido.
    public static Retrofit getRetrofitInstance(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Esto nos ayuda a generar logs para ver lo que esta pasando al momento de obtener la JSON
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(httpLoggingInterceptor);
        //Esto nos ayuda con los buffers de al momento de obtener el JSON.

        return new Retrofit.Builder().baseUrl(BuildConfig.URL)
                .addConverterFactory(GsonConverterFactory.create())//Esto nos ayuda a transformar el
                //texto plano de JSON a un objeto de java POJO
                .client(okHttpClient.build())
                .build();
    }
}
