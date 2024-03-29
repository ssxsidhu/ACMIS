package comp3350.acmis.acceptance;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
import comp3350.acmis.presentation.FrontPageActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SortSearchResultsTest {

    @Rule
    public ActivityScenarioRule<FrontPageActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(FrontPageActivity.class);

    @Test
    public void sortSearchResultsTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
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

        onView(withText("Regina, Canada"))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());

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

        onView(withContentDescription("Tuesday, August 9, 2022"))
                .inRoot(RootMatchers.isDialog())
                .perform(click());
        onView(withText("OK"))
                .inRoot(RootMatchers.isDialog())
                .perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.search_flights_button), withText("Search Flights"),
                        childAtPosition(
                                allOf(withId(R.id.book_details),
                                        childAtPosition(
                                                withId(R.id.fragment_container_book),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView = onView(
                allOf(withId(R.id.available_price), withText("$560"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("$560")));

        ViewInteraction extendedFloatingActionButton = onView(
                allOf(withId(R.id.sort_button), withText("Sort"),
                        childAtPosition(
                                allOf(withId(R.id.search_results),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        extendedFloatingActionButton.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.list_sort_options),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(0);
        linearLayout.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.available_price), withText("$320"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView2.check(matches(withText("$320")));
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
