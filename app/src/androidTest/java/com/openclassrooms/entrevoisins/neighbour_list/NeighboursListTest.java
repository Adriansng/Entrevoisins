
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.AddNeighbourEvent;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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
        // When perform a click on launch activity
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        // Check item DetailActivity
        onView(withId(R.id.detail_neighbour)).check(matches(isDisplayed()));
    }

    @Test
    public void FavoriteButtonClick() {
        // Given: We add the neighbour in favorite
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
        // When: click button for remove the neighbour in favorite
        onView(withId(R.id.favory_button)).perform(click());
        // Then: color button (neighbour no favorite)
        onView(withId(R.id.favory_button)).check(matches(withDrawable(R.drawable.no_yellow_star)));
    }
}