package com.rnb.install.buildcalculator;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateBuildFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateBuildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateBuildFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    /**
     * Fragment created by Blaze.
     */
    //Add EditText name, for user to enter in their own build name
    EditText name;
    //Add Spinner for both weapon and gear for user to choose from list
    Spinner classChoice;
    Spinner weapon;
    Spinner gear;

    public CreateBuildFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateBuildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateBuildFragment newInstance(String param1, String param2) {
        CreateBuildFragment fragment = new CreateBuildFragment();
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
    }

    FragmentManager fm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_create_build, container, false);
        //Creating the EditText and Spinner for name, weapon and gear
        name = (EditText) view.findViewById(R.id.buildName);
        weapon = (Spinner) view.findViewById(R.id.weaponSpinner);
        gear = (Spinner) view.findViewById(R.id.gearSpinner);
        classChoice = (Spinner) view.findViewById(R.id.classSpinner);

        //initializing Database
        DatabaseHandler db = new DatabaseHandler(getContext());
        //Use ArrayList on Item to get all the weapons and gears separately to populate Spinners
        ArrayList<Item> weaponList = db.getAllWeapons();
        ArrayList<String> weaponNames = new ArrayList<String>();
        for (int i = 0; i < weaponList.size(); i++) {
            weaponNames.add(weaponList.get(i).getName());
        }

        ArrayList<Item> gearList = db.getAllGears();
        ArrayList<String> gearNames = new ArrayList<String>();
        for (int j = 0; j < gearList.size(); j++) {
            gearNames.add(gearList.get(j).getName());
        }

        //Close database
        db.closeDB();

        //Create an ArrayAdapter to grab context and use weaponList and gearList
        ArrayAdapter adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, weaponNames);
        ArrayAdapter adapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, gearNames);
        //ArrayAdapter adapter3 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, classChoice);
        //Apply the adapter to the spinner
        weapon.setAdapter(adapter1);
        gear.setAdapter(adapter2);

        //Log.d(TAG, "onCreateView: ");

        //Creating button OnClickListener to submit your build
        Button submit = (Button) view.findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Build build = new Build(name.getText().toString(), (int) weapon.getSelectedItemId(), (int)gear.getSelectedItemId());
                DatabaseHandler db = new DatabaseHandler(getContext());
                db.addBuild(build);
                db.close();
                fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }

        });

        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
