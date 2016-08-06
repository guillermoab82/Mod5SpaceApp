package memoscorp.unam.mx.spaceappm5.ViewApodListAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.OnClick;
import memoscorp.unam.mx.spaceappm5.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import memoscorp.unam.mx.spaceappm5.model.Photo;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.img_holder)
    SimpleDraweeView imgApodHolder;
    @BindView(R.id.txt_title_holder) TextView titleApodHolder;
    //Estas son las varfiables para implementar el click
    private NasaApodAdapter.OnItemClickListener onItemListener;
    private Photo photo;

    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void setItemClick(Photo photo,NasaApodAdapter.OnItemClickListener onItemListener){
        this.photo = photo;
        this.onItemListener = onItemListener;
    }
    @OnClick(R.id.img_holder)
    public void onViewClick(View view){
        if(onItemListener!=null){
            onItemListener.onItemClick(photo);
        }
    }
}
