package cn.org.unk.musicapp.bottomSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import cn.org.unk.musicapp.R;
import cn.org.unk.musicapp.activity.ListActivity;
import cn.org.unk.musicapp.db.DatabaseClient;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SongBottomSheet extends BottomSheetDialogFragment {
    String songId;
    int songPosition;
    private SongBottomSheet sheet;

    public SongBottomSheet(String songId,int songPosition) {
        this.songId = songId;
        sheet = this;
        this.songPosition = songPosition;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL,R.style.BottomSheetDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ListActivity activity = (ListActivity)getActivity();
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.bottomsheet_song, container, false);

        DatabaseClient instance = DatabaseClient.getInstance(getContext());


        view.findViewById(R.id.bottomsheet_song_option_delete).setOnClickListener(v->{
            Thread thread = new Thread(() -> {
                instance.deleteSong(songId);
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sheet.dismiss();
            activity.deleteOne(songPosition);
        });

        view.findViewById(R.id.bottomsheet_song_option_addto).setOnClickListener(v -> {
            System.out.println("bottomsheet_song_option_addto");
        });

        return view;
    }
}
