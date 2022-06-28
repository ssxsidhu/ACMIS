package comp3350.acmis.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import comp3350.acmis.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DateFragment() {
        // Required empty public constructor
    }

    public static DateFragment newInstance(String param1, String param2) {
        DateFragment fragment = new DateFragment();
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
        return inflater.inflate(R.layout.fragment_date, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        pickDate(view);

    }

    private void pickDate(View view){
        MaterialDatePicker.Builder<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Select Departure Date");

        CalendarConstraints.Builder constraints = new CalendarConstraints.Builder();
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        constraints.setStart(today);
        calendar.set(2022,12,Calendar.DATE);
        constraints.setEnd(calendar.getTimeInMillis());
        constraints.setValidator(DateValidatorPointForward.now());

        materialDateBuilder.setCalendarConstraints(constraints.build());

        final MaterialDatePicker<Long> materialDatePicker = materialDateBuilder.build();
        Button pickDepart = view.findViewById(R.id.pick_depart_date);

        View.OnClickListener pickDepartListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");
                ;
            }
        };
        pickDepart.setOnClickListener(pickDepartListener);
        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            pickDepart.setVisibility(View.VISIBLE);
            pickDepart.setText(String.format(Locale.CANADA,"Depart %s", materialDatePicker.getHeaderText()));
            Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            utc.setTimeInMillis(selection);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.CANADA);
            String formatted = format.format(utc.getTime());
        });
    }
}