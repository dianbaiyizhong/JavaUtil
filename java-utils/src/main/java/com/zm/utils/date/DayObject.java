package com.zm.utils.date;

import java.util.Objects;

public class DayObject {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayObject dayObject = (DayObject) o;
        return Objects.equals(day, dayObject.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day);
    }

    private String day;

}
