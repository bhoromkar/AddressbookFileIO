package com.Bridgelabz;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class CsvFileReadAndWrite implements PersonContacts {
    public static final String file = "C:\\Users\\adesh\\IdeaProjects\\addressbook\\src\\com\\Bridgelabz\\Contact.CSV";


    @Override
    public void writeDataToDestination(List<Contact> contactsList) throws IOException {
        Writer writer = Files.newBufferedWriter(Path.of(file));

        contactsList.stream().flatMap(contacts -> contactsList.stream()).flatMap(contact -> Stream.of("Name :" + contact.getFirstName(), "LastName :" + contact.getLastName(), "PhoneNumber:" + contact.getPhoneNumber(),
                "Address:" + contact.getAddress(),
                "City :" + contact.getCity(),
                "State :" + contact.getState(),
                "Zip :" + contact.getZip())).forEach(contact -> {
            try {
                writer.write(contact);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public void readDataFromDestination() throws IOException {

    }

    @Override
    public long countEntries() throws IOException {
        return 0;
    }
}

