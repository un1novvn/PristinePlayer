package cn.org.unk.musicapp;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import cn.org.unk.musicapp.db.DatabaseClient;
import cn.org.unk.musicapp.db.entity.Song;
import cn.org.unk.musicapp.db.entity.SongList;
import cn.org.unk.musicapp.db.entity.SongListItem;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Util {
    public static void scanAllMusic(Context context){
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC +" != 0";
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
        HashMap<String, List<Song>> map = new HashMap<>();
        while(cursor.moveToNext()){
            Song song = new Song(UUID.randomUUID().toString(),cursor.getString(0),cursor.getString(1),cursor.getString(2));
            File file = new File(song.getPath());
            if(file.exists()){
                String songListName = file.getParentFile().getName();
                if(map.containsKey(songListName)){
                    map.get(songListName).add(song);
                }else{
                    map.put(songListName,new ArrayList<>(30));
                }
            }
        }

        ArrayList<SongList> songLists = new ArrayList<>(32);
        ArrayList<Song> songs = new ArrayList<>(512);
        ArrayList<SongListItem> songListItems = new ArrayList<>(512);

        for (String songListName : map.keySet()) {
            SongList songList = new SongList(UUID.randomUUID().toString(), songListName);
            songLists.add(songList);
            for (Song song : map.get(songListName)) {
                SongListItem songListItem = new SongListItem(UUID.randomUUID().toString(),song.getId(),songList.getId());
                songListItems.add(songListItem);
                songs.add(song);
            }
        }
        Thread thread = new Thread(() -> {
            DatabaseClient client = DatabaseClient.getInstance(context);

            client.deleteAllSong();
            client.deleteAllSongListItem();
            client.deleteAllSongList();

            client.addSong(songs);
            client.addSongList(songLists);
            client.addSongListItem(songListItems);

        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
