package com.example.animebookfragment.View;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.animebookfragment.Adapter.AnimeAdapter;
import com.example.animebookfragment.Model.Anime;
import com.example.animebookfragment.R;
import com.example.animebookfragment.RoomDB.AnimeDAO;
import com.example.animebookfragment.RoomDB.AnimeDatabase;
import com.example.animebookfragment.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private AnimeAdapter adapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private AnimeDAO animeDAO;
    private AnimeDatabase db;
    private Anime anime;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getActivity().getApplicationContext(), AnimeDatabase.class, "Anime").build();
        animeDAO = db.animeDAO();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toolbarMain.setTitle("Anime Book");
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbarMain);

        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.anime_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentDirections.ActionMainFragmentToAnimeFragment action = MainFragmentDirections.actionMainFragmentToAnimeFragment("new");
                Navigation.findNavController(view).navigate(action);
            }
        });
        getData();
    }

    private void getData(){
        compositeDisposable.add(animeDAO.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(MainFragment.this::handleResponse));
    }

    private void handleResponse(List<Anime> animeList){
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        adapter = new AnimeAdapter(getActivity().getApplicationContext(), animeList);
        binding.rv.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}