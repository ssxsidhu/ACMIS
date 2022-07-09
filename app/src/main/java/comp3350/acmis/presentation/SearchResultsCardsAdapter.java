package comp3350.acmis.presentation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import comp3350.acmis.R;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Route;

public class SearchResultsCardsAdapter extends RecyclerView.Adapter<SearchResultsCardsAdapter.Viewholder> {

    private Context context;
    private ArrayList<Route> displayList;
    //temporary

    // Constructor
    public SearchResultsCardsAdapter(Context context, ArrayList<Route> flightsAvailable) {
        this.context = context;
        displayList = flightsAvailable;
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
       holder.departTime.setText(displayList.get(position).getRoute().get(0).getDepartureTime());
       System.out.println(displayList.get(position).getRoute().get(0).getArrivalTime());
        holder.arriveTime.setText(displayList.get(position).getRoute().get(0).getArrivalTime());
        holder.departAirport.setText(displayList.get(position).getRoute().get(0).getSource().getAirport());
        holder.destAirport.setText(displayList.get(position).getRoute().get(0).getDestination().getAirport());
        holder.flightDuration.setText(String.format(Locale.CANADA,"%d h", displayList.get(position).getRoute().get(0).getDuration().toHours()));
        holder.flightPrice.setText("$ 369");
        holder.numStops.setImageResource(R.drawable.no_stop_shape);
        holder.stopNames.setText("No Stops");
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
        private TextView departTime,arriveTime,departAirport,destAirport,flightDuration,flightPrice,stopNames;
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
            numStops = itemView.findViewById(R.id.num_stop_image);
        }
    }
}
