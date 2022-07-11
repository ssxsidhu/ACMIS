package comp3350.acmis.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import comp3350.acmis.objects.Route;

public class OrderSummary extends AppCompatActivity {

    private ArrayList<Route> orderRoute = new ArrayList<>();;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        setAppBarLayout();
        orderRoute.add((Route) getIntent().getSerializableExtra("selectedDepartRoute"));
        Route returnRoute = (Route) getIntent().getSerializableExtra("selectedReturnRoute");
        if(returnRoute!=null)
            orderRoute.add(returnRoute);

        AccessRouteFlights journeyDetails = new AccessRouteFlights(orderRoute.get(0)) ;

        TextView routeTitle = findViewById(R.id.search_results_title);
        TextView routeLocationTitle  = findViewById(R.id.search_results_location_title);

        //set textview showing search results to invisible
        findViewById(R.id.search_results_title).setVisibility(View.INVISIBLE);

        routeLocationTitle.setText(String.format(Locale.CANADA,"From %s", journeyDetails.getConnectSource().getCity()));

        journeyDetails.setConnectFlightPos(journeyDetails.getNumStops());
        String tripText;
        if(orderRoute.size() == 1)
            tripText = "One-way trip to ";
        else
            tripText = "Round-trip to ";

        routeTitle.setText(String.format(Locale.CANADA,"%s%s",tripText,journeyDetails.getConnectDestination().getCity()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        final RecyclerView recyclerView = this.findViewById(R.id.list_search_results);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(new SearchResultsCardsAdapter(getBaseContext(), orderRoute, new SearchResultsCardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Route item) {
                Intent i = new Intent(getBaseContext(), RouteDetails.class);
                i.putExtra("selectedRoute", item);
                i.putExtra("continueButtonVisibility",false);
                startActivity(i);
            }
        }));

        Button bookButton =  findViewById(R.id.book_button);
        bookButton.setVisibility(View.VISIBLE);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void setAppBarLayout(){
        MaterialToolbar materialToolbar = findViewById(R.id.search_results_top_app_bar);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
