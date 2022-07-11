package comp3350.acmis.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Locale;

import comp3350.acmis.R;
import comp3350.acmis.business.BookingManager;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

public class SearchResults extends AppCompatActivity {

    private Location selectedDeparture, selectedDestination;
    private LocalDate departDate, returnDate;
    private Route selectedDepartRoute;
    private Boolean showReturnFlightRslts;
    private BookingManager bookingManager = new BookingManager();
    private ArrayList<Route> flightsAvailable = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        receiveData();
        setAppBarLayout();
        Utils.setStatusBarColor(getWindow(), getBaseContext());

        //checks if there are flights available
        String checkFlights = bookingManager.searchRoute(selectedDeparture, selectedDestination, flightsAvailable);
        if (checkFlights != null) {
            Messages.noFlightsMessage(this);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
            final RecyclerView recyclerView = this.findViewById(R.id.list_search_results);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new SearchResultsCardsAdapter(getBaseContext(), flightsAvailable, new SearchResultsCardsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Route item) {
                    Intent i = new Intent(getBaseContext(), RouteDetails.class);
                    i.putExtra("selectedRoute", item);
                    i.putExtra("returnDate",returnDate);
                    i.putExtra("selectedDepartRoute",selectedDepartRoute);
                    startActivity(i);
                }
            }));
        }

        TextView searchTitle = findViewById(R.id.search_results_title);
        TextView searchLocationTitle = findViewById(R.id.search_results_location_title);

        if (!showReturnFlightRslts) {
            searchTitle.setText(String.format(Locale.CANADA, "Departing %s", Utils.getFormattedDate(departDate)));
            searchLocationTitle.setText(String.format(Locale.CANADA,"From %s", selectedDeparture.getCity()));
        }
        else {
            searchTitle.setText(String.format(Locale.CANADA, "Returning %s", Utils.getFormattedDate(returnDate)));
            searchLocationTitle.setText(String.format(Locale.CANADA,"From %s", selectedDestination.getCity()));
        }

    }

    private void setAppBarLayout() {
        MaterialToolbar materialToolbar = findViewById(R.id.search_results_top_app_bar);
        NestedScrollView nestedScrollView = findViewById(R.id.search_results_scroll);
        materialToolbar.setSubtitle(selectedDeparture.getAirport().toUpperCase(Locale.CANADA) + " ->- " + selectedDestination.getAirport().toUpperCase(Locale.CANADA));
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 150)
                    materialToolbar.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.md_theme_dark_shadow));
                else
                    materialToolbar.setBackgroundColor(ContextCompat.getColor(getBaseContext(), android.R.color.transparent));
            }
        });
    }


//    private void changeColor(int colorFrom, int colorTo){
//        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
//        colorAnimation.setDuration(250); // milliseconds
//        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animator) {
//            }
//
//        });
//        colorAnimation.start();
//    }



    // receive data from previous activity
    private void receiveData() {
        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        selectedDeparture = (Location) i.getSerializableExtra("selectedDeparture");
        selectedDestination = (Location) i.getSerializableExtra("selectedDestination");
        departDate = (LocalDate) i.getSerializableExtra("departDate");
        returnDate = (LocalDate) i.getSerializableExtra("returnDate");
        selectedDepartRoute = (Route) i.getSerializableExtra("selectedDepartRoute");
        showReturnFlightRslts = i.getBooleanExtra("showReturnView", false);
    }


}