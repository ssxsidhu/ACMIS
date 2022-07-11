package comp3350.acmis.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import comp3350.acmis.R;
import comp3350.acmis.business.AccessRouteFlights;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Route;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.Viewholder> {

    private Context mContext;
    private ArrayList<Booking> displayList;
    // Constructor
    public CardsAdapter(Context context,ArrayList<Booking> userBookings) {
        mContext = context;
        displayList = userBookings;
    }

    @NonNull
    @Override
    public CardsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.Viewholder holder, int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<Route> bookedRouteList = new ArrayList<>();
        Booking currBooking = displayList.get(position);
        bookedRouteList.add(currBooking.getRouteDepart());

        if(currBooking.checkForReturn()) {
            bookedRouteList.add(currBooking.getRouteReturn());
            holder.directionImage.setImageResource(R.drawable.ic_round_u_turn_right_24);
        }
        AccessRouteFlights accessRouteFlights = new AccessRouteFlights(currBooking.getRouteDepart());

        holder.cityDepart.setText(accessRouteFlights.getConnectSource().getCity());
        accessRouteFlights.setConnectFlightPos(accessRouteFlights.getNumStops());
        holder.cityDest.setText(accessRouteFlights.getConnectDestination().getCity());

        holder.numPassengers.setText(String.format(Locale.CANADA,"%d ", currBooking.getNumPassengers()));

        holder.recyclerView.setAdapter(new SearchResultsCardsAdapter(bookedRouteList,true, new SearchResultsCardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Route item) {
                Intent i = new Intent(mContext, RouteDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("selectedRoute", item);
                i.putExtra("continueButtonVisibility",false);
                mContext.startActivity(i);
            }
        }));

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
        private RecyclerView recyclerView;
        private TextView cityDepart,cityDest,numPassengers;
        private ImageView directionImage;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.card_view_list);
            cityDepart = itemView.findViewById(R.id.card_city_depart);
            cityDest = itemView.findViewById(R.id.card_city_arrive);
            numPassengers = itemView.findViewById(R.id.card_num_passengers);
            directionImage = itemView.findViewById(R.id.card_direction_image);
        }
    }
}
