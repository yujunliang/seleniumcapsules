package com.algocrafts.table;


import com.algocrafts.selenium.Searchable;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class TableSpecification<T, Where extends Searchable<Where>> {

    private final Set<String> headers;
    private final Set<T> rows;
    private SetDiff<?> diff;

    public TableSpecification(Set<String> headers, Set<T> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    public boolean pass(Table<T, Where> actual) {
        Set<String> set = actual.getHeader().collect(toSet());
        if (!headers.equals(set)) {
            diff = new SetDiff<>("headers are different,", headers, set);
            return false;
        } else {
            Set<T> rows = actual.getRows().collect(toSet());
            if (!this.rows.equals(rows)) {
                diff = new SetDiff<>("rows are different,", this.rows, rows);
                return false;
            }
            return true;
        }
    }

    @Override
    public String toString() {
        return diff.toString();
    }
}
