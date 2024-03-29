package cn.org.unk.musicapp.db.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "song_lists")
public class SongList implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;

    private String songListName;

    private boolean isLocal = true;

    @Override
    public String toString() {
        return "SongList{" +
                "id='" + id + '\'' +
                ", songListName='" + songListName + '\'' +
                ", isLocal=" + isLocal +
                '}';
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public SongList() {
    }


    @Ignore
    public SongList(@NonNull String id, String songListName) {
        this.id = id;
        this.songListName = songListName;
    }



}
