package cn.org.unk.musicapp.bottomSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.Nullable;

import cn.org.unk.musicapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class NewSongListBottomSheet extends BottomSheetDialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL,R.style.BottomSheetDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottomsheet_new_songllist, container, false);
        EditText editText = (EditText)view.findViewById(R.id.bottomsheet_new_songlist_songListName);
        view.findViewById(R.id.bottomsheet_new_songlist_submit).setOnClickListener(v->{
            String s = editText.getText().toString();
            System.out.println("新建歌单: "+s);

        });
        return view;
    }
}
