package cn.org.unk.musicapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.org.unk.musicapp.R;

import cn.org.unk.musicapp.MyApplication;
import cn.org.unk.musicapp.service.MusicPlayService;

public class BottomBar extends Fragment {

    ImageView pausePlay;
    ImageView nextBtn;
    ImageView previousBtn;

    TextView title;

    MusicPlayService musicPlayService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void flush(){
        if(musicPlayService != null){
            title.setText(musicPlayService.getCurrentSong().getName());
        }else {
            musicPlayService = ((MyApplication)getActivity().getApplication()).getMusicPlayService();
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_bar, container, false);

        nextBtn = view.findViewById(R.id.bottom_bar_next);
        previousBtn = view.findViewById(R.id.bottom_bar_previous);
        pausePlay = view.findViewById(R.id.bottom_bar_pause_play);
        title = view.findViewById(R.id.bottom_bar_title);

        pausePlay.setOnClickListener(v-> {
            musicPlayService.pausePlay();
            flush();
        });
        nextBtn.setOnClickListener(v-> {
            musicPlayService.playNextSong();
            flush();
        });
        previousBtn.setOnClickListener(v-> {
            musicPlayService.playPreviousSong();
            flush();
        });

        if(musicPlayService !=null && musicPlayService.isPlaying()){
            title.setText(musicPlayService.getCurrentSong().getName());
        }

        return view;
    }
}
