package comp3350.acmis.presentation;


import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.TextStyle;

import java.util.Locale;

import comp3350.acmis.R;

public class Utils {

    public static void setStatusBarColor(Window window, Context context){
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(context, R.color.md_theme_dark_shadow));
    }

    public static String getFormattedDate(LocalDate localDate){
        String formattedDate;
        formattedDate = localDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CANADA)+", ";
        formattedDate += localDate.getMonth().getDisplayName(TextStyle.SHORT,Locale.CANADA)+" "+localDate.getDayOfMonth();
        return formattedDate;
    }
}
