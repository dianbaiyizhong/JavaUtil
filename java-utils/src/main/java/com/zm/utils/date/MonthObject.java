package com.zm.utils.date;

import java.util.List;
import java.util.Objects;

public class MonthObject {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthObject that = (MonthObject) o;
        return Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month);
    }

    private String month;
    private List<DayObject> dayObjectList;
}
