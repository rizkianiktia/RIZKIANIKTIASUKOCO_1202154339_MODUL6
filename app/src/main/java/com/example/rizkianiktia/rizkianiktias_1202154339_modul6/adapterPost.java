package com.example.rizkianiktia.rizkianiktias_1202154339_modul6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class adapterPost extends  RecyclerView.Adapter<adapterPost.PostViewHolder>{
    private List<DBPost> list;
    private Context con;

    //Constructor dari adapter
    public adapterPost(List<DBPost> list, Context con) {
        this.list = list;
        this.con = con;
    }

    //Kembali ViewHolder untuk Adapter
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(con).inflate(R.layout.rec_feed, parent, false));
    }

    //Mengikat nilai dari list dengan view
    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        DBPost current = list.get(position);
        String [] user = current.user.split("@");
        holder.user.setText(user[0]);
        holder.user.setTag(current.getKey());
        holder.title.setText(current.getTitle());
        holder.caption.setText(current.getCaption());
        holder.caption.setTag(current.getImage());
        Glide.with(con).load(current.getImage()).placeholder(R.drawable.add_picture).override(450, 450).into(holder.image);
    }

    //Mendapatkan jumlah item pada recyclerview
    @Override
    public int getItemCount() {
        return list.size();
    }

    //Subclass sebagai viewholder
    class PostViewHolder extends RecyclerView.ViewHolder{
        ImageView image; TextView user, title, caption;
        public PostViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.postgambar);
            user = itemView.findViewById(R.id.postpengupload);
            title = itemView.findViewById(R.id.postjudul);
            caption = itemView.findViewById(R.id.postdeskripsi);

            //Ketika item RecyclerView diklik
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent pindah = new Intent(con, post.class);
                    pindah.putExtra("user", user.getText().toString());
                    pindah.putExtra("key", user.getTag().toString());
                    pindah.putExtra("title", title.getText().toString());
                    pindah.putExtra("caption", caption.getText().toString());
                    pindah.putExtra("image", caption.getTag().toString());
                    con.startActivity(pindah);
                }
            });
        }
    }
}
