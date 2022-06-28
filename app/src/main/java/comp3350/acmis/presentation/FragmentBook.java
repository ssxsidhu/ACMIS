package comp3350.acmis.presentation;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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

    private Location selectedDeparture,selectedDestination;
    private DepartureFragment departureFragment;
    private DestinationFragment destinationFragment;
    private DateFragment dateFragment;
    private FragmentManager fragmentManager;
    private ImageView arrow ;


    public FragmentBook() {
        // Required empty public constructor
    }

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
        departureFragment = new DepartureFragment();
        destinationFragment = new DestinationFragment();
        dateFragment = new DateFragment();
        fragmentManager = getParentFragmentManager();

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
        Bundle args = new Bundle();
        args.putSerializable("locationList",locationList);

        ExtendedFloatingActionButton chooseDeparture = view.findViewById(R.id.choose_departure);
        ExtendedFloatingActionButton chooseDestination = view.findViewById(R.id.choose_destination);

        chooseDeparture.shrink();
        chooseDestination.shrink();

        View.OnClickListener chooseDepartureListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedDestination == null) {
                    chooseDestination.setEnabled(false);
                    chooseDestination.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                }
//                chooseDeparture.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#005AC1")));
                chooseDeparture.extend();
                args.putSerializable("selectedDestination",selectedDestination);
                departureFragment.setArguments(args);
                callFragment(departureFragment);

            }
        };

        chooseDeparture.setOnClickListener(chooseDepartureListener);


        View.OnClickListener chooseDestinationListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chooseDestination.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#005AC1")));
                chooseDestination.extend();
                args.putSerializable("selectedDeparture",selectedDeparture);
                destinationFragment.setArguments(args);
                callFragment(destinationFragment);
            }
        };
        chooseDestination.setOnClickListener(chooseDestinationListener);


        TextView setAirportDeparture = view.findViewById(R.id.airport_departure);
        TextView setCityDeparture = view.findViewById(R.id.city_departure);
        TextView setAirportDestination = view.findViewById(R.id.airport_destination);
        TextView setCityDestination = view.findViewById(R.id.city_destination);
        LinearLayout linearLayoutDeparture = view.findViewById(R.id.layout_departure);
        LinearLayout linearLayoutDestination = view.findViewById(R.id.layout_destination);
        arrow =view.findViewById(R.id.arrow_image);

        linearLayoutDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(linearLayoutDeparture,linearLayoutDestination,chooseDeparture,chooseDepartureListener,chooseDestination);
            }
        });

        linearLayoutDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(linearLayoutDestination,linearLayoutDeparture,chooseDestination,chooseDestinationListener,chooseDeparture);
            }
        });

        fragmentManager.setFragmentResultListener("selectedDeparture", getViewLifecycleOwner(), (requestKey, result) -> {
            selectedDeparture = (Location) result.getSerializable(requestKey);
            setAirportDeparture.setText(selectedDeparture.getAirport());
            setCityDeparture.setText(String.format("%s, %s", selectedDeparture.getCity(), selectedDeparture.getCountry()));
            chooseDeparture.setVisibility(View.INVISIBLE);
            linearLayoutDeparture.setVisibility(View.VISIBLE);
            arrow.setVisibility(View.VISIBLE);
            chooseDestination.setEnabled(true);
            if(selectedDeparture !=null && selectedDestination!=null){
                view.setClickable(false);
                callFragment(dateFragment);
            }
            else{
                chooseDestinationListener.onClick(view);
            }
        });

        fragmentManager.setFragmentResultListener("selectedDestination", getViewLifecycleOwner(), (requestKey, result) -> {
            selectedDestination = (Location) result.getSerializable(requestKey);
            setAirportDestination.setText(selectedDestination.getAirport());
            setCityDestination.setText(String.format("%s, %s", selectedDestination.getCity(), selectedDestination.getCountry()));
            chooseDestination.setVisibility(View.INVISIBLE);
            linearLayoutDestination.setVisibility(View.VISIBLE);
            arrow.setVisibility(View.VISIBLE);
            if(selectedDeparture !=null && selectedDestination!=null){;
                callFragment(dateFragment);
            }
        });


    }

    private void callFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_book, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void replace(LinearLayout linearLayout1,LinearLayout linearLayout2, Button floatingActionButton1,View.OnClickListener clickListener,Button floatingActionButton2){
        arrow.setVisibility(View.INVISIBLE);
        linearLayout1.setVisibility(View.INVISIBLE);
        floatingActionButton1.setVisibility(View.VISIBLE);
        clickListener.onClick(getView());
        if(selectedDeparture!=null && selectedDestination!=null){
            floatingActionButton2.setVisibility(View.INVISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            arrow.setVisibility(View.VISIBLE);
        }

    }



//        ArrayAdapter<Location> adapter1 = new ArrayAdapter<>(getActivity(), R.layout.drop_down_menu_item, R.id.menu_text_view, locationList);
//        ArrayAdapter<Location> adapter2 = new ArrayAdapter<>(getActivity(), R.layout.drop_down_menu_item, R.id.menu_text_view, locationList);
//
//        //for dropDown menus
//        AutoCompleteTextView ddDeparture = (AutoCompleteTextView) view.findViewById(R.id.auto_departure);
//        ddDeparture.setThreshold(1);
//        ddDeparture.setAdapter(adapter1);
//        //departure menu
//        TextInputLayout textInputLayout_departure = view.findViewById(R.id.menu_departure);
//        ((AutoCompleteTextView) Objects.requireNonNull(textInputLayout_departure.getEditText())).setOnItemClickListener((adapterView, view1, position, id) -> {
//            if(selectedDestination!=null) {
//                adapter2.add(selectedDestination);
//                tempDes=selectedDestination;
//                selectedDestination=null;
//            }
//            selectedDeparture = adapter1.getItem(position);
//            tempDep=selectedDeparture;
//            adapter1.remove(selectedDeparture);
//            adapter2.remove(selectedDeparture);
//        });
//
//
//        //destination menu
//        AutoCompleteTextView ddDestination = (AutoCompleteTextView) view.findViewById(R.id.auto_destination);
//        ddDestination.setThreshold(1);
//        ddDestination.setAdapter(adapter2);
//
//
//        TextInputLayout textInputLayout_destination = view.findViewById(R.id.menu_destination);
//        ((AutoCompleteTextView) Objects.requireNonNull(textInputLayout_destination.getEditText())).setOnItemClickListener((adapterView, view12, position, id) -> {
//            selectedDestination=adapter2.getItem(position);
//            tempDes=selectedDestination;
//            adapter1.remove(selectedDestination);
//            adapter2.remove(selectedDestination);
//            if(selectedDeparture!=null) {
//                tempDep=selectedDeparture;
//                adapter1.add(selectedDeparture);
//                selectedDeparture=null;
//            }
//
//        });
//
//        search(view);
//
//    }
//
//    //search button implementation
//    public void search(View rootView){
//        Button search = rootView.findViewById(R.id.search_button);
//        search.setOnClickListener(view -> {
//            if(tempDes!=null && tempDep!=null) {
//                sendData();
//            }
//            else{
//                if(tempDep == null){
//                    Messages.snackBar(view,"Please select departure");
//                }
//                else {
//                    Messages.snackBar(view,"Please select destination");
//                }
//            }
//        });


//    private void sendData(){
//        Intent i = new Intent(getActivity().getBaseContext(),SearchResults.class);
//        i.putExtra("selectedDeparture", tempDep);
//        i.putExtra("selectedDestination",tempDes);
//        getActivity().startActivity(i);
//
//    }


}