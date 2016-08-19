package memoscorp.unam.mx.spaceappm5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.security.KeyStore;

import butterknife.BindView;
import butterknife.ButterKnife;
import memoscorp.unam.mx.spaceappm5.model.FavoritesModel;
import memoscorp.unam.mx.spaceappm5.model.Photo;
import memoscorp.unam.mx.spaceappm5.model.Rover;
import memoscorp.unam.mx.spaceappm5.sql.AppDataSource;

/**
 * Created by Alumno on 06/08/2016.
 */
public class NasaDetailsActivity extends AppCompatActivity {
    @BindView(R.id.img_details) SimpleDraweeView imagen;
    @BindView(R.id.txt_full_name_details) TextView FullName;
    @BindView(R.id.txt_date_details) TextView fecha;
    @BindView(R.id.txt_urlimg_details) TextView UrlImg;
    @BindView(R.id.txt_rover_details) TextView RoverName;
    @BindView(R.id.txt_numphotos_details) TextView NumFotos;
    @BindView(R.id.toolBar_Action) Toolbar Bar;
    private AppDataSource favDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasadetails);
        ButterKnife.bind(this);

        favDataSource = new AppDataSource(getApplicationContext());
        setSupportActionBar(Bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if(getIntent().getExtras()!=null) {
            /**************************************************************************************
             * Esto se hace cuando se envian valores unitarios lo de más abajo se hace cuando se
             * envían datos como objetos (arrays)
            imagen.setImageURI(getIntent().getStringExtra("url"));
            fecha.setText(getIntent().getStringExtra("fecha"));
            UrlImg.setText(getIntent().getStringExtra("url"));
            RoverName.setText(getIntent().getStringExtra("rovername"));
            NumFotos.setText(String.valueOf(getIntent().getIntExtra("numphotos",0)));
             *************************************************************************************/
            Photo fotos = (Photo) getIntent().getSerializableExtra("photos");
            imagen.setImageURI(fotos.getImgSrc());
            FullName.setText(fotos.getCamera().getFullName());
            fecha.setText(fotos.getEarthDate());
            UrlImg.setText(fotos.getImgSrc());
            RoverName.setText(fotos.getRover().getName());
            NumFotos.setText(String.valueOf(fotos.getRover().getTotalPhotos()));

        }else{
            Toast.makeText(getApplicationContext(),getResources().getText(R.string.msj_error_data),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listing_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_favorites_listing:
                //Snackbar.make(findViewById(android.R.id.content),"Agregamos a favoritos",Snackbar.LENGTH_SHORT).show();
                //Log.d("Compartir","Agregado a Favoritos");
                /*******************************************************
                 * Aquí es donde se va a insertar los datos del favorito
                 * en la base de datos; mediante la llamada a un método.
                 */
                addFavorites();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addFavorites() {
        String img = UrlImg.getText().toString();
        String fullname = FullName.getText().toString();
        String fec = fecha.getText().toString();
        String rover = RoverName.getText().toString();
        String fotos = NumFotos.getText().toString();
        FavoritesModel model = new FavoritesModel(0,img,fullname,fec,rover,Integer.valueOf(fotos));
        favDataSource.saveFav(model);
        Snackbar.make(findViewById(android.R.id.content),getResources().getText(R.string.msjAddFavorites),Snackbar.LENGTH_SHORT).show();
    }
}
