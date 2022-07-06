package comp3350.acmis.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

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

    private Location selectedDeparture, selectedDestination;
    private DepartureFragment departureFragment;
    private DestinationFragment destinationFragment;
    private bookDetailsFragment bookDetailsFragment;
    private FragmentManager fragmentManager;
    private ImageView arrow;


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
        bookDetailsFragment = new bookDetailsFragment();
        fragmentManager = getParentFragmentManager();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        AccessLocations accessLocations = new AccessLocations();
        ArrayList<Location> locationList = new ArrayList<>();

        accessLocations.getLocations(locationList);
        Bundle args = new Bundle();
        args.putSerializable("locationList", locationList);

        ExtendedFloatingActionButton chooseDeparture = view.findViewById(R.id.choose_departure);
        ExtendedFloatingActionButton chooseDestination = view.findViewById(R.id.choose_destination);
        chooseDeparture.shrink();
        chooseDestination.shrink();

        View.OnClickListener chooseDepartureListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedDestination == null) {
                    chooseDestination.setEnabled(false);
                    chooseDestination.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                }
                chooseDeparture.extend();
                departureFragment.setArguments(args);
                callFragment(departureFragment);
            }
        };

        chooseDepartureListener.onClick(view);
        chooseDeparture.setOnClickListener(chooseDepartureListener);


        View.OnClickListener chooseDestinationListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDestination.extend();
                destinationFragment.setArguments(args);
                callFragment(destinationFragment);
            }
        };
        chooseDestination.setOnClickListener(chooseDestinationListener);



        LinearLayout linearLayoutDeparture = view.findViewById(R.id.layout_departure);
        LinearLayout linearLayoutDestination = view.findViewById(R.id.layout_destination);
        arrow = view.findViewById(R.id.arrow_image);

        linearLayoutDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(linearLayoutDeparture, linearLayoutDestination, chooseDeparture, chooseDepartureListener, chooseDestination);
            }
        });

        linearLayoutDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(linearLayoutDestination, linearLayoutDeparture, chooseDestination, chooseDestinationListener, chooseDeparture);
            }
        });

        fragmentManager.setFragmentResultListener("selectedDeparture", getViewLifecycleOwner(), (requestKey, result) -> {
            selectedDeparture = (Location) result.getSerializable(requestKey);
//            setAirportDeparture.setText(selectedDeparture.getAirport());
//            setCityDeparture.setText(String.format("%s, %s", selectedDeparture.getCity(), selectedDeparture.getCountry()));
//            chooseDeparture.setVisibility(View.INVISIBLE);
//            linearLayoutDeparture.setVisibility(View.VISIBLE);
//            arrow.setVisibility(View.VISIBLE);
            chooseDestination.setEnabled(true);
            if(!useFragmentResults(view,selectedDeparture,chooseDeparture,linearLayoutDeparture,args))
                chooseDestinationListener.onClick(view);

        });

        fragmentManager.setFragmentResultListener("selectedDestination", getViewLifecycleOwner(), (requestKey, result) -> {
            selectedDestination = (Location) result.getSerializable(requestKey);
//            setAirportDestination.setText(selectedDestination.getAirport());
//            setCityDestination.setText(String.format("%s, %s", selectedDestination.getCity(), selectedDestination.getCountry()));
//            chooseDestination.setVisibility(View.INVISIBLE);
//            linearLayoutDestination.setVisibility(View.VISIBLE);
//            arrow.setVisibility(View.VISIBLE);
//            if (selectedDeparture != null && selectedDestination != null) {
//                bookDetailsFragment.setArguments(args);
//                callFragment(bookDetailsFragment);
//            }
            useFragmentResults(view,selectedDestination,chooseDestination,linearLayoutDestination,args);
        });

    }

    private Boolean useFragmentResults(View view,Location location,ExtendedFloatingActionButton button,LinearLayout linearLayout,Bundle args){
        TextView setAirportDeparture = view.findViewById(R.id.airport_departure);
        TextView setCityDeparture = view.findViewById(R.id.city_departure);
        TextView setAirportDestination = view.findViewById(R.id.airport_destination);
        TextView setCityDestination = view.findViewById(R.id.city_destination);

        if(location == selectedDeparture){
            setAirportDeparture.setText(location.getAirport());
            setCityDeparture.setText(String.format("%s, %s", location.getCity(), location.getCountry()));
        }
        else{
            setAirportDestination.setText(location.getAirport());
            setCityDestination.setText(String.format("%s, %s", location.getCity(), location.getCountry()));
        }

        args.putSerializable("selectedDestination", selectedDestination);
        args.putSerializable("selectedDeparture", selectedDeparture);
        button.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        arrow.setVisibility(View.VISIBLE);
        if (selectedDeparture != null && selectedDestination != null) {
            bookDetailsFragment.setArguments(args);
            callFragment(bookDetailsFragment);
            return true;
        }
        else
            return false;
    }

    private void callFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_book, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void replace(LinearLayout linearLayout1, LinearLayout linearLayout2, Button floatingActionButton1, View.OnClickListener clickListener, Button floatingActionButton2) {
        arrow.setVisibility(View.INVISIBLE);
        linearLayout1.setVisibility(View.INVISIBLE);
        floatingActionButton1.setVisibility(View.VISIBLE);
        clickListener.onClick(getView());
        if (selectedDeparture != null && selectedDestination != null) {
            floatingActionButton2.setVisibility(View.INVISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            arrow.setVisibility(View.VISIBLE);
        }

    }

}