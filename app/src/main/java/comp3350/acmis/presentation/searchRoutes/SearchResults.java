package comp3350.acmis.presentation.searchRoutes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Locale;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessRoutes;
import comp3350.acmis.business.SortRoutes;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.presentation.Messages;
import comp3350.acmis.presentation.Utils;

public class SearchResults extends AppCompatActivity {

    private Location selectedDeparture, selectedDestination;
    private LocalDate departDate, returnDate;
    private Route selectedDepartRoute;
    private Boolean showReturnFlightRslts;
    private SearchResultsCardsAdapter searchResultsCardsAdapter;
    private ArrayList<Route> flightsAvailable = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.searchTransition);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        findViewById(R.id.window_image).startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_out));

        receiveData();
        Utils.setStatusBarColor(getWindow(), getBaseContext());

        setSortView();

        new loadFights().execute();

        setAppBarLayout();

        TextView searchTitle = findViewById(R.id.search_results_title);
        TextView searchLocationTitle = findViewById(R.id.search_results_location_title);

        if (!showReturnFlightRslts) {
            searchTitle.setText(String.format(Locale.CANADA, "Departing %s", Utils.getFormattedDate(departDate)));
        } else {
            searchTitle.setText(String.format(Locale.CANADA, "Returning %s", Utils.getFormattedDate(departDate)));
        }
        searchLocationTitle.setText(String.format(Locale.CANADA, "From %s %s", selectedDeparture.getCity(), selectedDeparture.getAirport()));
    }

    private void setAppBarLayout() {
        MaterialToolbar materialToolbar = findViewById(R.id.search_results_top_app_bar);
        NestedScrollView nestedScrollView = findViewById(R.id.search_results_scroll);
        nestedScrollView.getParent().requestChildFocus(nestedScrollView, nestedScrollView);
        TextView searchDepartAirport = findViewById(R.id.search_depart_airport);
        TextView searchDestAirport = findViewById(R.id.search_arrive_airport);
        ImageView searchDirection = findViewById(R.id.search_direction_image);

        searchDepartAirport.setText(selectedDeparture.getAirport());
        searchDestAirport.setText(selectedDestination.getAirport());

        if (returnDate != null)
            searchDirection.setImageResource(R.drawable.ic_round_u_turn_right_24);

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

    private class loadFights extends AsyncTask<Void, Void, Void> {
        private CircularProgressIndicator progressIndicator = findViewById(R.id.progress_indicator);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressIndicator.setVisibility(View.VISIBLE);
            flightsAvailable = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AccessRoutes accessRoutes = new AccessRoutes(selectedDeparture, selectedDestination);

            synchronized (this) {
                while (accessRoutes.getAvailableRoutes(flightsAvailable, departDate) != null) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressIndicator.setVisibility(View.GONE);
            if (flightsAvailable.isEmpty()) {
                Messages.noFlightsMessage(SearchResults.this);
            } else {
                new SortRoutes().leastStops(flightsAvailable);
                displaySearchResults();
            }
        }
    }

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
        flightsAvailable = (ArrayList<Route>) i.getSerializableExtra("flights");
    }


    private void setSortView() {
        ExtendedFloatingActionButton sortButton = findViewById(R.id.sort_button);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SortFragment sortFragment = new SortFragment();
                Bundle args = new Bundle();
                args.putSerializable("availableRouteList", flightsAvailable);
                sortFragment.setArguments(args);
                sortFragment.show(getSupportFragmentManager(), "SortBottomSheet");
            }
        });
    }

    private void displaySearchResults() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        final RecyclerView recyclerView = this.findViewById(R.id.list_search_results);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.animate();
        recyclerView.setLayoutManager(linearLayoutManager);
        searchResultsCardsAdapter = new SearchResultsCardsAdapter(flightsAvailable, false, item -> {
            Intent i = new Intent(getBaseContext(), RouteDetails.class);
            i.putExtra("selectedRoute", item);
            i.putExtra("returnDate", returnDate);
            i.putExtra("selectedDepartRoute", selectedDepartRoute);
            i.putExtra("numPassengers", getIntent().getIntExtra("numPassengers", 1));
            startActivity(i);
        });
        recyclerView.setAdapter(searchResultsCardsAdapter);
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        if (searchResultsCardsAdapter != null)
            searchResultsCardsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}