package comp3350.acmis.presentation.searchroutes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import org.threeten.bp.LocalDate;

import java.util.Locale;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessRouteFlights;
import comp3350.acmis.objects.Route;
import comp3350.acmis.presentation.Utils;

public class RouteDetails extends AppCompatActivity {
    private Route route, selectedDepartRoute;
    private LocalDate returnDate;
    private Boolean contButtonVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);

        recieveData();

        AccessRouteFlights routeDetails = new AccessRouteFlights(route);

        TextView totalDuration = findViewById(R.id.total_duration);
        totalDuration.setText(routeDetails.getRouteTotalDuration());

        Utils.setStatusBarColor(getWindow(), getBaseContext());

        Utils.setStatusBarColor(getWindow(), getBaseContext());
        MaterialToolbar materialToolbar = findViewById(R.id.details_top_app_bar);
        materialToolbar.setSubtitle("Departing " + Utils.getFormattedDate(routeDetails.getConnectDepartureDate()));

        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView totalCost = findViewById(R.id.details_total_cost);
        totalCost.setText(String.format(Locale.CANADA, "CA $%d", routeDetails.getRouteTotalCost()));

        if (contButtonVisibility) {
            LinearLayout continueButtonLayout = findViewById(R.id.route_details_continue_layout);
            continueButtonLayout.setVisibility(View.VISIBLE);
            Button continueButton = findViewById(R.id.details_continue);
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (returnDate == null) {
                        Intent i = new Intent(getBaseContext(), OrderSummary.class);
                        i.putExtra("selectedDepartRoute", route);
                        i.putExtra("numPassengers", getIntent().getIntExtra("numPassengers", 1));
                        startActivity(i);
                    } else if (selectedDepartRoute == null) {
                        Intent i = new Intent(getBaseContext(), SearchResults.class);
                        i.putExtra("showReturnView", true);
                        routeDetails.setConnectFlightPos(0);
                        i.putExtra("selectedDestination", routeDetails.getConnectSource());
                        i.putExtra("departDate", returnDate);
                        routeDetails.setConnectFlightPos(routeDetails.getNumStops());
                        i.putExtra("selectedDeparture", routeDetails.getConnectDestination());
                        i.putExtra("returnDate", returnDate);
                        i.putExtra("selectedDepartRoute", route);
                        i.putExtra("numPassengers", getIntent().getIntExtra("numPassengers", 1));
                        startActivity(i);
                    } else {
                        Intent i = new Intent(getBaseContext(), OrderSummary.class);
                        i.putExtra("selectedDepartRoute", selectedDepartRoute);
                        i.putExtra("selectedReturnRoute", route);
                        i.putExtra("numPassengers", getIntent().getIntExtra("numPassengers", 1));
                        startActivity(i);
                    }
                }
            });
        }

        RouteDetailsAdapter adapter = new RouteDetailsAdapter(getBaseContext(), routeDetails);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        final RecyclerView recyclerView = this.findViewById(R.id.list_route_details);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void recieveData() {
        Intent i = getIntent();
        route = (Route) i.getSerializableExtra("selectedRoute");
        contButtonVisibility = i.getBooleanExtra("continueButtonVisibility", true);
        returnDate = (LocalDate) i.getSerializableExtra("returnDate");
        selectedDepartRoute = (Route) i.getSerializableExtra("selectedDepartRoute");
    }

}
