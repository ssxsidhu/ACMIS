package comp3350.acmis.presentation;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Location tempDep,tempDes,selectedDeparture,selectedDestination;

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
    //for future use
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
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        AccessLocations accessLocations = new AccessLocations();
        ArrayList<Location> locationList = new ArrayList<>();

        accessLocations.getLocations(locationList);

        ArrayAdapter<Location> adapter1 = new ArrayAdapter<>(getActivity(), R.layout.drop_down_menu_item, R.id.menu_text_view, locationList);
        ArrayAdapter<Location> adapter2 = new ArrayAdapter<>(getActivity(), R.layout.drop_down_menu_item, R.id.menu_text_view, locationList);

        //for dropDown menus
        AutoCompleteTextView ddDeparture = (AutoCompleteTextView) view.findViewById(R.id.auto_departure);
        ddDeparture.setThreshold(1);
        ddDeparture.setAdapter(adapter1);
        //departure menu
        TextInputLayout textInputLayout_departure = view.findViewById(R.id.menu_departure);
        ((AutoCompleteTextView) Objects.requireNonNull(textInputLayout_departure.getEditText())).setOnItemClickListener((adapterView, view1, position, id) -> {
            if(selectedDestination!=null) {
                adapter2.add(selectedDestination);
                tempDes=selectedDestination;
                selectedDestination=null;
            }
            selectedDeparture = adapter1.getItem(position);
            tempDep=selectedDeparture;
            adapter1.remove(selectedDeparture);
            adapter2.remove(selectedDeparture);
        });


        //destination menu
        AutoCompleteTextView ddDestination = (AutoCompleteTextView) view.findViewById(R.id.auto_destination);
        ddDestination.setThreshold(1);
        ddDestination.setAdapter(adapter2);


        TextInputLayout textInputLayout_destination = view.findViewById(R.id.menu_destination);
        ((AutoCompleteTextView) Objects.requireNonNull(textInputLayout_destination.getEditText())).setOnItemClickListener((adapterView, view12, position, id) -> {
            selectedDestination=adapter2.getItem(position);
            tempDes=selectedDestination;
            adapter1.remove(selectedDestination);
            adapter2.remove(selectedDestination);
            if(selectedDeparture!=null) {
                tempDep=selectedDeparture;
                adapter1.add(selectedDeparture);
                selectedDeparture=null;
            }

        });

        search(view);

    }

    //search button implementation
    public void search(View rootView){
        Button search = rootView.findViewById(R.id.search_button);
        search.setOnClickListener(view -> {
            if(tempDes!=null && tempDep!=null) {
                sendData();
            }
            else{
                if(tempDep == null){
                    Messages.snackBar(view,"Please select departure");
                }
                else {
                    Messages.snackBar(view,"Please select destination");
                }
            }
        });


    }

    private void sendData(){
        Intent i = new Intent(getActivity().getBaseContext(),SearchResults.class);
        i.putExtra("selectedDeparture", tempDep);
        i.putExtra("selectedDestination",tempDes);
        getActivity().startActivity(i);

    }


}