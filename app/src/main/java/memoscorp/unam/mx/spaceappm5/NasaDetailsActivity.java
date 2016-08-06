package memoscorp.unam.mx.spaceappm5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alumno on 06/08/2016.
 */
public class NasaDetailsActivity extends AppCompatActivity {
    @BindView(R.id.img_details) SimpleDraweeView imagen;
    @BindView(R.id.txt_date_details) TextView fecha;
    @BindView(R.id.txt_urlimg_details) TextView UrlImg;
    @BindView(R.id.txt_rover_details) TextView RoverName;
    @BindView(R.id.txt_numphotos_details) TextView NumFotos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasadetails);
        ButterKnife.bind(this);

        if(getIntent().getExtras()!=null) {
            imagen.setImageURI(getIntent().getStringExtra("url"));
            fecha.setText(getIntent().getStringExtra("fecha"));
            UrlImg.setText(getIntent().getStringExtra("url"));
            RoverName.setText(getIntent().getStringExtra("rovername"));
            //NumFotos.setText(getIntent().getIntExtra("numphotos",0));
        }else{
            Toast.makeText(getApplicationContext(),"Error al recibir datos!!",Toast.LENGTH_SHORT).show();
        }
    }
}
