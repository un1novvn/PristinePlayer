package cn.org.unk.musicapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.org.unk.musicapp.MyApplication;

import cn.org.unk.musicapp.R;
import cn.org.unk.musicapp.activity.MusicPlayerActivity;
import cn.org.unk.musicapp.bottomSheet.SongBottomSheet;
import cn.org.unk.musicapp.db.entity.Song;
import cn.org.unk.musicapp.service.MusicPlayService;

public class SongItemAdapter extends RecyclerView.Adapter<SongItemAdapter.ViewHolder>{

    ArrayList<Song> songsList;
    Context context;

    public void setSongsList(ArrayList<Song> songsList) {
        this.songsList = songsList;
    }

    public void removeItem(int position){
        songsList.remove(position);
        notifyItemRemoved(position);
    }

    FragmentManager fragmentManager;
    public SongItemAdapter(ArrayList<Song> songsList, Context context, FragmentManager fragmentManager) {
        this.songsList = songsList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item_song,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Song song = songsList.get(position);

        holder.musicName.setText(song.getName());
        holder.item.setOnClickListener(v->{

            MusicPlayService musicPlayService = ((MyApplication) context).getMusicPlayService();
            musicPlayService.setSongsList(songsList);
            musicPlayService.playMusic(position);

            Intent intent = new Intent(context, MusicPlayerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        });

        holder.musicOptions.setOnClickListener(v->{
            SongBottomSheet bottomSheetDialogFragment = new SongBottomSheet(song.getId(),position);
            bottomSheetDialogFragment.show(fragmentManager, bottomSheetDialogFragment.getTag());
        });
    }


    @Override
    public int getItemCount() {
        return songsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView musicName;
        ImageView musicOptions;

        RelativeLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            musicName = itemView.findViewById(R.id.music_item_name);
            musicOptions = itemView.findViewById(R.id.music_item_options);
            item = (RelativeLayout) itemView;
        }
    }
}
