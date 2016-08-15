package memoscorp.unam.mx.spaceappm5;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import memoscorp.unam.mx.spaceappm5.ViewApodListAdapter.NasaApodAdapter;
import memoscorp.unam.mx.spaceappm5.data.ApodService;
import memoscorp.unam.mx.spaceappm5.data.Data;
import memoscorp.unam.mx.spaceappm5.fragments.FragmentListing;
import memoscorp.unam.mx.spaceappm5.fragments.FragmentTodayAPOD;
import memoscorp.unam.mx.spaceappm5.model.ModelMarRoverPhotos;
import memoscorp.unam.mx.spaceappm5.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 05/08/2016.
 */
public class RecyclerViewActivity extends AppCompatActivity{
    /*@BindView(R.id.rv_listMars) RecyclerView marsRoverListRecycler;
    @BindView(R.id.toolBar) Toolbar Bar;
    String img;*/
    @BindView(R.id.listing_toolbar) Toolbar toolbar;
    @BindView(R.id.listing_navigation_view) NavigationView navigationView;
    @BindView(R.id.listing_navigation_drawer) DrawerLayout drawerLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_navigation_activity);
        ButterKnife.bind(this);
        getFBUserInfo();
        /** // Este código nos permite obtener el key has de la aplicación

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.facebook.samples.hellofacebook",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        } **/

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.today_apod_item:
                        getFragmentManager().beginTransaction().replace(R.id.main_container,new FragmentTodayAPOD()).commit();
                        //Snackbar.make(findViewById(android.R.id.content),"Today",Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.mars_rover_item:
                        getFragmentManager().beginTransaction().replace(R.id.main_container,new FragmentListing()).commit();
                        break;
                    case R.id.favorite_item:
                        break;
                }
                return false;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name){
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
            }
            public void onDrawerOpen(View drawerView){
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //setSupportActionBar(Bar);

/*
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
                /**********************************************
                 Esto se hace con un intent normal, lo de más
                 abajo se hace pasando un obejto serializable,
                 para eso en el modelo hay que agregar "implements Seralizable"
                intent.putExtra("url",photo.getImgSrc());
                intent.putExtra("fecha",photo.getEarthDate());
                intent.putExtra("rovername",photo.getRover().getName());
                intent.putExtra("numphotos",photo.getRover().getTotalPhotos());/
                intent.putExtra("photos",photo);
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
        });*/
    }
    private void getFBUserInfo(){
        GraphRequest request=GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try{
                    SimpleDraweeView userImage = (SimpleDraweeView) findViewById(R.id.UsrFBPhoto);
                    userImage.setImageURI("http://graph.facebook.com/"+object.getString("id")+"/picture?type=large");
                    TextView userName = (TextView) findViewById(R.id.UsrFBName);
                    userName.setText(object.getString("name"));
                    Log.d("name",object.getString("name"));
                    Log.d("id",object.getString("id"));
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        request.executeAsync();
    }
}
