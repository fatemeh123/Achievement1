package com.example.hero.achievement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hero.achievement.core.Core;
import com.example.hero.achievement.model.DatabaseModel1;
import com.example.hero.achievement.modeltwo.DatabaseModelTwo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.MyViewHolder> {


    List<DatabaseModel1> subjectList = new LinkedList<>();              //vase delete kardan rahat tare
    private SQLiteDBHelper sqLiteDBHelper;                              //mikhaym list haye zakhire shodaro beigirm
    private final Context context;
    private MaterialDialog.Builder sessionDialogBuilder;
    private MaterialDialog addingSessionDialog;

    NamesAdapter(Context context1, List<DatabaseModel1> subjects) {

        context = context1;
        subjectList = subjects;

        this.sqLiteDBHelper = new SQLiteDBHelper(context1);
    }

    /*
        inja behesh migim ke az kodoom layout bayad estefade konim
    */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.names_list_item, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(v);


        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        myViewHolder.textView.setText(subjectList.get(position).getSubjectName() );


        myViewHolder.addSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.initDialogAddSession(subjectList.get(position).getSubjectName());
            }
        });


    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView addSession;
        ImageView seeProgress;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtName);
            addSession = itemView.findViewById(R.id.addSession_ImageView);
            seeProgress = itemView.findViewById(R.id.seeProgress_ImageView);


            addSession.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("star","onClick ");

                    addingSessionDialog.show();
                }
            });




            seeProgress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("star","onClick ");
                    Intent intent = new Intent (v.getContext(), ChartDialogActivity.class);

                 // itemView.getContext().startActivity(new Intent(v.getContext(), ChartDialogActivity.class));  // bejaye Intent intent=new Intent()
                    context.startActivity(intent);
                }
            });

        }

/*
zakhire sazie etellate  DialogAddSession too data base
 */
        public void initDialogAddSession(final String name) {

            sessionDialogBuilder = new MaterialDialog.Builder(context);
            sessionDialogBuilder.customView(R.layout.addig_session_dialog_layout, false)
                    .autoDismiss(false)
                    .cancelable(false);
            addingSessionDialog = sessionDialogBuilder.build();

            Button  btnBackAddSessionDialog = (Button) addingSessionDialog.findViewById(R.id.btn_Back_AddSession_Dialog);
            Button btnSaveBackAddSessionDialog = (Button) addingSessionDialog.findViewById(R.id.btn_Save_AddSession_Dialog);
            final TextView enjoytxt = (TextView) addingSessionDialog.findViewById(R.id.edt_AddSession_Dialog_enjoy);
            final EditText hourEdt = (EditText) addingSessionDialog.findViewById(R.id.edt_AddSession_Dialog_hour);
            final EditText qualityEdt = (EditText) addingSessionDialog.findViewById(R.id.edt_AddSession_Dialog_quality);
            final int[] satisfaction = new int[1];


            btnBackAddSessionDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addingSessionDialog.dismiss(); //behtare ke aval soal beporse ke yeho etellat pak nashe // va mitoni az handler mesle code exit stefadeh koni
                }
            });


            //baraye har emogy yek adad dar nazar gereftam ke nemoodare stisfactiono badan neshoon bedam
            ImageView veryBadImV        =  (ImageView) addingSessionDialog.findViewById(R.id.veryBad_session_Emogy);
            ImageView badImV            = (ImageView) addingSessionDialog.findViewById(R.id.bad_session_Emogy);
            ImageView sosSoImV          = (ImageView) addingSessionDialog.findViewById(R.id.soSo_session_Emogy);
            ImageView goodImV           = (ImageView) addingSessionDialog.findViewById(R.id.good_session_Emogy);
            ImageView veryGoodImV       = (ImageView) addingSessionDialog.findViewById(R.id.veryGood_session_Emogy);


            veryBadImV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    satisfaction[0] = 10;
                }
            });
            badImV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    satisfaction[0] = 35;
                }
            });
            sosSoImV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    satisfaction[0] = 60;
                }
            });
            goodImV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    satisfaction[0] = 80;
                }
            });
            veryGoodImV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    satisfaction[0] = 100;
                }
            });

            final int satis = satisfaction[0];

            final List<DatabaseModelTwo> list = new ArrayList<>();

            btnSaveBackAddSessionDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String d =Core.getTime();
                    if (enjoytxt.getText().toString().equals("")) {
                        enjoytxt.setError("is Empty...");
                    } else if (hourEdt.getText().toString().equals("")) {
                        hourEdt.setError("is Empty...");
                    }
                    if (qualityEdt.getText().toString().equals("")) {
                        qualityEdt.setError("is Empty...");
                    } else {


                        list.add(new DatabaseModelTwo(
                                name ,
                                satis,
                                Integer.valueOf(hourEdt.getText().toString()),
                                Integer.valueOf(qualityEdt.getText().toString()),
                                d
                        ));

                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                sqLiteDBHelper.insertAddSEssion(list);
                            }
                        }).start();

                        addingSessionDialog.dismiss();
                    }

                }
            });

            addingSessionDialog.show();

        }


    }


}
