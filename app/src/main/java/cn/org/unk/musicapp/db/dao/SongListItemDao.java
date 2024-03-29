package cn.org.unk.musicapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import cn.org.unk.musicapp.db.entity.SongListItem;

import java.util.List;

@Dao
public interface SongListItemDao {
    @Query("SELECT * FROM song_list_items")
    @Transaction
    List<SongListItem> getAll();

    @Query("SELECT * FROM song_list_items where listId=:listId")
    @Transaction
    List<SongListItem> getByListId(String listId);

//    The return value includes a POJO with a @Relation. It is usually desired to annotate this method with @Transaction to avoid possibility of inconsistent results between the POJO and its relations.
    @Query("SELECT * FROM song_list_items where id= :id")
    @Transaction
    SongListItem getById(String id);

    @Insert
    void insert(SongListItem songListItem);

    @Insert
    void insert(List<SongListItem> songListItem);

    @Query("delete from song_list_items where id = :itemId")
    void delete(String itemId);

    @Query("delete from song_list_items")
    void delete();
    @Delete
    void delete(List<SongListItem> songListItems);

    @Update
    void update(SongListItem songListItem);

    @Update
    void update(List<SongListItem> songListItems);


}
