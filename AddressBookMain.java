package com.Bridgelabz;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddressBookMain {
    public static final String fileCSV = "C:\\Users\\adesh\\IdeaProjects\\addressbook\\src\\com\\Bridgelabz\\Contact.CSV";
    public static final String fileJson = "C:\\Users\\adesh\\IdeaProjects\\addressbook\\src\\com\\Bridgelabz\\Contact.json";


    public static final String filepath = "C:\\Users\\adesh\\IdeaProjects\\addressbook\\src\\com\\Bridgelabz\\contact.txt";

    private static final Scanner scanner = new Scanner(System.in);
    Map<String, AddressBook> addressBooks = new HashMap<>();

    public static void main(String[] args) throws IOException {
        AddressBookMain addressBookMain = new AddressBookMain();

        boolean quit = false;
        while (!quit) {
            System.out.println("Enter your choice:");
            System.out.println("1. Create new Address Book");
            System.out.println("2. Add new Contact to Address Book");
            System.out.println("3. Edit Contact to Address Book");
            System.out.println("4. Delete a Contact from Address Book");
            System.out.println("5. Add multiple Contacts to Address Book");
            System.out.println("6. Search Contacts by City or State");
            System.out.println("7. View Contacts by City or State");
            System.out.println("8. Get count of Contacts by City or State");
            System.out.println("9. Sort the Address book BY Person Name");
            System.out.println("10. Get all contacts by zipcode");
            System.out.println("11. Get all contacts ");
            System.out.println("12. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addressBookMain.createNewAddressBook();
                    break;
                case 2:
                    addressBookMain.addContact();
                    break;
                case 3:
                    addressBookMain.deleteContact();
                    break;
                case 4:
                    addressBookMain.editContact();
                    break;
                case 5:
                    addressBookMain.addMultipleContacts();
                    break;
                case 6:
                    addressBookMain.searchContacts();
                    break;
                case 7:
                    addressBookMain.viewContacts();
                    break;
                case 8:
                    addressBookMain.getContacts();
                    break;
                case 9:
                    addressBookMain.sortContactsByName();
                    break;
                case 10:
                    //addressBookMain.getContactsByCity();
                    addressBookMain.readAndWriteContactFromCsv();
                    break;
                case 11:
                    addressBookMain.readAndWriteContact();
                    break;
                case 12:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createNewAddressBook() {
        System.out.println("Enter name of the new Address Book:");
        String name = scanner.nextLine();
        if (addressBooks.containsKey(name)) {
            System.out.println("An Address Book with this name already exists.");
        } else {
            AddressBook addressBook = new AddressBook(name);
            addressBooks.put(name, addressBook);
            System.out.println("Address Book created with name: " + name);
        }
    }


    private void editContact() {
        System.out.println("Enter the name of the Address Book to add the Contact to:");
        String name = scanner.nextLine();
        AddressBook addressBook = addressBooks.get(name);
        if (addressBook == null) {
            System.out.println("No Address Book found with name: " + name);
            return;
        }
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter address:");
        String address = scanner.nextLine();
        System.out.println("Enter city:");
        String city = scanner.nextLine();
        System.out.println("Enter state:");
        String state = scanner.nextLine();
        System.out.println("Enter zip code:");
        String zip = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        Contact updatedcontact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        addressBook.updateContact(firstName, lastName, updatedcontact);
        System.out.println("Contact added successfully to Address Book: " + name);
    }

    private void addContact() {
        System.out.println("Enter the name of the Address Book to add the Contact to:");
        String name = scanner.nextLine();
        AddressBook addressBook = addressBooks.get(name);
        if (addressBook == null) {
            System.out.println("No Address Book found with name: " + name);
            return;
        }
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter address:");
        String address = scanner.nextLine();
        System.out.println("Enter city:");
        String city = scanner.nextLine();
        System.out.println("Enter state:");
        String state = scanner.nextLine();
        System.out.println("Enter zip code:");
        String zip = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        addressBook.addContact(contact);
        System.out.println("Contact added successfully to Address Book: " + name);
    }

    private void deleteContact() {
        System.out.println("Enter the name of the Address Book to delete a Contact from:");
        String name = scanner.nextLine();
        AddressBook addressBook = addressBooks.get(name);
        if (addressBook == null) {
            System.out.println("No Address Book found with name: " + name);
            return;
        }
        System.out.println("Enter the first name of the Contact to delete:");
        String firstName = scanner.nextLine();
        System.out.println("Enter the last name of the Contact to delete:");
        String lastName = scanner.nextLine();
        boolean isDeleted = addressBook.deleteContact(firstName, lastName);
        if (isDeleted) {
            System.out.println("Contact " + firstName + " " + lastName + " deleted successfully from Address Book: " + name);
        } else {
            System.out.println("Contact " + firstName + " " + lastName + " not found in Address Book: " + name);
        }
    }


    private void addMultipleContacts() {
        System.out.println("Enter the name of the Address Book to add the Contacts to:");
        String name = scanner.nextLine();
        AddressBook addressBook = addressBooks.get(name);
        if (addressBook == null) {
            System.out.println("No Address Book found with name: " + name);
            return;
        }
        System.out.println("Enter number of contacts to add:");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        for (int i = 1; i <= count; i++) {
            System.out.println("Enter details for Contact " + i + ":");
            System.out.println("Enter first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name:");
            String lastName = scanner.nextLine();
            System.out.println("Enter address:");
            String address = scanner.nextLine();
            System.out.println("Enter city:");
            String city = scanner.nextLine();
            System.out.println("Enter state:");
            String state = scanner.nextLine();
            System.out.println("Enter zip code:");
            String zip = scanner.nextLine();
            System.out.println("Enter phone number:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
            addressBook.addContact(contact);
        }
        System.out.println("Contacts added successfully to Address Book: " + name);
    }

    private void searchContacts() {
        System.out.println("Enter the city or state to search for Contacts:");
        String location = scanner.nextLine();
        System.out.println("Search results:");
        addressBooks.values().stream()
                .map(addressBook -> addressBook.searchByCity(location).stream())
                .forEach(System.out::println);
    }

    private void viewContacts() {
        System.out.println("Enter the city or state to view Contacts:");
        String location = scanner.nextLine();
        System.out.println("Contacts in " + location + ":");
        addressBooks.values().stream()
                .map(addressBook -> addressBook.searchByState(location).stream())
                .forEach(System.out::println);
    }

    private void getCount() {
        System.out.println("Enter the city or state to get count of Contacts:");
        String location = scanner.nextLine();
        int count = addressBooks.values().stream()
                .flatMapToInt(addressBook -> IntStream.of(addressBook.getCountByCity(location)))
                .sum();
        System.out.println("Number of Contacts in " + location + ": " + count);
    }


    private void viewMobile() {
        System.out.println("Enter the city or state to view Contacts:");
        String name = scanner.nextLine();
        System.out.println("Contacts in " + name + ":");
        addressBooks.values().stream().map(addressBook -> addressBook.searchByPhoneNumber(name)).forEach(System.out::println);
    }

    private void sortContactsByName() {
        addressBooks.values()
                .stream()
                .flatMap(ab -> ab.getcontactsListList().stream())
                .sorted(Comparator.comparing(Contact::getFirstName)).collect(Collectors.toList())
                .forEach(System.out::println);


    }

    private void getContacts() {
        addressBooks.values().stream().collect(Collectors.toList()).forEach(System.out::println);
    }

    private void getContactsByCity() {
        addressBooks.values()
                .stream()
                .flatMap(ab -> ab.getcontactsListList().stream())
                .sorted(Comparator.comparing(Contact::getCity))
                .forEach(System.out::println);
    }

    public void sortEntries() {
        System.out.println("Enter the city or state or zip");
        String sortBy = scanner.nextLine();
        switch (sortBy.toLowerCase()) {
            case "city":
                addressBooks.values()
                        .stream()
                        .flatMap(ab -> ab.getcontactsListList().stream())
                        .sorted(Comparator.comparing(Contact::getCity))
                        .forEach(System.out::println);
                break;
            case "state":
                addressBooks.values()
                        .stream()
                        .flatMap(ab -> ab.getcontactsListList().stream())
                        .sorted(Comparator.comparing(Contact::getState))
                        .forEach(System.out::println);
                break;
            case "zip":
                addressBooks.values()
                        .stream()
                        .flatMap(ab -> ab.getcontactsListList().stream())
                        .sorted(Comparator.comparing(Contact::getZip))
                        .forEach(System.out::println);
                break;
            default:
                System.out.println("Invalid sort field.");
                return;
        }
    }

    public void readAndWriteContact() throws IOException {
       // Path file = Path.of(filepath);
       // StringBuffer sb = new StringBuffer();
        // PersonContacts personContacts = new FileIOPersonContacts();
      //  BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(new File("contact.txt")));
    FileWriter fileWriter = new FileWriter(filepath);
        addressBooks.values().stream().forEach(addressBook -> {
            addressBook.contactsList.forEach(contact -> {
                contact.toString().concat("\n");
                try {
                    fileWriter.write(String.valueOf(contact));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            // bufferedWriter.write(contact)
            // Files.write(file,sb.toString().getBytes());
        });

            //       addressBook.writeData(personContacts);


        //  final String filepath ="C:\\Users\\adesh\\IdeaProjects\\addressbook\\src\\com\\Bridgelabz\\contact.txt";
//
//        try {
//            Files.lines(Path.of(filepath)).forEach(contactString -> System.out.println(contactString));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }



    public  void readAndWriteContactFromCsv() throws IOException {
        PersonContacts personContact = new CsvFileReadAndWrite();
        addressBooks.values().stream().forEach(addressBook -> {
            try {
                addressBook.writeDataForCSV(personContact);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Files.lines(Path.of(fileCSV)).forEach(contactString -> System.out.println(contactString));
    }
//    public static void readAndWriteContactFromJson() throws IOException {
//        Gson gson= new Gson(contactsList);
//        Filewriter writer = new FileWriter(fileJson);
//        writer.write(json);
//        writer.close();

    }
//}









