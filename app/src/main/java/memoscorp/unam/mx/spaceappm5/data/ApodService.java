package memoscorp.unam.mx.spaceappm5.data;

import memoscorp.unam.mx.spaceappm5.model.ModelAPOD;
import memoscorp.unam.mx.spaceappm5.model.ModelMarRoverPhotos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alumno on 30/07/2016.
 */
public interface ApodService {
    @GET("planetary/apod?api_key=7E4dAclCSWtztvWfQD94rji9VWWUKznWz0rJyR9J")
    Call<ModelAPOD> getTodayApod();//Estamos creando una función o método que vamos a llamara después
    //en nuestro código. Esto se construye con la biblioteca retrofit.

    @GET("planetary/apod")
    Call<ModelAPOD> getTodayApodWithQuery(@Query("api_key") String apiKey);

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    Call<ModelMarRoverPhotos> getMarsRoverPhotos(@Query("sol") int sol, @Query("api_key") String apiKey);

}
