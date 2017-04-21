package com.rnb.install.buildcalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.rnb.install.buildcalculator.MainActivity.fab;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BuildFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BuildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Fragment created by Blaze
     */
    ListView list;
    TextView build;


    private OnFragmentInteractionListener mListener;

    public BuildFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildFragment newInstance(String param1, String param2) {
        BuildFragment fragment = new BuildFragment();
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

    //Create fragment manager
    FragmentManager fm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_build, container, false);
        fm = getActivity().getSupportFragmentManager();
        //Had to add this fab.show in order to create a build
        fab.show();
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.content_main, new CreateBuildFragment());
                ft.commit();
            }
        });

        list = (ListView) view.findViewById(R.id.buildslist);
        DatabaseHandler db = new DatabaseHandler(getContext());
        final ArrayList<Build> buildslist = db.getAllBuilds();
        db.closeDB();

        //create custom adapter
        final CustomAdapter adapter = new CustomAdapter(getContext(), buildslist);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView build = (TextView) view.findViewById(R.id.build);
                TextView details = (TextView) view.findViewById(R.id.details);
                ImageView chevron = (ImageView) view.findViewById(R.id.chevron);
                //open a database connection here
                DatabaseHandler db = new DatabaseHandler(getContext());
                Log.d("WEPCONTENTS", "" + buildslist.size()+"");
                Item wep = db.getWeapon(buildslist.get(position).getWeapon());
                db.closeDB();

                if(build.getText() != (buildslist.get(position)).getName()){
                    //update the text of build
                    build.setText(((Build) list.getItemAtPosition(position)).getName());
                    if (wep != null) {
                        build.setText(wep.getName());
                    }
                    //update the text of the show more
                    details.setText("Click to show less");
                    //update the chevron image
                    chevron.setImageResource(R.drawable.ic_expand_less_black_24dp);

                }
                else{
                    build.setText("");
                    details.setText("Click to show more");
                    //update the chevron image
                    chevron.setImageResource(R.drawable.ic_expand_more_black_24dp);
                }
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseHandler db = new DatabaseHandler(getContext());
                Build build = buildslist.get(position);
                db.deleteBuild(build.getId());
                db.closeDB();
                buildslist.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return view;
    }

    public class CustomAdapter extends ArrayAdapter<Build> {

        public CustomAdapter(Context context, ArrayList<Build> items) {
            super(context, 0, items);
        }

        /**
         * getView is used to take every item in a list
         * and assign a view to it.
         * With this specific adapter we specified item_view as the view
         * we want every item in a list to look like.
         * After that item has item_view attached to it
         * we populate the item_view's name TextView
         */
        public View getView(int position, View convertView, ViewGroup parent){
            final Build item = getItem(position);

           // DatabaseHandler db = new DatabaseHandler(getContext());
            //ArrayList<Item> weaponList = db.getAllWeapons();

           // ArrayList<String> weaponNames = new ArrayList<String>();
            //for (int i = 0; i < weaponList.size(); i++) {
             //   weaponNames.add(weaponList.get(i).getName());
           // }
           // ArrayList<Item> weaponList = db.getAllWeapons();
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
            }

            TextView name = (TextView) convertView.findViewById(R.id.name);
            name.setText(item.getName());
            //TextView weaponName = (TextView) convertView.findViewById(R.id.weaponSpinner);
            //weaponName.setText(weaponName.getText());

            return convertView;
        }
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
