package comp3350.acmis.presentation;

import android.content.Intent;
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

import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import comp3350.acmis.R;
import comp3350.acmis.objects.Location;

public class bookDetailsFragment extends Fragment {


    private int selectedNumPassengers = 1;
    private LocalDate departDate, returnDate;
    private Button pickReturn, pickDepart, searchFlightsButton;

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
        pickReturn = view.findViewById(R.id.pick_return_date);
        pickDepart = view.findViewById(R.id.pick_depart_date);
        searchFlightsButton = view.findViewById(R.id.search_flights_button);
        pickDepartDate(view);
        pickReturnDate(view);
        pickNumPassengers(view);
        searchFlights();
    }

    private MaterialDatePicker<Long> setCalender(String titleText, long startDate) {

        //date picker builder
        MaterialDatePicker.Builder<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText(titleText);

        //calender constraints setting
        CalendarConstraints.Builder constraints = new CalendarConstraints.Builder();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        constraints.setStart(startDate);
        calendar.set(2022, 12, Calendar.DATE);
        constraints.setEnd(calendar.getTimeInMillis());
        constraints.setValidator(DateValidatorPointForward.from(startDate));
        materialDateBuilder.setCalendarConstraints(constraints.build());
        return materialDateBuilder.build();

    }

    private void pickDepartDate(View view) {
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        MaterialDatePicker<Long> materialDatePicker = setCalender("Select Departure Date", today);
        pickDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER_DEPART");

            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            pickDepart.setText(String.format(Locale.CANADA, "Depart %s", materialDatePicker.getHeaderText()));
            Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            utc.setTimeInMillis(selection);
            departDate = LocalDate.parse(calendarToDate(utc, "yyyy-MM-dd"));
            pickReturn.setEnabled(true);
            if (returnDate != null && returnDate.isBefore(departDate)) {
                returnDate = null;
                searchFlightsButton.setEnabled(false);
                pickReturn.setText(requireContext().getString(R.string.return_flight));
            }
            if (!checkRoundTrip(view))
                searchFlightsButton.setEnabled(true);
        });

    }

    private void pickReturnDate(View view) {
        checkRoundTrip(view);
        pickReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDatePicker<Long> materialDatePicker = setCalender("Select Return Date", departDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
                materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER_RETURN");
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    pickReturn.setText(String.format(Locale.CANADA, "Return %s", materialDatePicker.getHeaderText()));
                    Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                    utc.setTimeInMillis(selection);
                    returnDate = LocalDate.parse(calendarToDate(utc, "yyyy-MM-dd"));
                    ;
                    searchFlightsButton.setEnabled(true);
                });
            }
        });

    }


    public String calendarToDate(Calendar calendar, String dateFormat) {
        if (calendar == null) {
            return null;
        }
        Locale locale = requireContext().getResources().getConfiguration().locale;
        DateFormat df = new SimpleDateFormat(dateFormat, locale);
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        df.setTimeZone(timeZone);

        Date d = calendar.getTime();
        return df.format(d);
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
                if (selectedNumPassengers < 25) {
                    selectedNumPassengers++;
                    setNumPassengers(passengerButton);
                } else
                    Messages.snackBar(view, "Maximum Number of passengers reached for this account");
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

    private Boolean checkRoundTrip(View view) {

        final SwitchMaterial roundTripSwitch = view.findViewById(R.id.round_trip_switch);
        roundTripSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    pickReturn.setVisibility(View.VISIBLE);
                    searchFlightsButton.setEnabled(false);
                } else {
                    returnDate = null;
                    pickReturn.setVisibility(View.GONE);
                    searchFlightsButton.setEnabled(true);
                }
            }
        });
        return roundTripSwitch.isChecked();
    }

    private void searchFlights() {
        searchFlightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }

    private void sendData() {
        Location selectedDestination = null, selectedDeparture = null;
        if (getArguments() != null) {
            selectedDeparture = (Location) getArguments().get("selectedDeparture");
            selectedDestination = (Location) getArguments().get("selectedDestination");
        }
        Intent i = new Intent(requireActivity().getBaseContext(), SearchResults.class);
        i.putExtra("selectedDeparture", selectedDeparture);
        i.putExtra("selectedDestination", selectedDestination);
        i.putExtra("departDate", departDate);
        i.putExtra("returnDate", returnDate);
        i.putExtra("numPassengers", selectedNumPassengers);
        requireActivity().startActivity(i);
    }
}