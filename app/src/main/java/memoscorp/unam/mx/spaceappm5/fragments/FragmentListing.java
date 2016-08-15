package memoscorp.unam.mx.spaceappm5.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import memoscorp.unam.mx.spaceappm5.NasaDetailsActivity;
import memoscorp.unam.mx.spaceappm5.R;
import memoscorp.unam.mx.spaceappm5.ViewApodListAdapter.NasaApodAdapter;
import memoscorp.unam.mx.spaceappm5.data.ApodService;
import memoscorp.unam.mx.spaceappm5.data.Data;
import memoscorp.unam.mx.spaceappm5.model.ModelMarRoverPhotos;
import memoscorp.unam.mx.spaceappm5.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by GuillermoAB on 12/08/2016.
 */
public class FragmentListing extends Fragment {
    @BindView(R.id.rv_listMars) RecyclerView marsRoverListRecycler;
    String img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(getArguments()!=null){

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.listingfragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.VERTICAL);
        //marsRoverListRecycler.setLayoutManager(linearLayoutManager);
        marsRoverListRecycler.setLayoutManager(gridLayoutManager);

        //Esto para el Listener
        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Photo photo){

                Intent intent = new Intent(getActivity(),NasaDetailsActivity.class);
                /**********************************************
                 Esto se hace con un intent normal, lo de más
                 abajo se hace pasando un obejto serializable,
                 para eso en el modelo hay que agregar "implements Seralizable"
                 intent.putExtra("url",photo.getImgSrc());
                 intent.putExtra("fecha",photo.getEarthDate());
                 intent.putExtra("rovername",photo.getRover().getName());
                 intent.putExtra("numphotos",photo.getRover().getTotalPhotos());*/
                 intent.putExtra("photos",photo);
                 startActivity(intent);
                 //Toast.makeText(getApplicationContext(),photo.getImgSrc(),Toast.LENGTH_SHORT).show();
                 //Log.d("PHOTO",photo.getImgSrc());
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.listing_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_favorites_listing:
                Snackbar.make(getView(),"Agregamos a favoritos",Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
