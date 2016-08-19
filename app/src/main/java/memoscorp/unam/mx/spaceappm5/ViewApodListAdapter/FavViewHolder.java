package memoscorp.unam.mx.spaceappm5.ViewApodListAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import memoscorp.unam.mx.spaceappm5.R;
import memoscorp.unam.mx.spaceappm5.model.FavoritesModel;
import memoscorp.unam.mx.spaceappm5.model.Photo;

/**
 * Created by GuillermoAB on 17/08/2016.
 */
public class FavViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.img_holder) SimpleDraweeView imgApodHolder;
    @BindView(R.id.txt_title_holder) TextView titleApodHolder;
    //Estas son las varfiables para implementar el click
    private FavAdapter.OnItemClickListener onItemListener;
    private FavoritesModel favoritesModel;
    public FavViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void setItemClick(FavoritesModel favoritesModel, FavAdapter.OnItemClickListener onItemListener){
        this.favoritesModel = favoritesModel;
        this.onItemListener = onItemListener;
    }
    @OnClick(R.id.img_holder)
    public void onViewClick(View view){
        if(onItemListener!=null){
            onItemListener.onItemClick(favoritesModel);
        }
    }
}
