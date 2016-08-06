package memoscorp.unam.mx.spaceappm5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import memoscorp.unam.mx.spaceappm5.ViewApodListAdapter.NasaApodAdapter;
import memoscorp.unam.mx.spaceappm5.data.ApodService;
import memoscorp.unam.mx.spaceappm5.data.Data;
import memoscorp.unam.mx.spaceappm5.model.ModelMarRoverPhotos;
import memoscorp.unam.mx.spaceappm5.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 05/08/2016.
 */
public class RecyclerViewActivity extends AppCompatActivity{
    @BindView(R.id.rv_listMars) RecyclerView marsRoverListRecycler;
    String img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.VERTICAL);
        //marsRoverListRecycler.setLayoutManager(linearLayoutManager);
        marsRoverListRecycler.setLayoutManager(gridLayoutManager);

        //Esto para el Listener
        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Photo photo){

                Intent intent = new Intent(RecyclerViewActivity.this,NasaDetailsActivity.class);
                intent.putExtra("url",photo.getImgSrc());
                intent.putExtra("fecha",photo.getEarthDate());
                intent.putExtra("rovername",photo.getRover().getName());
                intent.putExtra("numphotos",photo.getRover().getTotalPhotos());
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),photo.getImgSrc(),Toast.LENGTH_SHORT).show();
                Log.d("PHOTO",photo.getImgSrc());
            }
        });

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<ModelMarRoverPhotos> modelMarRoverPhotosCall = apodService.getMarsRoverPhotos(1000,"7E4dAclCSWtztvWfQD94rji9VWWUKznWz0rJyR9J");

        modelMarRoverPhotosCall.enqueue(new Callback<ModelMarRoverPhotos>() {
            @Override
            public void onResponse(Call<ModelMarRoverPhotos> call, Response<ModelMarRoverPhotos> response) {
                //marsRoverListRecycler.setAdapter(new NasaApodAdapter(response.body().getPhotos()));
                //Log.d("modelMarRoverPhotosCall",response.body().getPhotos().get(0).getImgSrc().toString());
                //Ahora para el Listener sigue lo siguiente
                nasaApodAdapter.setMarsPhotos(response.body().getPhotos());
                marsRoverListRecycler.setAdapter(nasaApodAdapter);
            }

            @Override
            public void onFailure(Call<ModelMarRoverPhotos> call, Throwable t) {

            }
        });
    }
}
