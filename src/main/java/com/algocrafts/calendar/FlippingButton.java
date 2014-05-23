package com.algocrafts.calendar;

enum FlippingButton {
    YEAR_FLIPPER {
        @Override
        void next(Calendar calendar) {
            calendar.nextYear();
        }

        @Override
        void previous(Calendar calendar) {
            calendar.previousYear();
        }

        @Override
        int current(Calendar calendar) {
            return calendar.currentYear();
        }
    },
    MONTH_FLIPPER {
        @Override
        void next(Calendar calendar) {
            calendar.nextMonth();
        }

        @Override
        void previous(Calendar calendar) {
            calendar.previousMonth();
        }

        @Override
        int current(Calendar calendar) {
            return calendar.currentMonth();
        }
    };

    abstract void next(Calendar calendar);

    abstract void previous(Calendar calendar);

    abstract int current(Calendar calendar);

    void flip(Calendar calendar, int flipTo) {
        int difference = current(calendar) - flipTo;
        if (difference < 0) {
            for (int i = difference; i < 0; i++) {
                next(calendar);
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                previous(calendar);
            }
        }
    }
}
