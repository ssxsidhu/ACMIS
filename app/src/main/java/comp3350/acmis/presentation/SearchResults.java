package comp3350.acmis.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import comp3350.acmis.R;
import comp3350.acmis.business.BookingManager;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

public class SearchResults extends AppCompatActivity {

    private Location selectedDeparture, selectedDestination;
    private BookingManager bookingManager = new BookingManager();
    private ArrayList<Route> flightsAvailable = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        receiveData();

        //checks if there are flights available
        String checkFlights= bookingManager.searchRoute(selectedDeparture, selectedDestination,flightsAvailable );
        if(checkFlights!=null){
            Messages.noFlightsMessage(this);
        }
        else {
            SearchResultsCardsAdapter adapter = new SearchResultsCardsAdapter(getBaseContext(),flightsAvailable);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
            final RecyclerView recyclerView = this.findViewById(R.id.list_search_results);
            final Button book = this.findViewById(R.id.book_button);
            Activity thisActivity = this;
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
            //if the user clicks on a list item
        }

    }

    // receive data from previous activity
    private void receiveData()
    {
        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        selectedDeparture =(Location) i.getSerializableExtra("selectedDeparture");
        selectedDestination =(Location) i.getSerializableExtra("selectedDestination");
        //SET DATA TO TEXTVIEWS
    }

}