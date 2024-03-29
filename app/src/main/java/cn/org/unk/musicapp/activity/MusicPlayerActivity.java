package cn.org.unk.musicapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.org.unk.musicapp.MyApplication;

import cn.org.unk.musicapp.R;

import cn.org.unk.musicapp.service.MusicPlayService;

import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {

    TextView titleTv,currentTimeTv,totalTimeTv;
    SeekBar seekBar;
    ImageView pausePlay,nextBtn,previousBtn,musicIcon,playMode;
    int x=0;

    Toast currentToast;
    int currentIndex;

    MusicPlayService musicPlayService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music_player);

        titleTv = findViewById(R.id.song_title);
        currentTimeTv = findViewById(R.id.current_time);
        totalTimeTv = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        nextBtn = findViewById(R.id.next);
        previousBtn = findViewById(R.id.previous);
        musicIcon = findViewById(R.id.music_icon_big);
        playMode = findViewById(R.id.play_mode);

        titleTv.setSelected(true);
        musicPlayService = ((MyApplication)getApplicationContext()).getMusicPlayService();


        pausePlay.setOnClickListener(v-> {
            musicPlayService.pausePlay();
            flushResources();
        });
        nextBtn.setOnClickListener(v-> {
            musicPlayService.playNextSong();
            flushResources();
        });
        previousBtn.setOnClickListener(v-> {
            musicPlayService.playPreviousSong();
            flushResources();
        });

        showModeImage();

        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(currentIndex != musicPlayService.getCurrentIndex()){
                    flushResources();
                }
                seekBar.setProgress(musicPlayService.getCurrentPosition());
                currentTimeTv.setText(convertToMMSS(musicPlayService.getCurrentPosition()+""));
                if(musicPlayService.isPlaying()){
                    pausePlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                    musicIcon.setRotation(x++);
                }else{
                    pausePlay.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                    musicIcon.setRotation(0);
                }



                new Handler().postDelayed(this,100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(musicPlayService!=null && fromUser){
                    musicPlayService.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playMode.setOnClickListener(v->{
            int currentMode = musicPlayService.getCurrentMode();
            currentMode += 1;
            currentMode %= 3;
            musicPlayService.setCurrentMode(currentMode);
            showModeImage();
            currentToast.show();
        });

        flushResources();

    }

    void showModeImage(){
        if(currentToast != null) currentToast.cancel();
        switch (musicPlayService.getCurrentMode()){
            case MusicPlayService.PLAY_MODE_RANDOM:
                currentToast = Toast.makeText(getApplicationContext(), "随机播放", Toast.LENGTH_SHORT);
                playMode.setImageResource(R.drawable.play_mode_random);
                break;
            case MusicPlayService.PLAY_MODE_SEQUENCE:
                currentToast = Toast.makeText(getApplicationContext(), "顺序播放", Toast.LENGTH_SHORT);
                playMode.setImageResource(R.drawable.play_mode_nomal);
                break;
            case MusicPlayService.PLAY_MODE_REPEAT:
                currentToast = Toast.makeText(getApplicationContext(), "单曲循环", Toast.LENGTH_SHORT);
                playMode.setImageResource(R.drawable.play_mode_repeat);
                break;
        }
    }

    /*
        刷新页面元素，刷新进度条
     */
    void flushResources(){
        currentIndex = musicPlayService.getCurrentIndex();
        titleTv.setText(musicPlayService.getCurrentSong().getName());
        totalTimeTv.setText(convertToMMSS(musicPlayService.getCurrentSong().getDuration()));
        seekBar.setMax(musicPlayService.getDuration());

    }

    @Override
    protected void onDestroy() {
        System.out.println("[unk] MusicPlayActivity destroy！");
        super.onDestroy();

    }


    public static String convertToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }
}