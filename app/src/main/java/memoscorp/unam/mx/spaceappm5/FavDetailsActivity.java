package memoscorp.unam.mx.spaceappm5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import memoscorp.unam.mx.spaceappm5.model.FavoritesModel;
import memoscorp.unam.mx.spaceappm5.model.Photo;

/**
 * Created by GuillermoAB on 17/08/2016.
 */
public class FavDetailsActivity extends AppCompatActivity {
    @BindView(R.id.img_details) SimpleDraweeView imagen;
    @BindView(R.id.txt_full_name_details) TextView FullName;
    @BindView(R.id.txt_date_details) TextView fecha;
    @BindView(R.id.txt_urlimg_details) TextView UrlImg;
    @BindView(R.id.txt_rover_details) TextView RoverName;
    @BindView(R.id.txt_numphotos_details) TextView NumFotos;
    @BindView(R.id.toolBar_Action) Toolbar Bar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasadetails);
        ButterKnife.bind(this);

        setSupportActionBar(Bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if(getIntent().getExtras()!=null) {
            FavoritesModel favoritesModel = (FavoritesModel) getIntent().getSerializableExtra("favPhoto");
            //Photo fotos = (Photo) getIntent().getSerializableExtra("photos");
            imagen.setImageURI(favoritesModel.cImage_URI.toString());
            FullName.setText(favoritesModel.cFullName.toString());
            fecha.setText(favoritesModel.cEarth_Date.toString());
            UrlImg.setText(favoritesModel.cImage_URI.toString());
            RoverName.setText(favoritesModel.cRover_Name.toString());
            NumFotos.setText(String.valueOf(favoritesModel.nTotal_Photo));

        }else{
            Toast.makeText(getApplicationContext(),getResources().getText(R.string.msj_error_data),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
