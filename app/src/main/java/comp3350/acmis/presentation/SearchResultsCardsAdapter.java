package comp3350.acmis.presentation;

import android.content.Context;
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

public class SearchResultsCardsAdapter extends RecyclerView.Adapter<SearchResultsCardsAdapter.Viewholder> {

    private Context context;
    private ArrayList<Route> displayList;
    private AccessRouteFlights routeFlightDetails;
    private final OnItemClickListener listener;
    //temporary

    public interface OnItemClickListener {
        void onItemClick(Route item);
    }
    // Constructor
    public SearchResultsCardsAdapter(Context context, ArrayList<Route> flightsAvailable, OnItemClickListener listener) {
        this.context = context;
        displayList = flightsAvailable;
        this.listener = listener;
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
        routeFlightDetails = new AccessRouteFlights(displayList.get(position));

        routeFlightDetails.setConnectFlightPos(0);
        holder.departTime.setText(routeFlightDetails.getConnectDepartureTime());
        holder.departAirport.setText(routeFlightDetails.getConnectSource().getAirport());

        routeFlightDetails.setConnectFlightPos(routeFlightDetails.getNumStops());

        holder.arriveTime.setText(routeFlightDetails.getConnectArrivalTime());
        holder.destAirport.setText(routeFlightDetails.getConnectDestination().getAirport());
        holder.flightDuration.setText(routeFlightDetails.getRouteTotalDuration());
        holder.flightPrice.setText(String.format(Locale.CANADA,"$%d", routeFlightDetails.getRouteTotalCost()));

        int stopShape = 0;
        String numStops;
        switch (routeFlightDetails.getNumStops()){
            case 1:
                stopShape = R.drawable.one_stop_shape;
                numStops = "1 Stop";
                break;
            case 2:
                stopShape = R.drawable.two_stop_shape;
                numStops = "2 Stops";
                break;
            case 3:
                stopShape = R.drawable.three_stop_shape;
                numStops = "3 Stops";
                break;
            default:
                stopShape = R.drawable.no_stop_shape;
                numStops = "No Stops";
        }
        holder.numStops.setImageResource(stopShape);
        holder.stopNames.setText(numStops);
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
        private TextView departTime, arriveTime, departAirport, destAirport, flightDuration, flightPrice, stopNames;
        private ImageView numStops;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            departTime = itemView.findViewById(R.id.depart_time);
            arriveTime = itemView.findViewById(R.id.arrive_time);
            departAirport = itemView.findViewById(R.id.available_depart_airport);
            destAirport = itemView.findViewById(R.id.available_dest_airport);
            flightDuration = itemView.findViewById(R.id.flight_duration);
            flightPrice = itemView.findViewById(R.id.available_price);
            stopNames = itemView.findViewById(R.id.stopover_names);
            numStops = itemView.findViewById(R.id.num_stops_image);
        }

        public void bind(final Route item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
