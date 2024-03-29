package cn.org.unk.musicapp.db.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "song_list_items")
public class SongListItem implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;

    private String songId;
    private String listId;

    public SongListItem() {

    }

    @Ignore
    public SongListItem(String id,String songId, String listId) {
        this.id = id;
        this.songId = songId;
        this.listId = listId;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }



    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }
}
