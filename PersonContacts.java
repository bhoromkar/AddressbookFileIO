package com.Bridgelabz;

import java.io.IOException;
import java.util.List;

public interface PersonContacts {
    void writeDataToDestination(List<Contact> contactsList) throws IOException;

    void readDataFromDestination() throws IOException;

    long countEntries() throws IOException;

}

