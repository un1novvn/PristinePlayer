package cn.org.unk.musicapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.org.unk.musicapp.R;
import cn.org.unk.musicapp.activity.ListActivity;
import cn.org.unk.musicapp.bottomSheet.SongListBottomSheet;
import cn.org.unk.musicapp.db.entity.SongList;

import java.util.ArrayList;

public class SongListItemAdapter extends RecyclerView.Adapter<SongListItemAdapter.ViewHolder>{
    ArrayList<SongList> songLists;
    Context context;
    FragmentManager fragmentManager;
    public SongListItemAdapter(ArrayList<SongList> songsList, Context context, FragmentManager fragmentManager) {
        this.songLists = songsList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item_songlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongList songList = songLists.get(position);

        holder.titleTextView.setText(songList.getSongListName());
        holder.item.setOnClickListener(v->{
            new Thread(()->{
                Intent intent = new Intent(context, ListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("listId",songList.getId());
                context.startActivity(intent);
            }).start();
        });

//        给右边3个点的选项绑监听
        holder.musicOptions.setOnClickListener(view -> {

            SongListBottomSheet bottomSheetDialogFragment = new SongListBottomSheet(String.valueOf(position));
            bottomSheetDialogFragment.show(fragmentManager, bottomSheetDialogFragment.getTag());
        });
    }

    @Override
    public int getItemCount() {
        return songLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;
        RelativeLayout item;
        ImageView musicOptions;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.music_list_name);
            item = (RelativeLayout) itemView;
            musicOptions = itemView.findViewById(R.id.music_options);
        }
    }
}
