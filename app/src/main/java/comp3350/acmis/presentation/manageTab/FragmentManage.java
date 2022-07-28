package comp3350.acmis.presentation.manageTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessBookings;
import comp3350.acmis.objects.Booking;


public class FragmentManage extends Fragment {

    private ArrayList<Booking> myBookingList;
    private AccessBookings accessBookings;

    public FragmentManage() {
        // Required empty public constructor
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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        myBookingList = new ArrayList<>();
        accessBookings = new AccessBookings("braico");
        accessBookings.getMyBookings(myBookingList);
        if (!myBookingList.isEmpty()) {
            view.findViewById(R.id.no_bookings_found).setVisibility(View.GONE);
            displayBookingList(view);
        } else {
            view.findViewById(R.id.no_bookings_found).setVisibility(View.VISIBLE);
            view.findViewById(R.id.list_items_manage_tab).setVisibility(View.GONE);
        }

    }

    private void displayBookingList(View view) {
        ManageCardsAdapter adapter = new ManageCardsAdapter(requireActivity().getBaseContext(), myBookingList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView cards = view.findViewById(R.id.list_items_manage_tab);
        cards.setLayoutManager(linearLayoutManager);
        cards.setAdapter(adapter);
    }
}