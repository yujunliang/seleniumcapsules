package com.algocrafts.clickables;


import com.algocrafts.selenium.SearchScope;

public class ImageButton<T extends SearchScope<T>> extends Button<T> {

    public ImageButton(T where, String fileName, int index) {
        super(where, finder -> finder.image(fileName, index));
    }
}
