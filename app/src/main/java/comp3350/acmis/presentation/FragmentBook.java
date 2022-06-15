package comp3350.acmis.presentation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessLocations;
import comp3350.acmis.business.BookingManager;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBook#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBook extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private AccessLocations accessLocations;
    private ArrayList<Location> locationList;
    private ArrayList<Route> selectedRoutes;
    private Location selectedDeparture,selectedDestination;
    BookingManager bookingManager = new BookingManager();

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
        selectedRoutes = new ArrayList<>();

        String result = accessLocations.getLocations(locationList);

        //since we are using a Object arrayList
        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(getActivity(),R.layout.menu_item,R.id.menu_text_view, locationList);

        //for dropDown menus
        AutoCompleteTextView ddDeparture = (AutoCompleteTextView) rootView.findViewById(R.id.auto_departure);
        ddDeparture.setThreshold(1);
        ddDeparture.setAdapter(adapter);

        TextInputLayout textInputLayout_departure = rootView.findViewById(R.id.menu_departure);
        ((AutoCompleteTextView) Objects.requireNonNull(textInputLayout_departure.getEditText())).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(selectedDeparture!=null) {
                    adapter.add(selectedDeparture);
                }
                selectedDeparture = adapter.getItem(position);
                adapter.remove(adapter.getItem(position));
            }
        });


        AutoCompleteTextView ddDestination = (AutoCompleteTextView) rootView.findViewById(R.id.auto_destination);
        ddDestination.setThreshold(1);
        ddDestination.setAdapter(adapter);


        TextInputLayout textInputLayout_destination = rootView.findViewById(R.id.menu_destination);
        ((AutoCompleteTextView) Objects.requireNonNull(textInputLayout_destination.getEditText())).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(selectedDestination!=null) {
                    adapter.add(selectedDestination);
                }
                selectedDestination=adapter.getItem(position);
                adapter.remove(adapter.getItem(position));

            }
        });

        search(rootView);
//        System.out.println(selectedRoutes.get(0).getRoute().get(0).getFlightID());
        bookRoutes(rootView);


        return rootView;
    }


    public String bookRoutes(View rootView){
        Button book = rootView.findViewById(R.id.book_button);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<selectedRoutes.size();i++){
                    bookingManager.createBooking("default",selectedRoutes.get(i));
                }
            }
        });
        return null;
    }


    public String search(View rootView){

        FragmentBook context = this;
        Button search = rootView.findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Route> flightsAvailable = new ArrayList<>();

                if(selectedDeparture!=null && selectedDestination!=null) {
                    flightsAvailable.add(bookingManager.searchRoute(selectedDeparture, selectedDestination));
                    CustomAdapter customAdapter = new CustomAdapter(context,flightsAvailable);
                    final ListView listView = (ListView) rootView.findViewById(R.id.list_items_book_tab);
                    listView.setAdapter(customAdapter);
                    listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedRoutes.add(customAdapter.getItem(i));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }
        });
        return null;
    }


}