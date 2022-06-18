package comp3350.acmis.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import comp3350.acmis.R;
import comp3350.acmis.business.BookingManager;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

public class SearchResults extends AppCompatActivity {

    private Location selectedDeparture, selectedDestination;
    BookingManager bookingManager = new BookingManager();
    ArrayList<Route> flightsAvailable = new ArrayList<>();

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
            CustomAdapter customAdapter = new CustomAdapter(this, flightsAvailable);
            final ListView listView = (ListView) this.findViewById(R.id.list_items_book_tab);
            final Button book = this.findViewById(R.id.book_button);
            Activity thisActivity = this;
            listView.setAdapter(customAdapter);
            //if the user clicks on a list item
            listView.setOnItemClickListener((adapterView, view, i, l) -> {
                book.setEnabled(true);
                book.setOnClickListener(view1 -> {
                    //if the user clicks on the book button
                    String result = bookingManager.createBooking("default", customAdapter.getItem(i));
                    if(result!=null){
                        Messages.snackBar(view1,result);
                    }
                    else {
                        Intent i1 = new Intent(thisActivity.getBaseContext(), MainActivity.class);
                        thisActivity.startActivity(i1);
                    }
                });
            });

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