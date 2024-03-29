package cn.org.unk.musicapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cn.org.unk.musicapp.db.dao.SongDao;
import cn.org.unk.musicapp.db.dao.SongListDao;
import cn.org.unk.musicapp.db.dao.SongListItemDao;
import cn.org.unk.musicapp.db.entity.Song;
import cn.org.unk.musicapp.db.entity.SongList;
import cn.org.unk.musicapp.db.entity.SongListItem;

@Database(entities = {Song.class, SongList.class,SongListItem.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SongDao songDAO();
    public abstract SongListDao songListDao();
    public abstract SongListItemDao songListItemDao();
}
