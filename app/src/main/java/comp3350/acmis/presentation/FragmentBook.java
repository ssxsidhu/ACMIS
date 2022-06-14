package comp3350.acmis.presentation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessLocations;
import comp3350.acmis.objects.Location;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBook#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBook extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AccessLocations accessLocations;
    private ArrayList<Location> locationList;
//    private ArrayAdapter<Location> locationArrayAdapter;

    public FragmentBook() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBook newInstance(String param1, String param2) {
        FragmentBook fragment = new FragmentBook();
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

        View rootView = inflater.inflate(R.layout.fragment_book, container, false);


        accessLocations = new AccessLocations();
        locationList = new ArrayList<Location>();

        String result = accessLocations.getLocations(locationList);

        //since we are using a Object arrayList
        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(getActivity(),R.layout.menu_item,R.id.menu_text_view, locationList);

        //for dropDown menus
        AutoCompleteTextView ddDeparture = (AutoCompleteTextView) rootView.findViewById(R.id.auto_departure);
        ddDeparture.setThreshold(1);
        ddDeparture.setAdapter(adapter);

        //to check which location user has selected
        Location[] temp_departure = new Location[1];

        TextInputLayout textInputLayout_departure = rootView.findViewById(R.id.menu_departure);
        ((AutoCompleteTextView) Objects.requireNonNull(textInputLayout_departure.getEditText())).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(temp_departure[0]!=null) {
                    adapter.add(temp_departure[0]);
                }
                temp_departure[0] = adapter.getItem(position);
                adapter.remove(adapter.getItem(position));
            }
        });


        AutoCompleteTextView ddDestination = (AutoCompleteTextView) rootView.findViewById(R.id.auto_destination);
        ddDestination.setThreshold(1);
        ddDestination.setAdapter(adapter);

        Location[] temp_destination = new Location[1];

        TextInputLayout textInputLayout_destination = rootView.findViewById(R.id.menu_destination);
        ((AutoCompleteTextView) Objects.requireNonNull(textInputLayout_destination.getEditText())).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(temp_destination[0]!=null) {
                    adapter.add(temp_destination[0]);
                }
                temp_destination[0]=adapter.getItem(position);
                adapter.remove(adapter.getItem(position));

            }
        });




        return rootView;
    }
}