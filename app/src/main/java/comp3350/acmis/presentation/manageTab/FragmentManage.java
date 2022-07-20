package comp3350.acmis.presentation.manageTab;

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
        return inflater.inflate(R.layout.fragment_manage, container, false);
    }

    //it shows the upcoming flights in the manage tab
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        myBookingList = new ArrayList<>();
        accessBookings = new AccessBookings("braico");
        accessBookings.getMyBookings(myBookingList);

//        User defaultUser = new User("John","Braico", User.Gender.MALE,"braico","somePassword","jbraico@cs.umanitoba.ca","2041234567");
//        Location vancouver = new Location("Vancouver", ZoneId.of("America/Vancouver"), "Canada","YVR");
//        Location calgary = new Location("Calgary", ZoneId.of("America/Edmonton"), "Canada","YYC");
//        Flight vanToCal = new Flight(vancouver,calgary, ZonedDateTime.of(2022,7,29,20,50,0,0,vancouver.getZoneName()), 200, 1.4, 220);
//        Flight calToVan = new Flight(calgary,vancouver, ZonedDateTime.of(2022,10,2,2,0,0,0,calgary.getZoneName()), 200, 1.4, 220);
//
//        Route r1 = new Route(vanToCal);
//        Route r2 = new Route(calToVan);
//
//        myBookingList.add(new Booking(defaultUser,r1,r2,2));

        CardsAdapter adapter = new CardsAdapter(requireActivity().getBaseContext(), myBookingList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView cards = view.findViewById(R.id.list_items_manage_tab);
        cards.setLayoutManager(linearLayoutManager);
        cards.setAdapter(adapter);
    }
}