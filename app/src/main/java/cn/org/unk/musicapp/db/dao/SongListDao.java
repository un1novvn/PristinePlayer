package cn.org.unk.musicapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import cn.org.unk.musicapp.db.entity.SongList;

import java.util.List;

@Dao
public interface SongListDao {
    @Query("SELECT * FROM song_lists")
    List<SongList> getAll();

    @Query("SELECT * FROM song_lists where isLocal= :isLocal")
    List<SongList> getAll(boolean isLocal);

    @Query("SELECT * FROM song_lists where id= :id")
    SongList getById(String id);


    @Query("SELECT * FROM song_lists where songListName= :name")
    SongList getByName(String name);

    @Insert
    void insert(SongList songList);
    @Insert
    void insert(List<SongList> songLists);

    @Query("delete from song_lists where id = :listId")
    void delete(String listId);

    @Query("delete from song_lists")
    void delete();
    @Delete
    void delete(List<SongList> songLists);

    @Update
    void update(SongList songList);

    @Update
    void update(List<SongList> songLists);
}
