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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hero.achievement.core.Core;
import com.example.hero.achievement.model.DatabaseAddProject;
import com.example.hero.achievement.modeltwo.DatabaseAddSession;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.MyViewHolder> {


    List<DatabaseAddProject> subjectList = new LinkedList<>();              //vase delete kardan rahat tare
    private SQLiteDBHelper sqLiteDBHelper;                              //mikhaym list haye zakhire shodaro beigirm
    private final Context context;
    private MaterialDialog.Builder sessionDialogBuilder;
    private MaterialDialog addingSessionDialog;

    private String calender;
    private String day;
    private String month0;
    private String year0;
    CalendarView calendarView;
    private boolean stateDate = false;

    //for AddSessionDialog
    ImageView veryBadImV   ;
    ImageView badImV       ;
    ImageView sosSoImV     ;
    ImageView goodImV      ;
    ImageView veryGoodImV  ;
    NamesAdapter(Context context1, List<DatabaseAddProject> subjects) {

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

        myViewHolder.seeProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChartDialogActivity.class);
                intent.putExtra("subject" , subjectList.get(position).getSubjectName());
                context.startActivity(intent);
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

            init();

            Button  btnBackAddSessionDialog   = (Button) addingSessionDialog.findViewById(R.id.btn_Back_AddSession_Dialog);
            Button btnSaveBackAddSessionDialog= (Button) addingSessionDialog.findViewById(R.id.btn_Save_AddSession_Dialog);
            final TextView enjoytxt           = (TextView) addingSessionDialog.findViewById(R.id.edt_AddSession_Dialog_enjoy);
            final EditText hourEdt            = (EditText) addingSessionDialog.findViewById(R.id.edt_AddSession_Dialog_hour);
            final EditText qualityEdt         = (EditText) addingSessionDialog.findViewById(R.id.edt_AddSession_Dialog_quality);
            final int[] satisfaction          = new int[1];


            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                    int d = dayOfMonth;
                    day =String.valueOf(d);
                    int m= month;
                    month0 = String.valueOf(month);
                    int y = year;
                    year0 = String.valueOf(year);

                    stateDate = true;

                }
            });
            calender = (day+'/'+month0+'/'+year0);





            btnBackAddSessionDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addingSessionDialog.dismiss(); //behtare ke aval soal beporse ke yeho etellat pak nashe // va mitoni az handler mesle code exit stefadeh koni
                }
            });

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

            final int satisfactionLevel = satisfaction[0];

            final List<DatabaseAddSession> list = new ArrayList<>();

            btnSaveBackAddSessionDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (!stateDate) {      //if no date selected , save today
                        calender = Core.getTime();
                    }




                    if (enjoytxt.getText().toString().equals("")) {
                        enjoytxt.setError("is Empty...");
                    }
                    else if (!hourEdt.getText().toString().matches("(0-24)")) {
                        hourEdt.setError("is Ridiculous...");
                    }

                    else if (hourEdt.getText().toString().equals("")) {
                        hourEdt.setError("is Empty...");
                    }
                    else if (!qualityEdt.getText().toString().matches("(0-10)")) {
                        qualityEdt.setError("is Ridiculous...");
                    }
                    else if (qualityEdt.getText().toString().equals("")) {
                        qualityEdt.setError("is Empty...");
                    } else {

                    Log.d("star","database ok  ");

                        list.add(new DatabaseAddSession(
                                name ,
                                satisfactionLevel,
                                Integer.valueOf(hourEdt.getText().toString()),
                                Integer.valueOf(qualityEdt.getText().toString()),
                                calender
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

    public void init(){
        sessionDialogBuilder = new MaterialDialog.Builder(context);
        sessionDialogBuilder.customView(R.layout.addig_session_dialog_layout, false)
                .autoDismiss(false)
                .cancelable(false);
        addingSessionDialog = sessionDialogBuilder.build();

        calendarView = (CalendarView) addingSessionDialog.findViewById(R.id.my_calender);

         //baraye har emogy yek adad dar nazar gereftam ke nemoodare stisfactiono badan neshoon bedam
         veryBadImV   = (ImageView) addingSessionDialog.findViewById(R.id.veryBad_session_Emogy);
         badImV       = (ImageView) addingSessionDialog.findViewById(R.id.bad_session_Emogy);
         sosSoImV     = (ImageView) addingSessionDialog.findViewById(R.id.soSo_session_Emogy);
         goodImV      = (ImageView) addingSessionDialog.findViewById(R.id.good_session_Emogy);
         veryGoodImV  = (ImageView) addingSessionDialog.findViewById(R.id.veryGood_session_Emogy);

    }

}
