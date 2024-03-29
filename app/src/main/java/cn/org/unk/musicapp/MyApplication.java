package cn.org.unk.musicapp;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;

import cn.org.unk.musicapp.receiver.MyReceiver;
import cn.org.unk.musicapp.service.MusicPlayService;

public class MyApplication extends Application {

    private MusicPlayService musicPlayService;

    public void setMusicPlayService(MusicPlayService musicPlayService) {
        this.musicPlayService = musicPlayService;
    }

    public MusicPlayService getMusicPlayService() {
        return musicPlayService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());

        System.out.println("MyApplication create!!");
        System.out.println("registerReceiver！！！");
        MyReceiver myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
        intentFilter.setPriority(10000);
        registerReceiver(myReceiver,intentFilter);

        Intent serviceIntent = new Intent(this, MusicPlayService.class);
        System.out.println("startService");
        startService(serviceIntent);

    }
}
