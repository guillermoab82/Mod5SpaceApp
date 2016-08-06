package memoscorp.unam.mx.spaceappm5.ViewApodListAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import memoscorp.unam.mx.spaceappm5.R;
import memoscorp.unam.mx.spaceappm5.model.ModelAPOD;
import memoscorp.unam.mx.spaceappm5.model.ModelMarRoverPhotos;
import memoscorp.unam.mx.spaceappm5.model.Photo;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder> {

    private  List<Photo> apods;
    public NasaApodAdapter(List<Photo> apods){this.apods = apods;}

    @Override
    public  NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_view_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position) {
        Photo modelAPOD = apods.get(position);
        Picasso.with(holder.imgApodHolder.getContext()).load(modelAPOD.getImgSrc()).into(holder.imgApodHolder);
        holder.titleApodHolder.setText(modelAPOD.getCamera().getFullName());
    }

    @Override
    public int getItemCount() {
        return apods != null?apods.size():0;
    }
}
