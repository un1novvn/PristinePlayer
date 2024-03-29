package cn.org.unk.musicapp.db;

import android.content.Context;
import androidx.room.Room;

import cn.org.unk.musicapp.db.entity.Song;
import cn.org.unk.musicapp.db.entity.SongList;
import cn.org.unk.musicapp.db.entity.SongListItem;

import java.util.List;

public class DatabaseClient {
    private static DatabaseClient mInstance;
    private final AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "app_database").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public List<Song> getAllSongs() {
        return appDatabase.songDAO().getAll();
    }

    public Song getSongById(String id){
        return appDatabase.songDAO().getById(id);
    }

    public List<Song> getSongByListId(String listId){
        return appDatabase.songDAO().getByListId(listId);
    }

    public List<SongList> getAllList(){
        return appDatabase.songListDao().getAll();
    }

    public List<SongList> getAllList(boolean isLocal){
        return appDatabase.songListDao().getAll(isLocal);
    }

    public SongList getSongListById(String id){
        return appDatabase.songListDao().getById(id);
    }

    public List<SongListItem> getAllSongListItem(){
        return appDatabase.songListItemDao().getAll();
    }

    public SongListItem getSongListItemById(String id){
        return appDatabase.songListItemDao().getById(id);
    }
    public List<SongListItem> getSongListItemByListId(String id){
        return appDatabase.songListItemDao().getByListId(id);
    }

    public void addSong(Song song) {
        appDatabase.songDAO().insert(song);
    }
    public void addSong(List<Song> songs) {
        appDatabase.songDAO().insert(songs);
    }
    public void addSongList(SongList songList) {
        appDatabase.songListDao().insert(songList);
    }
    public void addSongList(List<SongList> songLists) {
        appDatabase.songListDao().insert(songLists);
    }
    public void addSongListItem(SongListItem songListItem) {
        appDatabase.songListItemDao().insert(songListItem);
    }
    public void addSongListItem(List<SongListItem> songListItems) {
        appDatabase.songListItemDao().insert(songListItems);
    }


    public void deleteSong(String id) {
        appDatabase.songDAO().delete(id);
    }
    public void deleteAllSong() {
        appDatabase.songDAO().delete();
    }
    public void deleteSong(List<Song> songs) {
        appDatabase.songDAO().delete(songs);
    }

    public void deleteSongList(String id) {
        appDatabase.songListDao().delete(id);
    }
    public void deleteAllSongList() {
        appDatabase.songListDao().delete();
    }
    public void deleteSongList(List<SongList> songLists) {
        appDatabase.songListDao().delete(songLists);
    }


    public void deleteSongListItem(String id) {
        appDatabase.songListItemDao().delete(id);
    }
    public void deleteAllSongListItem() {
        appDatabase.songListItemDao().delete();
    }
    public void deleteSongListItem(List<SongListItem> songListItems) {
        appDatabase.songListItemDao().delete(songListItems);
    }

    public void updateSong(Song song){
        appDatabase.songDAO().update(song);
    }

    public void updateSong(List<Song> songs){
        appDatabase.songDAO().update(songs);
    }

    public void updateSongList(SongList songList){
        appDatabase.songListDao().update(songList);
    }

    public void updateSongList(List<SongList> songLists){
        appDatabase.songListDao().update(songLists);
    }

    public void updateSongListItem(List<SongListItem> songListItems){
        appDatabase.songListItemDao().update(songListItems);
    }

    public void updateSongListItem(SongListItem songListItem){
        appDatabase.songListItemDao().update(songListItem);
    }


}
