package comp3350.acmis.presentation.searchroutes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Locale;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessRouteFlights;
import comp3350.acmis.business.BookingManager;
import comp3350.acmis.objects.Route;
import comp3350.acmis.presentation.MainActivity;
import comp3350.acmis.presentation.Messages;
import comp3350.acmis.presentation.Utils;

public class OrderSummary extends AppCompatActivity {

    private final ArrayList<Route> orderRoute = new ArrayList<>();
    private final BookingManager bookingManager = new BookingManager();
    private AccessRouteFlights journeyDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Utils.setStatusBarColor(getWindow(), getBaseContext());
        orderRoute.add((Route) getIntent().getSerializableExtra("selectedDepartRoute"));
        Route returnRoute = (Route) getIntent().getSerializableExtra("selectedReturnRoute");
        if (returnRoute != null)
            orderRoute.add(returnRoute);

        int numPassengers = getIntent().getIntExtra("numPassengers", 1);

        journeyDetails = new AccessRouteFlights(orderRoute.get(0));

        setAppBarLayout();
        TextView routeTitle = findViewById(R.id.search_results_title);
        TextView routeLocationTitle = findViewById(R.id.search_results_location_title);

        //set textview showing search results to invisible
        findViewById(R.id.search_results_text).setVisibility(View.INVISIBLE);

        routeLocationTitle.setText(String.format(Locale.CANADA, "From %s", journeyDetails.getConnectSource().getCity()));

        journeyDetails.setConnectFlightPos(journeyDetails.getNumStops());
        String tripText;
        if (orderRoute.size() == 1)
            tripText = "One-way trip to ";
        else
            tripText = "Round-trip to ";

        routeTitle.setText(String.format(Locale.CANADA, "%s%s", tripText, journeyDetails.getConnectDestination().getCity()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        final RecyclerView recyclerView = this.findViewById(R.id.list_search_results);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new SearchResultsCardsAdapter(orderRoute, true, new SearchResultsCardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Route item) {
                Intent i = new Intent(getBaseContext(), RouteDetails.class);
                i.putExtra("selectedRoute", item);
                i.putExtra("continueButtonVisibility", false);
                startActivity(i);
            }
        }));

        Button bookButton = findViewById(R.id.book_button);
        bookButton.setVisibility(View.VISIBLE);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result;
                if (orderRoute.size() == 1)
                    result = bookingManager.createBooking("braico", orderRoute.get(0), null, numPassengers);
                else
                    result = bookingManager.createBooking("braico", orderRoute.get(0), orderRoute.get(1), numPassengers);
                if (result != null) {
                    Messages.snackBar(view, result);
                } else {
                    Intent i1 = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i1);
                }
            }
        });

    }

    private void setAppBarLayout() {
        MaterialToolbar materialToolbar = findViewById(R.id.search_results_top_app_bar);

        TextView searchDepartAirport = findViewById(R.id.search_depart_airport);
        TextView searchDestAirport = findViewById(R.id.search_arrive_airport);
        ImageView searchDirection = findViewById(R.id.search_direction_image);

        searchDepartAirport.setText(journeyDetails.getConnectSource().getAirport());
        journeyDetails.setConnectFlightPos(journeyDetails.getNumStops());
        searchDestAirport.setText(journeyDetails.getConnectDestination().getAirport());

        if (orderRoute.size() > 1)
            searchDirection.setImageResource(R.drawable.ic_round_u_turn_right_24);
        journeyDetails.setConnectFlightPos(0);

        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
