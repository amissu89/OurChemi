package com.example.ourchemi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ourchemi.models.Person;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowResultFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ShowResultFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Result.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowResultFragment newInstance(String param1, String param2) {
        ShowResultFragment fragment = new ShowResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mParam1 ="show result";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    public String getParam()
    {
        return mParam1;
    }

    // TODO: 2022-03-14
    public void setPerson(Person p1, Person p2)
    {
        String str = "p1 : " + p1.toString();
        str += " p2 : " + p2.toString();
        System.out.println(str);
        TextView tv = (TextView)getView().findViewById(R.id.myinfo);
        tv.setText(str);
    }
}