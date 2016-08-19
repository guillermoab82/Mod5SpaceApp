package memoscorp.unam.mx.spaceappm5.ViewApodListAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import memoscorp.unam.mx.spaceappm5.R;
import memoscorp.unam.mx.spaceappm5.model.FavoritesModel;

/**
 * Created by GuillermoAB on 17/08/2016.
 */
public class FavAdapter extends RecyclerView.Adapter<FavViewHolder> {
    private List<FavoritesModel> marsFav;
    private OnItemClickListener onItemClickListener;

    public FavAdapter(){}
    public FavAdapter(List<FavoritesModel> favoritesModelList){
        this.marsFav = favoritesModelList;
    }

    @Override
    public FavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_view_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(FavViewHolder holder, int position) {
        FavoritesModel favoritesModel = marsFav.get(position);
        holder.imgApodHolder.setImageURI(favoritesModel.cImage_URI);
        holder.titleApodHolder.setText(favoritesModel.cEarth_Date);
        holder.setItemClick(favoritesModel,onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setMarsFav(List<FavoritesModel> marsFav){
        this.marsFav = marsFav;
    }

    @Override
    public int getItemCount() {
        return marsFav!=null?marsFav.size():0;
    }
    public interface OnItemClickListener{
        void onItemClick(FavoritesModel favoritesModel);
    }
}
