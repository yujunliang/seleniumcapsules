package com.algocrafts.converters;


import com.algocrafts.selenium.Locator;

public enum StringConverter implements Locator<String, String> {
    RESTORE_UNDERSCORE {
        @Override
        public String locate(String element) {
            return element.replace(' ', '_');
        }
    },

    REPLACE_UNDERSCORE {
        @Override
        public String locate(String element) {
            return element.replace('_', ' ');
        }
    };
}
