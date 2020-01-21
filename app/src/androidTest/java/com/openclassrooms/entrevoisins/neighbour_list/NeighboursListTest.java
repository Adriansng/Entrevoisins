
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.AddNeighbourEvent;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.EspressoTestsMatchers.withDrawable;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule<>(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        ListNeighbourActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        // This is fixed
        int ITEMS_COUNT = 12;
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT -1));
    }

    @Test
    public void AddingRandomNeighbour(){
        // Given: We add the element
        // This is fixed
        int ITEMS_COUNT =12;
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a add icon
        onView(allOf(withId(R.id.activity_add_neighbour_btn),isDisplayed())).perform(click());
        // Then : the number of element is 13
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT +1));

    }

    @Test
    public void detailActivityLaunch() {
        // Given: We launch Detail Activity
        // This is fixed
        String ITEM_NAME= "Caroline";
        // When perform a click on launch activity
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        // Then item DetailActivity
        onView(withId(R.id.detail_neighbour)).check(matches(isDisplayed()));
        // Then item name neighbour
        onView(allOf(withId(R.id.name_description_txt),isDisplayed())).check(matches(withText(ITEM_NAME)));
        // Then item avatar neighbour
        //onView(allOf(withId(R.id.detail_avatar_neighbour),isDisplayed())).check(matches(withText(ITEM_AVATAR)));
    }

    @Test
    public void FavoriteButtonClick() {
        // Given: We add the neighbour in favorite
        // WHEN: CHECK 3 Neighbours favorite in tab favorite
        int ITEMS_FAVORITE=3;
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(swipeLeft());
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_FAVORITE));
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(swipeRight());
        // When perform a click on launch activity
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        // Then: favorite button is clickable
        onView(withId(R.id.favory_button))
                .check(matches(isClickable()));
        // Then:  color button (neighbour no favorite)
        onView(withId(R.id.favory_button)).check(matches(withDrawable(R.drawable.no_yellow_star)));
        // When: click button for add the neighbour in favorite
        onView(withId(R.id.favory_button)).perform(click());
        // Then: color button (neighbour favorite)
        onView(withId(R.id.favory_button)).check(matches(withDrawable(R.drawable.yellow_star)));
        // Then: check add neighbour on list favorite
        ViewInteraction appCompatImageButton4 = onView(
                Matchers.allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                Matchers.allOf(withId(R.id.toolbar2),
                                        childAtPosition(
                                                Matchers.allOf(withId(R.id.collapsingTollBar), withContentDescription("Caroline")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton4.perform(click());
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(swipeLeft());
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_FAVORITE+1));
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(swipeRight());
        // When: click button for remove the neighbour in favorite
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.favory_button)).perform(click());
        // Then: color button (neighbour no favorite)
        onView(withId(R.id.favory_button)).check(matches(withDrawable(R.drawable.no_yellow_star)));
        // Then: check remove neighbour on list favorite
        appCompatImageButton4.perform(click());
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(swipeLeft());
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_FAVORITE));
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