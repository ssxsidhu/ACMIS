package comp3350.acmis.presentation.searchroutes;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.CircularPropagation;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import comp3350.acmis.business.FilterRoutes;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.presentation.Messages;
import comp3350.acmis.presentation.PropagatingTransition;
import comp3350.acmis.presentation.Utils;

public class SearchResults extends AppCompatActivity {

    private Location selectedDeparture, selectedDestination;
    private LocalDate departDate, returnDate;
    private Route selectedDepartRoute;
    private Boolean showReturnFlightRslts;
    private final ArrayList<Route> flightsAvailable = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.searchTransition);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        findViewById(R.id.window_image).startAnimation(AnimationUtils.loadAnimation(this,R.anim.zoom_out));

        receiveData();
        Utils.setStatusBarColor(getWindow(), getBaseContext());

        FilterRoutes filterRoutes = new FilterRoutes(selectedDeparture, selectedDestination);
        String checkFlights = filterRoutes.getFilteredRoutes(flightsAvailable, departDate);
        if (checkFlights != null) {
            Messages.noFlightsMessage(this);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
            final RecyclerView recyclerView = this.findViewById(R.id.list_search_results);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new SearchResultsCardsAdapter(flightsAvailable, false, item -> {
                Intent i = new Intent(getBaseContext(), RouteDetails.class);
                i.putExtra("selectedRoute", item);
                i.putExtra("returnDate", returnDate);
                i.putExtra("selectedDepartRoute", selectedDepartRoute);
                i.putExtra("numPassengers", getIntent().getIntExtra("numPassengers", 1));
                startActivity(i);
            }));
        }

        setAppBarLayout();
        TextView searchTitle = findViewById(R.id.search_results_title);
        TextView searchLocationTitle = findViewById(R.id.search_results_location_title);

        if (!showReturnFlightRslts) {
            searchTitle.setText(String.format(Locale.CANADA, "Departing %s", Utils.getFormattedDate(departDate)));
        } else {
            searchTitle.setText(String.format(Locale.CANADA, "Returning %s", Utils.getFormattedDate(departDate)));
        }
        searchLocationTitle.setText(String.format(Locale.CANADA, "From %s", selectedDeparture.getCity()));
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


    private void letsExplodeIt() {

        findViewById(R.id.window_image).startAnimation(AnimationUtils.loadAnimation(this,R.anim.zoom_out));
        TransitionSet transition1 = new TransitionSet();
        transition1.addTransition(new Fade(Fade.IN).addTarget(findViewById(R.id.search_results_title_layout)));
        transition1.addTransition(new Slide(Gravity.START).addTarget(findViewById(R.id.search_results_title_layout)));
//        transition1.addTransition(new Slide(Gravity.START).addTarget(findViewById(R.id.search_results_top_app_bar)));
        transition1.addTransition(new Slide(Gravity.BOTTOM).addTarget(findViewById(R.id.list_search_results)).setStartDelay(250));
        transition1.addTransition(new Fade(Fade.IN).addTarget(findViewById(R.id.search_results_text)).setStartDelay(200));
//        new PropagatingTransition(findViewById(R.id.header_layout_results),findViewById(R.id.window_image),transition1).start();
        transition1.setInterpolator(new AccelerateDecelerateInterpolator());
        transition1.setDuration(1000);
        transition1.setPropagation(new CircularPropagation());

        ArrayList<View> targets = new ArrayList<>();
//
        ViewGroup sceneRoot = findViewById(R.id.header_layout_results);
        for(int i = 0; i<sceneRoot.getChildCount();i++){
            targets.add(sceneRoot.getChildAt(i));
        }

        for(int i=0;i<targets.size();i++){
            targets.get(i).setVisibility(View.INVISIBLE);
        }
//        Transition transition = new Explode();
//        transition.excludeTarget(findViewById(R.id.search_results_scroll),true);
//
//        new PropagatingTransition(findViewById(R.id.search_results),findViewById(R.id.window_image),transition).start();

        TransitionManager.beginDelayedTransition(sceneRoot,transition1);

        for(int i=0;i<targets.size();i++){
            if(targets.get(i).getId()!= R.id.book_button && targets.get(i).getId()!= R.id.no_results_found)
                targets.get(i).setVisibility(View.VISIBLE);
        }

    }



}