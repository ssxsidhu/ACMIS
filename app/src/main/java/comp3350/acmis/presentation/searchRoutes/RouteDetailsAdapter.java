package comp3350.acmis.presentation.searchRoutes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessRouteFlights;

public class RouteDetailsAdapter extends RecyclerView.Adapter<RouteDetailsAdapter.Viewholder> {

    private final AccessRouteFlights routeDetails;
    //temporary

    // Constructor
    public RouteDetailsAdapter(Context context, AccessRouteFlights accessRouteFlights) {
        routeDetails = accessRouteFlights;
    }

    @NonNull
    @Override
    public RouteDetailsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_details_card_view, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteDetailsAdapter.Viewholder holder, int position) {
        routeDetails.setConnectFlightPos(position);
        if (position > 0) {
            holder.routeLayoverLayout.setVisibility(View.VISIBLE);
            holder.routeLayoverTime.setText(String.format(Locale.CANADA, "%s layover", routeDetails.getCurrLayoverTime(position-1,position)));
        }
        holder.detailsDepartTime.setText(routeDetails.getConnectDepartureTime());
        holder.detailsArriveTime.setText(routeDetails.getConnectArrivalTime());
        holder.detailsDepartAirport.setText(routeDetails.getConnectSource().getAirport());
        holder.detailsArriveAirport.setText(routeDetails.getConnectDestination().getAirport());
        holder.detailsDepartCity.setText(routeDetails.getConnectSource().getCity());
        holder.detailsArriveCity.setText(routeDetails.getConnectDestination().getCity());
        holder.detailsDuration.setText(routeDetails.getConnectDuration());
        if (position == routeDetails.getNumStops())
            holder.verticalStopImage.setImageResource(R.drawable.vertical_stop_to_dest_shape);
        else
            holder.verticalStopImage.setImageResource(R.drawable.vertical_stop_to_stop_shape);
    }


    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return routeDetails.getNumStops() + 1;
    }


    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        TextView detailsDepartTime, detailsArriveTime, detailsDepartAirport, detailsArriveAirport, detailsDepartCity, detailsArriveCity, detailsDuration, routeLayoverTime;
        LinearLayout routeLayoverLayout;
        ImageView verticalStopImage;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            detailsDepartTime = itemView.findViewById(R.id.details_depart_time);
            detailsArriveTime = itemView.findViewById(R.id.details_arrive_time);
            detailsDepartAirport = itemView.findViewById(R.id.details_depart_airport);
            detailsArriveAirport = itemView.findViewById(R.id.details_arrive_airport);
            detailsDepartCity = itemView.findViewById(R.id.details_depart_city);
            detailsArriveCity = itemView.findViewById(R.id.details_arrive_city);
            detailsDuration = itemView.findViewById(R.id.details_duration);
            verticalStopImage = itemView.findViewById(R.id.vertical_stops_image);
            routeLayoverLayout = itemView.findViewById(R.id.route_layover_time_layout);
            routeLayoverTime = itemView.findViewById(R.id.route_layover_time);
        }
    }
}
