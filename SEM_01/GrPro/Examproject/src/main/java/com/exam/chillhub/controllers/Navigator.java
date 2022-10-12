package com.exam.chillhub.controllers;

import com.exam.chillhub.models.Model;

/**
 * A Navigator is anything that can be navigated to and that cause further navigation. When it is navigated to it is passed
 * an object it can use to invoke future navigation.
 */
public interface Navigator {
    /**
     * Method that is called when this is navigated to.
     *
     * @param navigable An object the navigator can call to cause navigation away from itself.
     * @param model     The model to set on the navigator.
     */
    void onNavigateTo(Navigable navigable, Model model);
}
