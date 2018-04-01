package com.example.rizkianiktia.rizkianiktias_1202154339_modul6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adapterCommand extends  RecyclerView.Adapter<adapterCommand.CommentHolder>{
    Context con;
    List<DBcommand> list;
    //Constructor untuk adapter
    public adapterCommand(Context con, List<DBcommand> list) {
        this.con = con;
        this.list = list;
    }

    //Return ViewHolder dari Recyclerview
    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutInflater.from(con).inflate(R.layout.rec_comment, parent, false));
    }

    //Mengikat nilai dari list dengan view
    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        DBcommand cur = list.get(position);
        holder.yangkomen.setText(cur.getYangkomen());
        holder.komennya.setText(cur.getKomennya());
    }

    //Mendapatkan jumlah item pada recyclerview
    @Override
    public int getItemCount() {
        return list.size();
    }

    //Subclass sebagai viewholder
    class CommentHolder extends RecyclerView.ViewHolder{
        TextView yangkomen, komennya;
        public CommentHolder(View itemView) {
            super(itemView);
            yangkomen = itemView.findViewById(R.id.yangkomen);
            komennya = itemView.findViewById(R.id.komennya);
        }
    }
}
