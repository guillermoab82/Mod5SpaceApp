package memoscorp.unam.mx.spaceappm5.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import memoscorp.unam.mx.spaceappm5.R;
import memoscorp.unam.mx.spaceappm5.data.ApodService;
import memoscorp.unam.mx.spaceappm5.data.Data;
import memoscorp.unam.mx.spaceappm5.model.ModelAPOD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by GuillermoAB on 12/08/2016.
 */
public class FragmentTodayAPOD extends Fragment {

    @BindView(R.id.txt_date) TextView txtDate;
    @BindView(R.id.txt_title) TextView txtTitle;
    @BindView(R.id.txt_explanation) TextView txtExplanation;
    @BindView(R.id.img_image) ImageView img;
    String img_url_share;

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
        final View view = inflater.inflate(R.layout.todayapodfragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<ModelAPOD> callApodService = apodService.getTodayApod();


        callApodService.enqueue(new Callback<ModelAPOD>() {
            @Override
            public void onResponse(Call<ModelAPOD> call, Response<ModelAPOD> response) {
                txtDate.setText(response.body().getDate().toString());
                txtTitle.setText(response.body().getTitle().toString());
                txtExplanation.setText(response.body().getExplanation().toString());
                Picasso.with(getActivity().getApplicationContext()).load(response.body().getUrl()).into(img);
                img_url_share=response.body().getUrl();
                Log.d("ModelAPOD",response.body().getTitle());
            }

            @Override
            public void onFailure(Call<ModelAPOD> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apod_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share_today_apod:
                //En este caso como estamos en un fragment no se tiene el metodo findViewById
                //por tal motivo lo sustituimos con getView()
                //Snackbar.make(getView(),"Vamos a compartir",Snackbar.LENGTH_SHORT).show();
                shareText(getResources().getText(R.string.msj_share_image)+" "+img_url_share);
                return true;
            /*case R.id.add_favorites:
                Snackbar.make(getView(),"Agregado a favritos",Snackbar.LENGTH_SHORT).show();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void shareText(String text){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(shareIntent,getResources().getText(R.string.txtShare)));
    }
}
