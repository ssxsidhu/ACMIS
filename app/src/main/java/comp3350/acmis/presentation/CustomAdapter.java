package comp3350.acmis.presentation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import comp3350.acmis.R;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Route;

public class CustomAdapter extends BaseAdapter {

    private Fragment mContext = null;
    private Activity activity;
    private ArrayList<Route> displayList;
    private ArrayList<Booking> userBookings;


    public CustomAdapter(FragmentManage context, ArrayList<Booking> userBookings) {
        mContext = context;
        displayList = new ArrayList<>();
        for (int i = 0; i < userBookings.size(); i++) {
            displayList.add(userBookings.get(i).getRoute());
        }
        this.userBookings=userBookings;
    }

    public CustomAdapter(Activity activity, ArrayList<Route> flightsAvailable) {
        this.activity = activity;
        displayList = flightsAvailable;
    }


    @Override
    public int getCount() {
        return displayList.size();
    }

    @Override
    public Route getItem(int i) {
        return displayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"ViewHolder", "DefaultLocale"})
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater;
        if(mContext != null) {
            inflater = mContext.getLayoutInflater();
        }
        else {
            inflater=activity.getLayoutInflater();
        }

        View row;
        row = inflater.inflate(R.layout.row, parent, false);
        TextView titleTopLeft, titleTopRight, titleMiddleLeft, titleMiddleRight, titleBottomLeft, titleBottomRight,titleExtraLeft,titleExtraRight;
        ImageView i1;
        i1 = (ImageView) row.findViewById(R.id.list_icon);
        titleTopLeft = (TextView) row.findViewById(R.id.list_top_text_left);
        titleTopRight = (TextView) row.findViewById(R.id.list_top_text_right);
        titleMiddleLeft = (TextView) row.findViewById(R.id.list_middle_text_left);
        titleMiddleRight = (TextView) row.findViewById(R.id.list_middle_text_right);
        titleBottomLeft = (TextView) row.findViewById(R.id.list_bottom_text_left);
        titleBottomRight = (TextView) row.findViewById(R.id.list_bottom_text_right);
        titleExtraLeft = (TextView) row.findViewById(R.id.list_extra_text_left);
        titleExtraRight = (TextView) row.findViewById(R.id.list_extra_text_right);

        if(displayList!=null && displayList.size()>0 && displayList.get(position).getRoute().size()>0) {
            titleTopLeft.setText(displayList.get(position).getRoute().get(0).getDepartureTime());
            titleTopRight.setText(displayList.get(position).getRoute().get(0).getArrivalTime());
            titleMiddleLeft.setText(displayList.get(position).getRoute().get(0).getSource().getCity());
            titleMiddleRight.setText(displayList.get(position).getRoute().get(0).getDestination().getCity());
            titleBottomLeft.setText(displayList.get(position).getRoute().get(0).getDepartureDate());
            titleBottomRight.setText(String.format("FlightID: #%d", displayList.get(position).getRoute().get(0).getFlightID()));
            if(userBookings!=null) {
                titleExtraLeft.setText(String.format("Passengers: %d", userBookings.get(position).getNumPassengers()));
            }
            i1.setImageResource(R.drawable.airplane_symbol);
        }

        return (row);
    }
}