package memoscorp.unam.mx.spaceappm5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import memoscorp.unam.mx.spaceappm5.data.ApodService;
import memoscorp.unam.mx.spaceappm5.data.Data;
import memoscorp.unam.mx.spaceappm5.model.ModelAPOD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    /*private TextView txtDate;
    private TextView txtTitle;
    private TextView txtExplanation;
    private ImageView img;*/
    @BindView(R.id.txt_date) TextView txtDate;
    @BindView(R.id.txt_title) TextView txtTitle;
    @BindView(R.id.txt_explanation) TextView txtExplanation;
    @BindView(R.id.img_image) ImageView img;
    @OnClick(R.id.btn_enviar)
    public void btn_enviar(){
        Intent intent = new Intent(getApplicationContext(),RecyclerViewActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //Aqui se genera una instancia de la interfaz utilizando retrofit ya que por defecto no se
        //se puede instancia una interfaz o una clase abstracta

        //Esto lo sustituimos por el ButterKnife
        /*txtDate = (TextView) findViewById(R.id.txt_date);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtExplanation = (TextView) findViewById(R.id.txt_explanation);
        img = (ImageView) findViewById(R.id.img_image);*/

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<ModelAPOD> callApodService = apodService.getTodayApod();


        callApodService.enqueue(new Callback<ModelAPOD>() {
            @Override
            public void onResponse(Call<ModelAPOD> call, Response<ModelAPOD> response) {
                txtDate.setText(response.body().getDate().toString());
                txtTitle.setText(response.body().getTitle().toString());
                txtExplanation.setText(response.body().getExplanation().toString());
                Picasso.with(getApplicationContext()).load(response.body().getUrl()).into(img);
                Log.d("ModelAPOD",response.body().getTitle());
            }

            @Override
            public void onFailure(Call<ModelAPOD> call, Throwable t) {

            }
        });
        //Log.d("build",BuildConfig.BUILD_TYPEUNO);
        //Log.d("URL:",BuildConfig.URL);
    }

}
