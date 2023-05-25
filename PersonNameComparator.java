package com.Bridgelabz;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class PersonNameComparator<AddressBook> implements Comparator<Contact> {

    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }

}
