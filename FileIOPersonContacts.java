package com.Bridgelabz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileIOPersonContacts implements PersonContacts {

    public final String filepath ="C:\\Users\\adesh\\IdeaProjects\\addressbook\\src\\com\\Bridgelabz\\contact.txt";




    @Override
    public void writeDataToDestination(List<Contact> contactsList ) throws IOException {

        StringBuffer sb = new StringBuffer();
        contactsList.forEach(contact-> {
            String contactString = contact.toString().concat("\n");
            sb.append(contactString);
        });
        Files.write(Path.of(filepath),sb.toString().getBytes());

    }



    @Override
    public void readDataFromDestination() throws IOException {
        Files.lines(Path.of(filepath)).forEach(contactString -> System.out.println(contactString));


    }

    @Override
    public long countEntries() throws IOException {
    return 0;
    }
}
