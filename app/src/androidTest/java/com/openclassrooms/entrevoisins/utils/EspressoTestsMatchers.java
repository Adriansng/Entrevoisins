package com.openclassrooms.entrevoisins.utils;

import android.view.View;
import org.hamcrest.Matcher;
public class EspressoTestsMatchers {

    public static Matcher<View> withDrawable (final int resourceId) {
        return new DrawableMatcher (resourceId);
    }

}
