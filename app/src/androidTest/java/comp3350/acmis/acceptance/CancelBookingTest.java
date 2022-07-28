package comp3350.acmis.acceptance;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import comp3350.acmis.R;
import comp3350.acmis.application.Services;
import comp3350.acmis.business.BookingManager;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Route;
import comp3350.acmis.persistence.DataAccess;
import comp3350.acmis.persistence.DataAccessStub;
import comp3350.acmis.presentation.FrontPageActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CancelBookingTest {

    @Rule
    public ActivityScenarioRule<FrontPageActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(FrontPageActivity.class);

    @Test
    public void cancelBookingTest() {

        DataAccess db = Services.createDataAccess(new DataAccessStub());
        Services.dataAccessOpen();

        BookingManager bookingManager =  new BookingManager();
        ArrayList<Flight> testFlights = new ArrayList<>();
        db.getAllFlights(testFlights);
        Route route = new Route(testFlights.get(0));
        bookingManager.createBooking("braico",route,null,1);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.tab_2), withContentDescription("Manage flight"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.card_view_list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.cancel_Booking_Button), withText("Cancel booking"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton6.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
