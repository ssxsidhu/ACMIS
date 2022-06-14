package comp3350.acmis.presentation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessUserFlights;
import comp3350.acmis.objects.Flight;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Flight> userFlightList;
    private AccessUserFlights accessUserFlights;

    public ManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageFragment newInstance(String param1, String param2) {
        ManageFragment fragment = new ManageFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_manage, container, false);
        userFlightList = new ArrayList<Flight>();
        accessUserFlights = new AccessUserFlights("default");

        accessUserFlights.getUserFlights(userFlightList);

//        ArrayAdapter<Flight> ArrayAdapter = new ArrayAdapter<Flight>(this.getActivity(), android.R.layout.simple_list_item_activated_2, android.R.id.text1, userFlightList) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//
//                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
//
//                text1.setText(String.format("%s    ---->    %s", userFlightList.get(position).getDepart().getCity(), userFlightList.get(position).getArrive().getCity()));
////                text2.setText(userFlightList.get(position).getFlightID());
//
//                return view;
//            }
//        };
        String[] textString = {"Item1","Item2"};
        int[] drawableIds = {R.drawable.airplane_symbol,R.drawable.airplane_symbol};

        CustomAdapter adapter = new CustomAdapter(this, textString , drawableIds);


        final ListView listView = (ListView) rootView.findViewById(R.id.menuList);
        listView.setAdapter(adapter);
        return rootView;
    }
}