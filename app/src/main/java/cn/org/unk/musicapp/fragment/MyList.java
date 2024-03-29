package cn.org.unk.musicapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import cn.org.unk.musicapp.R;

import cn.org.unk.musicapp.bottomSheet.NewSongListBottomSheet;

public class MyList extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_songlist, container, false);
        // You can initialize views and set up logic here

        view.findViewById(R.id.fragment_my_songlist_head).setOnClickListener(v->{
            NewSongListBottomSheet newSongListBottomSheet = new NewSongListBottomSheet();
            newSongListBottomSheet.show(getParentFragmentManager(),"123");
        });

        System.out.println("[unk] MyList onCreateView");

        RecyclerView recyclerView = view.findViewById(R.id.recycler_myList);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        ArrayList<String> data = new ArrayList<>();
//        data.add("Item 1");
//        data.add("Item 2");
//        data.add("Item 3");
//        SongListItemAdapter mAdapter = new SongListItemAdapter(data,getContext(), getActivity().getSupportFragmentManager());
//        recyclerView.setAdapter(mAdapter);

        return view;
    }
}
