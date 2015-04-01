package com.algocrafts.converters;

import com.algocrafts.objectcache.OneKeyLFUPolicy;
import com.algocrafts.objectcache.SelfPopulatingCache;
import com.algocrafts.selenium.Locator;

import java.time.Month;

import static com.algocrafts.objectcache.SelfPopulatingCache.Creator;
import static com.algocrafts.converters.StringToMonth.MonthMapper.MONTH_MAPPER;

public enum StringToMonth implements Locator<String, Month> {

    TO_MONTH;

    @Override
    public Month locate(String element) {
        return maps.valueOf(element.toUpperCase());
    }

    private static final SelfPopulatingCache<String, Month> maps = new SelfPopulatingCache<>(48, new OneKeyLFUPolicy<>(), MONTH_MAPPER);

    enum MonthMapper implements Creator<String, Month> {
        MONTH_MAPPER;

        @Override
        public Month create(String key) {

            Month month = null;
            try {
                month = Month.valueOf(key);
            } catch (IllegalArgumentException e) {
                String newKey;
                if (key.contains(".")) {
                    newKey = key.replace(".", "");
                } else {
                    newKey = key;
                }
                for (Month m : Month.values()) {
                    if (m.name().contains(newKey)) {
                        return m;
                    }
                }
            }
            return month;
        }
    }

}
