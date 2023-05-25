package com.Bridgelabz;


    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Comparator;
    import java.util.List;
    import java.util.stream.Collectors;

public class AddressBook {
   PersonContacts personContact;
    List<Contact> contactsList1;
    private String name;
     public List<Contact> contactsList;

    public AddressBook(String name) {
        this.name = name;
        this.contactsList = new ArrayList<>();
    }
    public AddressBook(List<Contact>  contactsList, PersonContacts personContacts) {
        this.personContact=personContacts;
        this.contactsList1 = new ArrayList<>();
    }

    public AddressBook() {
    }


    public String getName() {
        return name;
    }

    public List<Contact> getcontactsListList() {
        return contactsList;
    }

    public boolean addContact(Contact contact) {
        if (contactsList.stream().anyMatch(p -> p.getFirstName().equalsIgnoreCase(contact.getFirstName()))) {
            System.out.println("Duplicate entry: " + contact.getFirstName());
            return false;
        } else {
            contactsList.add(contact);
            System.out.println("Added: " + contact.getFirstName());
            return true;
        }
    }
    public void updateContact(String firstName, String lastName, Contact updatedContact) {
        for (int i = 0; i < contactsList.size(); i++) {
            Contact contact = contactsList.get(i);
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                contactsList.set(i, updatedContact);
                break;
            }
        }
    }

    public boolean deleteContact(String firstName, String lastName) {
        contactsList.removeIf(contact -> contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName));
        return false;
    }

    public List<Contact> searchByCity(String city) {
        return contactsList.stream()
                .filter(contact -> contact.getCity().equals(city))
                .toList();
    }
 //.stream().filter(contact -> contact.getstate.equal(state)).collect(Collectors.toList());
    public List<Contact> searchByState(String state) {
        return contactsList.stream()
                .filter(contact -> contact.getState().equals(state))
                .toList();
    }

    public int getCountByCity(String city) {
        return (int) contactsList.stream()
                .filter(contact -> contact.getCity().equals(city))
                .count();
    }

    public int getCountByState(String state) {
        return (int) contactsList.stream()
                .filter(contact -> contact.getState().equals(state))
                .count();
    }

    public String searchByPhoneNumber(String name) {
        return contactsList.stream().filter(contact -> contact.getFirstName().equals(name)).collect(Collectors.toList()).get(0).getPhoneNumber();


    }

        public void sortContactsByName() {
            List<Contact> sortedContacts = contactsList.stream()
                    .sorted(Comparator.comparing(Contact::getFirstName))
                    .collect(Collectors.toList());
            System.out.println("Contacts sorted by name:");
            for (Contact contact : sortedContacts) {
                System.out.println(contact);
            }
        }
    public void sortContactsByCity() {
        contactsList.sort((c1, c2) -> c1.getCity().compareTo(c2.getCity()));
    }

    public void sortContactsByState() {
        contactsList.sort((c1, c2) -> c1.getState().compareTo(c2.getState()));
    }

    public void sortContactsByZip() {
        contactsList.sort((c1, c2) -> c1.getZip().compareTo(c2.getZip()));
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "name='" + name + '\'' +
                ", contactsList=" + contactsList +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Contact)) {
            return false;
        }
        Contact person = (Contact) obj;
        return name.equalsIgnoreCase(person.getFirstName());
    }

    public void writeData(PersonContacts personContacts) throws IOException {
        try {

            personContacts.writeDataToDestination(contactsList);
        }catch(IOException e){
            System.out.println("catch");
        }

    }

    public void readData(PersonContacts personContacts) throws IOException {
        personContact.readDataFromDestination();
    }
    public void writeDataForCSV(PersonContacts personContacts) throws IOException {

        try {
            personContacts.writeDataToDestination(contactsList);
        }catch(IOException e){
            System.out.println("catch");
        }

    }
}




