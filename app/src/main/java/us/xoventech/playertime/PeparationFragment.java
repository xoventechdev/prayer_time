package us.xoventech.playertime;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PeparationFragment extends Fragment {

    public static String WACT_NAME = "";
    public static String WACT_PREPARATION_DETAILS = "";
    public static String WACT_PREPARATION_FULL_DETAILS = "";
    public static TextView wact_name;
    public static TextView wact_preparation_details;
    public static TextView wact_preparation_full_details;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate =  inflater.inflate(R.layout.fragment_peparation, container, false);

        wact_name = (TextView) inflate.findViewById(R.id.wact_name);
        wact_preparation_details = (TextView) inflate.findViewById(R.id.wact_preparation_details);
        wact_preparation_full_details = (TextView) inflate.findViewById(R.id.wact_preparation_full_details);
        wact_name.setText(WACT_NAME + " এর নিয়ম/নিয়ত");
        wact_preparation_details.setText(WACT_PREPARATION_DETAILS);
        wact_preparation_full_details.setText(WACT_PREPARATION_FULL_DETAILS);
        return inflate;
    }
}