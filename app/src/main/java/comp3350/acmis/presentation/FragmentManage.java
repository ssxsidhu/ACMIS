package comp3350.acmis.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessBookings;
import comp3350.acmis.objects.Booking;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentManage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentManage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //future use
    private String mParam1;
    private String mParam2;
    private ArrayList<Booking> myBookingList;
    private AccessBookings accessBookings;

    public FragmentManage() {
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
    public static FragmentManage newInstance(String param1, String param2) {
        FragmentManage fragment = new FragmentManage();
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
        return inflater.inflate(R.layout.fragment_manage, container, false);
    }

    //it shows the upcoming flights in the manage tab
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        myBookingList = new ArrayList<>();
        accessBookings = new AccessBookings("default");
        accessBookings.getMyBookings(myBookingList);
        CardsAdapter adapter = new CardsAdapter(this, myBookingList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);

//        final ListView listView = (ListView) view.findViewById(R.id.list_items_manage_tab);
        RecyclerView cards = view.findViewById(R.id.list_items_manage_tab);
//        listView.setAdapter(adapter);
        cards.setLayoutManager(linearLayoutManager);
        cards.setAdapter(adapter);
    }
}