package com.example.hero.achievement;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hero.achievement.model.DatabaseModel1;

import java.util.LinkedList;
import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.MyViewHolder> {

    List<DatabaseModel1> subjectList= new LinkedList<>(); //vase delete kardan rahat tare

    NamesAdapter(List<DatabaseModel1> subjects ){
        subjectList=subjects;
    }

    /*
        inja behesh migim ke az kodoom layout bayad estefade konim
    */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.names_list_item,viewGroup,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.textView.setText(subjectList.get(position).getSubjectName() + position);


    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

          public MyViewHolder(@NonNull View itemView) {
              super(itemView);
              textView=itemView.findViewById(R.id.txtName);

              textView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      // rooye har dars click kard chi beshe?

                  }
              });
          }
      }
}
