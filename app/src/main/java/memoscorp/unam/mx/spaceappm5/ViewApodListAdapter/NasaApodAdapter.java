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

    //private  List<Photo> apods;
    private List<Photo> marsPhotos;
    private OnItemClickListener onItemClickListener;

    public NasaApodAdapter(){}
    public NasaApodAdapter(List<Photo> apods){this.marsPhotos = apods;}

    @Override
    public  NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_view_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position) {
        //Photo modelAPOD = apods.get(position);
        Photo photo = marsPhotos.get(position);
        //Picasso.with(holder.imgApodHolder.getContext()).load(modelAPOD.getImgSrc()).into(holder.imgApodHolder);
        //Ahora con Fresco
        //holder.imgApodHolder.setImageURI(modelAPOD.getImgSrc());
        holder.imgApodHolder.setImageURI(photo.getImgSrc());
        //holder.titleApodHolder.setText(modelAPOD.getCamera().getFullName());
        holder.titleApodHolder.setText(photo.getEarthDate());
        holder.setItemClick(photo,onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setMarsPhotos(List<Photo> marsPhotos){
        this.marsPhotos = marsPhotos;
    }

    @Override
    public int getItemCount() {
        return marsPhotos != null?marsPhotos.size():0;
    }
    //Estamos implementando el método del OnItemClickListener para el holder
    //y detectar cuando el usr hace click en la imagen
    public interface OnItemClickListener{
        void onItemClick(Photo photo);
    }
}
