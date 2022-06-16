package comp3350.acmis.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        flightsAvailable = bookingManager.searchRoute(selectedDeparture,selectedDestination);
        if (flightsAvailable.size() > 0) {
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
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    book.setEnabled(true);
                    book.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                bookingManager.createBooking("default",customAdapter.getItem(i));
                                Intent i = new Intent(thisActivity.getBaseContext(), BottomTabActivity.class);
                                thisActivity.startActivity(i);
                        }
                    });
                }
            });

        }
        else {
            this.findViewById(R.id.no_results_found).setVisibility(View.VISIBLE);
            this.findViewById(R.id.header_title_book_tab).setVisibility(View.INVISIBLE);
        }
    }


    private String bookRoutes(int position) {
        Button book = this.findViewById(R.id.book_button);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < selectedRoutes.size(); i++) {
                    bookingManager.createBooking("default",selectedRoutes.get(i));
                            String result = bookingManager.createBooking("default", customAdapter.getItem(i));
                            if(result!=null){
                                Messages.snackBar(view,result);
                            }
                            else {
                                Intent i = new Intent(thisActivity.getBaseContext(), MainActivity.class);
                                thisActivity.startActivity(i);
                            }
                        }
                    });
                }
            });

        }

    }

    private void receiveData() {
        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        selectedDeparture =(Location) i.getSerializableExtra("selectedDeparture");
        selectedDestination =(Location) i.getSerializableExtra("selectedDestination");
        //SET DATA TO TEXTVIEWS
    }
}