package com.exam.chillhub.controllers;

import com.exam.chillhub.enums.View;
import com.exam.chillhub.models.Model;

/**
 * A Navigable is anything that can exchange the view it is displaying with another. It keeps track of exchanges,
 * allowing backwards navigation.
 */
public interface Navigable {
    /**
     * Navigates the given view, passing the given model to that view's controller. Implementers should store a new
     * navigation frame when this is called.
     *
     * @param view  The view to navigate to.
     * @param model The model for the given view's controller.
     */
    void navigateTo(View view, Model model);

    /**
     * Navigates backwards to the previous view if any exists.
     */
    void navigateBack();

    /**
     * Represents a frame in a navigation stack.
     */
    record NavigationFrame(View view, Model model) {
    }
}
