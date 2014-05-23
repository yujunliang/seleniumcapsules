package com.algocrafts.clickables;


import com.algocrafts.pages.Searchable;

public class ImageButton<Where extends Searchable<Where>> extends Button<Where> {

    public ImageButton(Where where, String fileName, int index) {
        super(where, input -> where.image(fileName, index));
    }
}
