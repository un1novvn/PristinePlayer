package cn.org.unk.musicapp.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import cn.org.unk.musicapp.MyApplication;
import cn.org.unk.musicapp.db.entity.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MusicPlayService extends Service {
    ArrayList<Song> songsList;
    Song currentSong;

    boolean playing;

    public boolean isPlaying(){
        return playing;
    }

    public void setSongsList(ArrayList<Song> songsList) {
        System.out.println("setSongsList!");
        this.songsList = songsList;
    }

    int currentIndex;
    int duration;
    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    public static final int PLAY_MODE_REPEAT = 0;
    public static final int PLAY_MODE_SEQUENCE = 1;
    public static final int PLAY_MODE_RANDOM = 2;
    int currentMode = PLAY_MODE_SEQUENCE;

    public Song getCurrentSong() {
        return currentSong;
    }

    public int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }

    public void setCurrentMode(int currentMode) {
        this.currentMode = currentMode;
    }

    public int getCurrentMode() {
        return currentMode;
    }

    public MusicPlayService() {

    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getDuration() {
        return duration;
    }

    public void seekTo(int progress){
        mediaPlayer.seekTo(progress);
    }

    static class MyMediaPlayer {
        static MediaPlayer instance;

        public static MediaPlayer getInstance(){
            if(instance == null){
                instance = new MediaPlayer();
            }
            return instance;
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println("onStartCommand");

        ((MyApplication)getApplication()).setMusicPlayService(this);

        mediaPlayer.setOnCompletionListener(v->{
            System.out.println("Compelete");
            System.out.println(getCurrentSong());
            if(currentMode==PLAY_MODE_REPEAT) playMusic(currentIndex);
            else playNextSong();
        });

        return super.onStartCommand(intent, flags, startId);
    }

    public void playMusic(int index) {
        currentIndex = index;
        currentSong = songsList.get(currentIndex);
        playing = true;
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            duration = mediaPlayer.getDuration();
//            seekBar.setProgress(0);
//            seekBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void playNextSong(){

        System.out.println("playNextSong");
        System.out.println(songsList);
        if(currentMode == PLAY_MODE_RANDOM){
            currentIndex = new Random().nextInt(100);
        }else{
            currentIndex += 1;
        }
        currentIndex %=  songsList.size();

        playMusic(currentIndex);

    }

    public void playPreviousSong(){
        currentIndex += songsList.size()-1;
        currentIndex %=  songsList.size();
        playMusic(currentIndex);
    }

    public void pausePlay(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            playing = false;
        }
        else{
            playing = true;
            mediaPlayer.start();
        }

    }
}
