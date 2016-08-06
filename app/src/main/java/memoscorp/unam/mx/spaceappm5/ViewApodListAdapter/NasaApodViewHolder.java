package memoscorp.unam.mx.spaceappm5.ViewApodListAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import memoscorp.unam.mx.spaceappm5.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.img_holder) ImageView imgApodHolder;
    @BindView(R.id.txt_title_holder) TextView titleApodHolder;

    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
