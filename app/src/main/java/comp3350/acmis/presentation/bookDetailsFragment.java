package comp3350.acmis.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import comp3350.acmis.R;

public class bookDetailsFragment extends Fragment {


    private int selectedNumPassengers = 1;

    public bookDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_details, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        pickDepartDate(view);
        pickArrivalDate(view);
        pickNumPassengers(view);
    }

    private void pickDepartDate(View view) {

        //date picker builder
        MaterialDatePicker.Builder<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Select Departure Date");

        //calender constraints setting
        CalendarConstraints.Builder constraints = new CalendarConstraints.Builder();
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        constraints.setStart(today);
        calendar.set(2022, 12, Calendar.DATE);
        constraints.setEnd(calendar.getTimeInMillis());
        constraints.setValidator(DateValidatorPointForward.now());

        materialDateBuilder.setCalendarConstraints(constraints.build());

        //make a date picker instance
        final MaterialDatePicker<Long> materialDatePicker = materialDateBuilder.build();
        Button pickDepart = view.findViewById(R.id.pick_depart_date);

        pickDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");
                ;
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            pickDepart.setVisibility(View.VISIBLE);
            pickDepart.setText(String.format(Locale.CANADA, "Depart %s", materialDatePicker.getHeaderText()));
            Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            utc.setTimeInMillis(selection);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
            String formatted = format.format(utc.getTime());
        });
    }

    private void pickNumPassengers(View view) {

        final Button passengerButton = view.findViewById(R.id.pick_num_passengers);
        final Button subtractButton = view.findViewById(R.id.subtract_button);
        final Button addButton = view.findViewById(R.id.add_button);
        setNumPassengers(passengerButton);
        subtractButton.setEnabled(selectedNumPassengers != 1);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedNumPassengers++;
                setNumPassengers(passengerButton);
                subtractButton.setEnabled(selectedNumPassengers != 1);
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedNumPassengers > 1)
                    selectedNumPassengers--;
                setNumPassengers(passengerButton);
                subtractButton.setEnabled(selectedNumPassengers != 1);
            }
        });

    }

    private void setNumPassengers(Button passengerButton) {
        if (selectedNumPassengers == 1)
            passengerButton.setText(String.format(Locale.CANADA, "%d %s", selectedNumPassengers, requireContext().getString(R.string.passenger)));
        else
            passengerButton.setText(String.format(Locale.CANADA, "%d %s", selectedNumPassengers, requireContext().getString(R.string.passengers)));

    }

    private void checkRoundTrip(View view, Button pickArrival) {

        final SwitchMaterial roundTripSwitch = view.findViewById(R.id.round_trip_switch);
        roundTripSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (pickArrival.getVisibility() == View.GONE)
                    pickArrival.setVisibility(View.VISIBLE);
                else
                    pickArrival.setVisibility(View.GONE);
            }
        });
    }

    private void pickArrivalDate(View view) {
        Button pickArrival = view.findViewById(R.id.pick_arrival_date);
        checkRoundTrip(view, pickArrival);
    }
}