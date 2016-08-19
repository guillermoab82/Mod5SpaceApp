package memoscorp.unam.mx.spaceappm5.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import memoscorp.unam.mx.spaceappm5.FavDetailsActivity;
import memoscorp.unam.mx.spaceappm5.R;
import memoscorp.unam.mx.spaceappm5.ViewApodListAdapter.FavAdapter;
import memoscorp.unam.mx.spaceappm5.model.FavoritesModel;
import memoscorp.unam.mx.spaceappm5.sql.AppDataSource;

/**
 * Created by GuillermoAB on 17/08/2016.
 */
public class FragmentFavorites extends Fragment {
    @BindView(R.id.rv_favoriteListing)
    RecyclerView marsRoverListRecycler_Favorites;
    String img;
    private AppDataSource appDataSource;
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
        final View view = inflater.inflate(R.layout.favoritesfagment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.VERTICAL);

        marsRoverListRecycler_Favorites.setLayoutManager(gridLayoutManager);
        final FavAdapter favAdapter = new FavAdapter();
        favAdapter.setOnItemClickListener(new FavAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FavoritesModel favoritesModel) {
                Intent intent = new Intent(getActivity(),FavDetailsActivity.class);
                intent.putExtra("favPhoto",favoritesModel);
                startActivity(intent);
            }
        });
        appDataSource = new AppDataSource(getActivity());
        List<FavoritesModel> favoritesModelList = appDataSource.getAllFAV();
        if(!favoritesModelList.isEmpty()) {
            favAdapter.setMarsFav(favoritesModelList);
            marsRoverListRecycler_Favorites.setAdapter(favAdapter);
        }else{
            Snackbar.make(getView(),"No hay datos agregados a favoritos",Snackbar.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),getResources().getText(R.string.NoApps),Toast.LENGTH_SHORT).show();
        }
    }

}
