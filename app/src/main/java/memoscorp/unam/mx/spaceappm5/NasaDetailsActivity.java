package memoscorp.unam.mx.spaceappm5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import memoscorp.unam.mx.spaceappm5.model.Photo;
import memoscorp.unam.mx.spaceappm5.model.Rover;

/**
 * Created by Alumno on 06/08/2016.
 */
public class NasaDetailsActivity extends AppCompatActivity {
    @BindView(R.id.img_details) SimpleDraweeView imagen;
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
            fecha.setText(fotos.getEarthDate());
            UrlImg.setText(fotos.getImgSrc());
            RoverName.setText(fotos.getRover().getName());
            NumFotos.setText(String.valueOf(fotos.getRover().getTotalPhotos()));
        }else{
            Toast.makeText(getApplicationContext(),"Error al recibir datos!!",Toast.LENGTH_SHORT).show();
        }
    }
}
