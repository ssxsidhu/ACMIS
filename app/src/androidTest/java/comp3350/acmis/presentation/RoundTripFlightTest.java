package comp3350.acmis.presentation;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.acmis.R;
import comp3350.acmis.application.Services;
import comp3350.acmis.persistence.DataAccessStub;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RoundTripFlightTest {

    @Rule
    public ActivityScenarioRule<FrontPageActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(FrontPageActivity.class);

    @Test
    public void roundTripFlightTest() {
        Services.createDataAccess(new DataAccessStub());
        Services.dataAccessOpen();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialAutoCompleteTextView = onView(
                allOf(withId(R.id.auto_departure),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.menu_departure),
                                        0),
                                0),
                        isDisplayed()));
        materialAutoCompleteTextView.perform(click());

        onView(withText("Winnipeg, Canada"))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());

        ViewInteraction materialAutoCompleteTextView2 = onView(
                allOf(withId(R.id.auto_destination),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.menu_destination),
                                        0),
                                0),
                        isDisplayed()));
        materialAutoCompleteTextView2.perform(click());

        onView(withText("Vancouver, Canada"))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());

        ViewInteraction switchMaterial = onView(
                allOf(withId(R.id.round_trip_switch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.book_details),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.pick_depart_date), withText("Depart"),
                        childAtPosition(
                                allOf(withId(R.id.book_details),
                                        childAtPosition(
                                                withId(R.id.fragment_container_book),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        onView(withContentDescription("Saturday, July 30, 2022"))
                .inRoot(RootMatchers.isDialog())
                .perform(click());
        onView(withText("OK"))
                .inRoot(RootMatchers.isDialog())
                .perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.pick_return_date), withText("Return"),
                        childAtPosition(
                                allOf(withId(R.id.book_details),
                                        childAtPosition(
                                                withId(R.id.fragment_container_book),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton3.perform(click());

        onView(withContentDescription("Sunday, July 31, 2022"))
                .inRoot(RootMatchers.isDialog())
                .perform(click());
        onView(withText("OK"))
                .inRoot(RootMatchers.isDialog())
                .perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.search_flights_button), withText("Search Flights"),
                        childAtPosition(
                                allOf(withId(R.id.book_details),
                                        childAtPosition(
                                                withId(R.id.fragment_container_book),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton5.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_search_results),
                        childAtPosition(
                                withId(R.id.header_layout_results),
                                5)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.details_continue), withText("Continue"),
                        childAtPosition(
                                allOf(withId(R.id.route_details_continue_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        materialButton6.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.list_search_results),
                        childAtPosition(
                                withId(R.id.header_layout_results),
                                5)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.details_continue), withText("Continue"),
                        childAtPosition(
                                allOf(withId(R.id.route_details_continue_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        materialButton7.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.book_button), withText("Book Flight"),
                        childAtPosition(
                                allOf(withId(R.id.search_results),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton8.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
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

        ViewInteraction linearLayout = onView(
                allOf(withParent(allOf(withId(R.id.card),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.card_city_depart), withText("Winnipeg"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("Winnipeg")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.card_city_arrive), withText("Vancouver"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView2.check(matches(withText("Vancouver")));


        ViewInteraction textView3 = onView(
                allOf(withId(R.id.route_date), withText("   Sat, Jul 30, 2022"),
                        withParent(withParent(withId(R.id.result_card))),
                        isDisplayed()));
        textView3.check(matches(withText("   Sat, Jul 30, 2022")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.route_date), withText("   Sun, Jul 31, 2022"),
                        withParent(withParent(withId(R.id.result_card))),
                        isDisplayed()));
        textView4.check(matches(withText("   Sun, Jul 31, 2022")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.route_date), withText("   Sun, Jul 31, 2022"),
                        withParent(withParent(withId(R.id.result_card))),
                        isDisplayed()));
        textView5.check(matches(withText("   Sun, Jul 31, 2022")));
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
