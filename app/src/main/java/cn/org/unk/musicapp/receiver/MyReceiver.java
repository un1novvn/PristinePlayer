package cn.org.unk.musicapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import cn.org.unk.musicapp.MyApplication;
import cn.org.unk.musicapp.service.MusicPlayService;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("onReceive!!!");
        MusicPlayService musicPlayService = ((MyApplication) context.getApplicationContext()).getMusicPlayService();
        if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
            KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if (event != null && event.getAction() == KeyEvent.ACTION_DOWN) {
                int keyCode = event.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.KEYCODE_MEDIA_PLAY:
                        System.out.println("KEYCODE_MEDIA_PLAY");
                        musicPlayService.pausePlay();
                        break;
                    case KeyEvent.KEYCODE_MEDIA_PAUSE:
                        System.out.println("KEYCODE_MEDIA_PAUSE");
                        musicPlayService.pausePlay();
                        break;
                    case KeyEvent.KEYCODE_MEDIA_NEXT:
                        musicPlayService.playNextSong();
                        break;
                    case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                        musicPlayService.playPreviousSong();
                        break;
                }
            }
        }
    }
}
