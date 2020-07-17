package com.app.flamingoassignment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.flamingoassignment.Util.Util;
import com.app.flamingoassignment.adapter.ArtistAdapter;
import com.app.flamingoassignment.databinding.FragmentMusicBinding;
import com.app.flamingoassignment.response.Artist;
import com.app.flamingoassignment.response.ArtistResponse;
import com.app.flamingoassignment.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MusicFragment extends Fragment {
    ArtistAdapter artistAdapter;
    RecyclerView recyclerView;
    MainActivity mainActivity;
    FragmentMusicBinding fragmentMusicBinding;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvZmFjZXNvbHV0aW9uLmluXC9kaWJibGVcL3B1YmxpY1wvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU5NDk5MjMyMiwiZXhwIjoxNTk1NTk3MTIyLCJuYmYiOjE1OTQ5OTIzMjIsImp0aSI6IkM5bWtBTDVCU3JWV3UwWE8iLCJzdWIiOjMyLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.Rv7JDdDy2-_-ArnJMNk_gL-uui7Tz9RGuUa6lM8IdJY";


    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMusicBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_music, container, false);
        View view = fragmentMusicBinding.getRoot();
        recyclerView = fragmentMusicBinding.artistrecycler;
        ApiCall();


        return view;
    }


    public void ApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://facesolution.in/dibble/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<ArtistResponse> artistResponseCall = client.artistList("Bearer "+"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvZmFjZXNvbHV0aW9uLmluXC9kaWJibGVcL3B1YmxpY1wvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU5NDk5MjMyMiwiZXhwIjoxNTk1NTk3MTIyLCJuYmYiOjE1OTQ5OTIzMjIsImp0aSI6IkM5bWtBTDVCU3JWV3UwWE8iLCJzdWIiOjMyLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.Rv7JDdDy2-_-ArnJMNk_gL-uui7Tz9RGuUa6lM8IdJY");
        Util.showProgressDialog(getContext());
        artistResponseCall.enqueue(new Callback<ArtistResponse>() {
            @Override
            public void onResponse(Call<ArtistResponse> call, retrofit2.Response<ArtistResponse> response) {
               Util.dismissProgressDialog();
                if (response.body() != null) {
                    List<Artist>artistList = response.body().getArtists();
                    artistAdapter = new ArtistAdapter(artistList,getActivity());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(artistAdapter);
                }else {
                    Toast.makeText(mainActivity, "Api failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArtistResponse> call, Throwable t) {
               Toast.makeText(mainActivity, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });


    }
}