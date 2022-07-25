package comp3350.acmis.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import comp3350.acmis.R;

public class Messages {

    public static void makeToast(Context context, String message) {
        Toast toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void noFlightsMessage(Activity owner) {
        owner.findViewById(R.id.no_results_found).setVisibility(View.VISIBLE);
        owner.findViewById(R.id.search_results_text).setVisibility(View.INVISIBLE);
        owner.findViewById(R.id.dotted_bottom_line).setVisibility(View.INVISIBLE);
    }

    public static void warning(Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.warning));
        alertDialog.setMessage(message);

        alertDialog.show();
    }
}