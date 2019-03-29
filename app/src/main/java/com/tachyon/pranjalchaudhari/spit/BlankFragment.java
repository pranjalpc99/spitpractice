package com.tachyon.pranjalchaudhari.spit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;

public class BlankFragment extends Fragment {

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        RecyclerAdapter adapter = new RecyclerAdapter(
                new String[]{"Final Result End Semester",
                        "iB Hubs IoT Bootcamp '18",
                        "Brainhack Computing Hands On Python",
                        "Hands on: Machine Learning using Python",
                        "Cyber Security: Hands on Industrial Training" ,
                        "Provisional Result ODD Semester",
                        "Provisional Result make-up exam"},
                new String[] {"All",
                        "All",
                        "All",
                        "All",
                        "All",
                        "All",
                        "All"},
                new String[] {"Result",
                        "Bootcamp",
                        "IEEE",
                        "AICTE-ISTE",
                        "Workshop",
                        "Re-Exam",
                        "Result"});


        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;

    }

}