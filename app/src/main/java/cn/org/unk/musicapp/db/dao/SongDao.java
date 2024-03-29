package cn.org.unk.musicapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import cn.org.unk.musicapp.db.entity.Song;

import java.util.List;

@Dao
public interface SongDao {
    @Query("SELECT * FROM songs")
    List<Song> getAll();
    @Insert
    void insert(Song song);
    @Insert
    void insert(List<Song> song);

    @Query("SELECT * FROM songs where id= :id")
    Song getById(String id);

    @Query("select a.id,a.name,a.path,a.duration from songs a inner join song_list_items b on b.songId = a.id where b.listId = :listId")
    List<Song> getByListId(String listId);


    @Query("SELECT * FROM songs where name= :name")
    Song getByName(String name);

    @Query("DELETE FROM songs where id = :id")
    void delete(String id);

    @Query("DELETE FROM songs")
    void delete();
    @Delete
    void delete(List<Song> songs);

    @Update
    void update(Song song);
    @Update
    void update(List<Song> song);
}
