package comp3350.acmis.presentation.searchRoutes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessRouteFlights;
import comp3350.acmis.objects.Route;
import comp3350.acmis.presentation.Utils;

public class SearchResultsCardsAdapter extends RecyclerView.Adapter<SearchResultsCardsAdapter.Viewholder> {


    private final ArrayList<Route> displayList;
    private final OnItemClickListener listener;
    private boolean isRouteOrderSelected = false;

    //temporary

    public interface OnItemClickListener {
        void onItemClick(Route item);
    }

    // Constructor
    public SearchResultsCardsAdapter(ArrayList<Route> flightsAvailable, boolean isRouteOrderSelected, OnItemClickListener listener) {
        displayList = flightsAvailable;
        this.listener = listener;
        this.isRouteOrderSelected = isRouteOrderSelected;
    }

    @NonNull
    @Override
    public SearchResultsCardsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_results_card_view, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultsCardsAdapter.Viewholder holder, int position) {
        AccessRouteFlights routeFlightDetails = new AccessRouteFlights(displayList.get(position));

        routeFlightDetails.setConnectFlightPos(0);
        holder.departTime.setText(routeFlightDetails.getConnectDepartureTime());
        holder.departAirport.setText(routeFlightDetails.getConnectSource().getAirport());
        if (isRouteOrderSelected) {
            holder.routeDate.setVisibility(View.VISIBLE);
            holder.routeDate.setText(String.format(Locale.CANADA, "   %s, %d", Utils.getFormattedDate(routeFlightDetails.getConnectDepartureZdt().toLocalDate()), routeFlightDetails.getConnectDepartureZdt().getYear()));
            holder.flightPrice.setVisibility(View.GONE);
        }

        routeFlightDetails.setConnectFlightPos(routeFlightDetails.getNumStops());

        holder.arriveTime.setText(routeFlightDetails.getConnectArrivalTime());
        holder.destAirport.setText(routeFlightDetails.getConnectDestination().getAirport());
        holder.flightDuration.setText(routeFlightDetails.getRouteTotalDuration());
        holder.flightPrice.setText(String.format(Locale.CANADA, "$%d", routeFlightDetails.getRouteTotalCost()));

        int stopShape = 0;
        String numStops;
        switch (routeFlightDetails.getNumStops()) {
            case 1:
                stopShape = R.drawable.one_stop_shape;
                numStops = "1 Stop: ";
                break;
            case 2:
                stopShape = R.drawable.two_stop_shape;
                numStops = "2 Stops: ";
                break;
            case 3:
                stopShape = R.drawable.three_stop_shape;
                numStops = "3 Stops: ";
                break;
            default:
                stopShape = R.drawable.no_stop_shape;
                numStops = "No Stops";
        }
        holder.numStops.setImageResource(stopShape);
        holder.stopNames.setText(String.format("%s%s", numStops, routeFlightDetails.stopNames()));
        holder.bind(displayList.get(position), listener);
    }


    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return displayList.size();
    }


    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private final TextView departTime;
        private final TextView arriveTime;
        private final TextView departAirport;
        private final TextView destAirport;
        private final TextView flightDuration;
        private final TextView flightPrice;
        private final TextView stopNames;
        private final TextView routeDate;
        private final ImageView numStops;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            departTime = itemView.findViewById(R.id.depart_time);
            arriveTime = itemView.findViewById(R.id.arrive_time);
            departAirport = itemView.findViewById(R.id.available_depart_airport);
            destAirport = itemView.findViewById(R.id.available_dest_airport);
            flightDuration = itemView.findViewById(R.id.flight_duration);
            flightPrice = itemView.findViewById(R.id.available_price);
            stopNames = itemView.findViewById(R.id.stopover_names);
            routeDate = itemView.findViewById(R.id.route_date);
            numStops = itemView.findViewById(R.id.num_stops_image);
        }

        public void bind(final Route item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
