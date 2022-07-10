package comp3350.acmis.presentation;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Locale;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessRouteFlights;
import comp3350.acmis.objects.Route;

public class RouteDetails extends AppCompatActivity {
    private Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);
        route =(Route) getIntent().getSerializableExtra("selectedRoute");

        AccessRouteFlights routeDetails = new AccessRouteFlights(route);

        TextView totalDuration = findViewById(R.id.total_duration);
        totalDuration.setText(routeDetails.getRouteTotalDuration());


        setStatusBarColor();
        MaterialToolbar materialToolbar = findViewById(R.id.details_top_app_bar);
        materialToolbar.setSubtitle("Departing "+routeDetails.getConnectFormattedDepartureDate());

        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView totalCost = findViewById(R.id.details_total_cost);
        totalCost.setText(String.format(Locale.CANADA,"CA $%d", routeDetails.getRouteTotalCost()));

        Button continueButton = findViewById(R.id.details_continue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        RouteDetailsAdapter adapter = new RouteDetailsAdapter(getBaseContext(),routeDetails);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        final RecyclerView recyclerView = this.findViewById(R.id.list_route_details);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setStatusBarColor(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(getBaseContext(),R.color.md_theme_dark_shadow));
    }
}
