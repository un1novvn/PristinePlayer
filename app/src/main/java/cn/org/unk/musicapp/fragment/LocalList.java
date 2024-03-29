package cn.org.unk.musicapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.org.unk.musicapp.R;
import cn.org.unk.musicapp.adapter.SongListItemAdapter;
import cn.org.unk.musicapp.db.DatabaseClient;
import cn.org.unk.musicapp.db.entity.SongList;

import java.util.ArrayList;

public class LocalList extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_local_songlist, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recycler_localList);

        new Thread(()->{
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            DatabaseClient client = DatabaseClient.getInstance(getContext());
            ArrayList<SongList> data = (ArrayList)client.getAllList();
            System.out.println(data);
            for (SongList datum : data) {
                System.out.println(datum);
            }
            SongListItemAdapter mAdapter = new SongListItemAdapter(data,getContext(), getActivity().getSupportFragmentManager());
            recyclerView.setAdapter(mAdapter);
        }).start();

        return view;
    }
}
