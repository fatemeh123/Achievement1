package com.example.hero.achievement;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstclickAddFragment extends Fragment {


    public FirstclickAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //code hamoono inja neminevisim
        View v = inflater.inflate(R.layout.fragment_firstclick_add, container, false);
        EditText subjectName=v.findViewById(R.id.edt_firstClickFragment_subjName);
        EditText subjectPriority=v.findViewById(R.id.edt_firstClickFragment_subjPriority);
        EditText perWeek=v.findViewById(R.id.edt_firstClickFragment_dayPerWeek);
        EditText perDay=v.findViewById(R.id.edt_firstClickFragment_hourPerDay);

        return v;
    }
/*
code hamoono inja minevisim
 */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
