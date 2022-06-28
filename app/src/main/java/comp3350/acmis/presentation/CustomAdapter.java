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

import org.threeten.bp.format.TextStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import comp3350.acmis.R;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Route;

//used to display lists
public class CustomAdapter extends BaseAdapter {

    private Fragment mContext = null;
    private Activity activity;
    private ArrayList<Route> displayList;


    public CustomAdapter(FragmentManage context, ArrayList<Booking> userBookings) {
        mContext = context;
        displayList = new ArrayList<>();
        for (int i = 0; i < userBookings.size(); i++) {
            displayList.add(userBookings.get(i).getRoute());
        }
    }

    public CustomAdapter(Activity activity, ArrayList<Route> flightsAvailable) {
        this.activity = activity;
        displayList = flightsAvailable;
    }

    public CustomAdapter(FragmentManage context, Booking booking) {
        mContext=context;
        displayList=new ArrayList<>();
        displayList.add(booking.getRoute());
    }

    public CustomAdapter(FragmentManage Context){
        mContext = Context;
    }

    public void setDisplayList(Booking booking){
        displayList=new ArrayList<>();
        displayList.add(booking.getRoute());
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
        TextView frontDate,frontMonth,frontYear;
        ImageView i1;
        i1 = (ImageView) row.findViewById(R.id.list_icon);
        frontDate = (TextView) row.findViewById(R.id.frontDate);
        frontMonth = (TextView) row.findViewById(R.id.frontMonth);
        frontYear = (TextView) row.findViewById(R.id.frontYear);
        titleTopLeft = (TextView) row.findViewById(R.id.list_top_text_left);
        titleTopRight = (TextView) row.findViewById(R.id.list_top_text_right);
        titleMiddleLeft = (TextView) row.findViewById(R.id.list_middle_text_left);
        titleMiddleRight = (TextView) row.findViewById(R.id.list_middle_text_right);
        titleBottomLeft = (TextView) row.findViewById(R.id.list_bottom_text_left);
        titleBottomRight = (TextView) row.findViewById(R.id.list_bottom_text_right);
        titleExtraLeft = (TextView) row.findViewById(R.id.list_extra_text_left);
        //for future use
        titleExtraRight = (TextView) row.findViewById(R.id.list_extra_text_right);

        if(displayList!=null && displayList.size()>0 && displayList.get(position).getRoute().size()>0) {
            frontDate.setText(String.format("%d", displayList.get(position).getRoute().get(0).getDepartureDateTime().getDayOfMonth()));
            frontMonth.setText(displayList.get(position).getRoute().get(0).getDepartureDateTime().getMonth().getDisplayName(TextStyle.SHORT, Locale.CANADA));
            frontYear.setText(String.format("%d", displayList.get(position).getRoute().get(0).getDepartureDateTime().getYear()));
            titleTopLeft.setText(displayList.get(position).getRoute().get(0).getDepartureTime());
            titleTopRight.setText(displayList.get(position).getRoute().get(0).getArrivalTime());
            titleMiddleLeft.setText(displayList.get(position).getRoute().get(0).getSource().getCity());
            titleMiddleRight.setText(displayList.get(position).getRoute().get(0).getDestination().getCity());
            titleBottomRight.setText(String.format("FlightID: #%d", displayList.get(position).getRoute().get(0).getFlightID()));
            i1.setImageResource(R.drawable.airplane_symbol);
//            titleExtraLeft.setText(String.format("Total Passengers: %d", displayList.get(position).getRoute().get(0).getPassengerList().size()));
        }

        return (row);
    }
}