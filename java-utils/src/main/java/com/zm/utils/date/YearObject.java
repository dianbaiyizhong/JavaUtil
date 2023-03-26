package com.zm.utils.date;

import java.util.List;
import java.util.Objects;


public class YearObject {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearObject that = (YearObject) o;
        return Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year);
    }

    private String year;

    private List<MonthObject> monthObjectList;
}
