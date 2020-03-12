
package com.openclassrooms.entrevoisins.neighbour_list;



import android.content.Intent;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;

import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import android.support.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourProfileActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    NeighbourApiService mNeighbourApiService;
    Neighbour theNeighbour;
    String name;
    List<Neighbour> mNeighbours;

    DummyNeighbourApiService mApiService = new DummyNeighbourApiService();

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule = new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mNeighbours = mApiService.getNeighbours();
        Neighbour actualNeighbour = mNeighbours.get(2);
        name = actualNeighbour.getName();
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mNeighbourApiService = DI.getNeighbourApiService();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(isDisplayed(), ViewMatchers.withId(R.id.list_neighbours)))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(isDisplayed(), ViewMatchers.withId(R.id.list_neighbours))).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(isDisplayed(), ViewMatchers.withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(isDisplayed(), ViewMatchers.withId(R.id.list_neighbours))).check(withItemCount(ITEMS_COUNT-1));
    }

    // INSTRUMENTED TEST ADDED BY ME

    @Test
    public void neighbour_profile_is_displayed(){
        onView(allOf(isDisplayed(), ViewMatchers.withId(R.id.list_neighbours))).perform(click());
        onView(ViewMatchers.withId(R.id.frame_to_test)).check(matches(isDisplayed()));
    }

    @Test
    public void neighbour_name_is_correct(){
        onView(allOf(isDisplayed(), ViewMatchers.withId(R.id.list_neighbours))).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(ViewMatchers.withId(R.id.neighbour_name)).check(matches(withText(name)));
    }

    @Test
    public void only_favorite_neighbours_in_list(){
        onView(allOf(isDisplayed(), ViewMatchers.withId(R.id.list_neighbours))).perform(click());
        onView(ViewMatchers.withId(R.id.favButton)).perform(click());
        pressBack();
        onView(withContentDescription("FAVORITES")).perform(click());
        List<Neighbour> mFavoriteList = mNeighbourApiService.getFavoriteNeighbours();
        for (Neighbour theNeighbour:mNeighbours) {
            String toCheckName = theNeighbour.getName();
            if (mFavoriteList.contains(theNeighbour)) {
                onView(allOf(isDisplayed(), ViewMatchers.withId(R.id.item_list_name))).check(matches(withText(toCheckName)));
            }
            else
                onView(allOf(isDisplayed(), ViewMatchers.withText(toCheckName))).check(doesNotExist());
        }
    }
}