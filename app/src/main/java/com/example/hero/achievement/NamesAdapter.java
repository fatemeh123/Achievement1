package com.example.hero.achievement;



import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NamesAdapter extends RecyclerView.Adapter <NamesAdapter.MyViewHolder>{



    /*
    inja behesh migim ke az kodoom layout bayad estefade konim
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //begim layoutet names_list_item has
        View v =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.names_list_item,viewGroup,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    /*
    mese adamas ke listo gharare poresh kone
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.textView.setText("fatemeh abdi");

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    /*
    har chizi ke too listemoon darimo inja tarif mikonim textView o ImageView ...
    hameye view haye itmemeoon o tarif mikonim
     */
    class MyViewHolder extends RecyclerView.ViewHolder{


        TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            textView=itemView.findViewById(R.id.txtName);

        }
    }
}

