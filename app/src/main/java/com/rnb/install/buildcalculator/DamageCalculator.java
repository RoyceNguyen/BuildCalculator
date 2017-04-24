package com.rnb.install.buildcalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.id.list;
import static com.rnb.install.buildcalculator.R.id.buildslist;
import static com.rnb.install.buildcalculator.R.id.calculateButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DamageCalculator.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DamageCalculator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DamageCalculator extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView damage;
    private TextView atkSpeed;
    private TextView crit;
    private TextView critDamage;
    private TextView health;
    private TextView armor;
    private TextView magicRes;
    Spinner build;

    private OnFragmentInteractionListener mListener;

    public DamageCalculator() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DamageCalculator.
     */
    // TODO: Rename and change types and number of parameters
    public static DamageCalculator newInstance(String param1, String param2) {
        DamageCalculator fragment = new DamageCalculator();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_damage_calculator, container, false);

        //Creating textviews for all the stats
        damage = (TextView) view.findViewById(R.id.dmgGain);
        atkSpeed = (TextView) view.findViewById(R.id.aspeedGain);
        crit = (TextView) view.findViewById(R.id.critGain);
        critDamage = (TextView) view.findViewById(R.id.critDmgGain);
        health = (TextView) view.findViewById(R.id.healthGain);
        armor = (TextView) view.findViewById(R.id.armorGain);
        magicRes = (TextView) view.findViewById(R.id.magResGain);
        build = (Spinner) view.findViewById(R.id.buildSpinner);
        //initializing Database
        DatabaseHandler db = new DatabaseHandler(getContext());
        //Use ArrayList on Item to get all the weapons and gears separately to populate Spinners
        final ArrayList<Build> buildList = db.getAllBuilds();
        ArrayList<String> buildNames = new ArrayList<String>();
        for (int i = 0; i < buildList.size(); i++) {
            buildNames.add(buildList.get(i).getName());
        }
        //Close database
        db.closeDB();
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, buildNames);
        build.setAdapter(adapter);

        Button calculate = (Button) view.findViewById(R.id.calculateButton);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHandler db = new DatabaseHandler(getContext());
                build.getSelectedItemId();

                //weapon values
                Item wep = db.getWeapon(build.getSelectedItemPosition());
                //gear values
                Item gear = db.getGear(build.getSelectedItemPosition());

                damage.setText("+ " + wep.getAttackDamage());
                atkSpeed.setText("+ "  + wep.getAttackSpeed());
                crit.setText("+ " + wep.getCrit());
                critDamage.setText("+ " + wep.getCritDamage());
                health.setText("+ " + gear.getHealth());
                armor.setText("+ " + gear.getArmor());
                magicRes.setText("+ " + gear.getMagicResist());
                db.closeDB();
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
