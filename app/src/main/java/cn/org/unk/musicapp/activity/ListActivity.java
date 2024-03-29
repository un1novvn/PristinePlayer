package cn.org.unk.musicapp.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.org.unk.musicapp.R;
import cn.org.unk.musicapp.adapter.SongItemAdapter;
import cn.org.unk.musicapp.db.DatabaseClient;
import cn.org.unk.musicapp.db.entity.Song;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    String listId;
    String listName;
    ArrayList<Song> songList;
    RecyclerView recyclerView;
    private void loadData(){

        listId = getIntent().getStringExtra("listId");
        Thread thread = new Thread(() -> {
            DatabaseClient client = DatabaseClient.getInstance(getApplicationContext());
            songList = (ArrayList) client.getSongByListId(listId);
            listName = client.getSongListById(listId).getSongListName();
        });
        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteOne(int position){
        SongItemAdapter adapter = (SongItemAdapter)recyclerView.getAdapter();
        adapter.removeItem(position);
    }
    public void flushAll(){
        loadData();
        SongItemAdapter adapter = (SongItemAdapter)recyclerView.getAdapter();
        adapter.setSongsList(songList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        loadData();

        TextView title = (TextView) findViewById(R.id.activity_list_title);
        title.setText(listName);
        findViewById(R.id.list_back_button).setOnClickListener(v->{
            finish();
        });

        recyclerView = findViewById(R.id.recycler_music);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        SongItemAdapter mAdapter = new SongItemAdapter(songList,getApplicationContext(),getSupportFragmentManager());
        recyclerView.setAdapter(mAdapter);
    }

}
