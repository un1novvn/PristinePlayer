package cn.org.unk.musicapp.bottomSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import cn.org.unk.musicapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SongListBottomSheet extends BottomSheetDialogFragment {

    String listId;

    public SongListBottomSheet(String listId) {
        this.listId = listId;
    }

    public SongListBottomSheet() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL,R.style.BottomSheetDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottomsheet_songlist, container, false);
        view.findViewById(R.id.bottomsheet_songlist_option_delete).setOnClickListener(v->{
            System.out.println("delete list: "+listId);
        });
        return view;
    }
}
