package comp3350.acmis.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

    private Location selectedDeparture,selectedDestination;
    private DepartureFragment departureFragment;
    private DestinationFragment destinationFragment;
    FragmentManager fragmentManager;


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

        FragmentManager fragmentManager = getParentFragmentManager();


        FloatingActionButton chooseDeparture = view.findViewById(R.id.choose_departure);
        FloatingActionButton chooseDestination = view.findViewById(R.id.choose_destination);

        chooseDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedDestination == null)
                    chooseDestination.setEnabled(false);
                chooseDeparture.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#005AC1")));
                chooseDeparture.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                args.putSerializable("selectedDestination",selectedDestination);
                departureFragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_book, departureFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        chooseDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDestination.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#005AC1")));
                chooseDestination.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                args.putSerializable("selectedDeparture",selectedDeparture);
                destinationFragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_book, destinationFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        TextView setAirportDeparture = view.findViewById(R.id.airport_departure);
        TextView setCityDeparture = view.findViewById(R.id.city_departure);
        TextView setAirportDestination = view.findViewById(R.id.airport_destination);
        TextView setCityDestination = view.findViewById(R.id.city_destination);

        LinearLayout linearLayoutDeparture = view.findViewById(R.id.layout_departure);
        LinearLayout linearLayoutDestination = view.findViewById(R.id.layout_destination);

//        setAirportDeparture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setAirportDeparture.setVisibility(View.INVISIBLE);
//                setCityDeparture.setVisibility(View.INVISIBLE);
//                chooseDeparture.show();
//                chooseDeparture.performClick();
//
//            }
//        });

        linearLayoutDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(linearLayoutDeparture,linearLayoutDestination,chooseDeparture,chooseDestination);
            }
        });

        linearLayoutDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(linearLayoutDestination,linearLayoutDeparture,chooseDestination,chooseDeparture);
            }
        });


//        setAirportDestination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setAirportDestination.setVisibility(View.INVISIBLE);
//                setCityDestination.setVisibility(View.INVISIBLE);
//                chooseDestination.show();
//                chooseDestination.performClick();
//            }
//        });

        fragmentManager.setFragmentResultListener("selectedDeparture", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                selectedDeparture = (Location) result.getSerializable(requestKey);
                setAirportDeparture.setText(selectedDeparture.getAirport());
                setCityDeparture.setText(String.format("%s,%s", selectedDeparture.getCity(), selectedDeparture.getCountry()));
                chooseDeparture.hide();
                linearLayoutDeparture.setVisibility(View.VISIBLE);
                chooseDestination.setEnabled(true);
            }
        });

        fragmentManager.setFragmentResultListener("selectedDestination", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                selectedDestination = (Location) result.getSerializable(requestKey);
                setAirportDestination.setText(selectedDestination.getAirport());
                setCityDestination.setText(String.format("%s,%s", selectedDestination.getCity(), selectedDestination.getCountry()));
                chooseDestination.hide();
                linearLayoutDestination.setVisibility(View.VISIBLE);
            }
        });



    }

    private void replace(LinearLayout linearLayout1,LinearLayout linearLayout2, FloatingActionButton floatingActionButton1,FloatingActionButton floatingActionButton2){
        linearLayout1.setVisibility(View.INVISIBLE);
        floatingActionButton1.show();
        floatingActionButton1.performClick();
        if(selectedDeparture!=null && selectedDestination!=null){
            floatingActionButton2.hide();
            linearLayout2.setVisibility(View.VISIBLE);
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