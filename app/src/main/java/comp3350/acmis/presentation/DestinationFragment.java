package comp3350.acmis.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

import comp3350.acmis.R;
import comp3350.acmis.objects.Location;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DestinationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DestinationFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Location> locationList;
    private Location selectedDeparture;

    public DestinationFragment() {
        // Required empty public constructor
    }

    public static DestinationFragment newInstance(String param1, String param2) {
        DestinationFragment fragment = new DestinationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_destination, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        if (getArguments() != null) {
            locationList = (ArrayList<Location>) getArguments().getSerializable("locationList");
            selectedDeparture = (Location) getArguments().getSerializable("selectedDeparture");
        }

        ArrayList<Location> duplicateList = new ArrayList<>(locationList);
        duplicateList.remove(selectedDeparture);


        ArrayAdapter<Location> adapter = new ArrayAdapter<>(getActivity(), R.layout.drop_down_menu_item, R.id.menu_text_view, duplicateList);
        AutoCompleteTextView ddDestination = (AutoCompleteTextView) view.findViewById(R.id.auto_destination);
        ddDestination.setThreshold(1);
        ddDestination.setAdapter(adapter);

        TextInputLayout textInputLayout_destination = view.findViewById(R.id.menu_destination);
        ((AutoCompleteTextView) Objects.requireNonNull(textInputLayout_destination.getEditText())).setOnItemClickListener((adapterView, view12, position, id) -> {
            Bundle result = new Bundle();
            result.putSerializable("selectedDestination",adapter.getItem(position));
            getParentFragmentManager().setFragmentResult("selectedDestination",result);
        });
    }
}