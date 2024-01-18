import java.io.*;
import java.util.StringJoiner;
public class ContactManager {
    private Contact[] contacts;
    private int count;
    private static final int MAX_CONTACTS = 100;
    private static final String FILE_NAME = "contacts.txt";

    public ContactManager() {
        contacts = new Contact[MAX_CONTACTS];
        count = 0;
        loadContacts();
    }

    public void addContact(Contact contact) {
        if (count < MAX_CONTACTS) {
            contacts[count] = contact;
            count++;
            saveContacts();
        } else {
            System.out.println("Телефонная книга полна. Не удается добавить новый контакт.");
        }
    }

    public void viewContact(String name) {
        for (int i = 0; i < count; i++) {
            if (contacts[i].getFirstName().equalsIgnoreCase(name) || contacts[i].getLastName().equalsIgnoreCase(name)) {
                printContact(contacts[i]);
                return;
            }
        }
        System.out.println("Контакт с именем " + name + " не найден.");
    }

    public void updateContact(String name, Contact updatedContact) {
        for (int i = 0; i < count; i++) {
            if (contacts[i].getFirstName().equalsIgnoreCase(name) || contacts[i].getLastName().equalsIgnoreCase(name)) {
                contacts[i] = updatedContact;
                saveContacts();
                System.out.println("Контакт обновлен.");
                return;
            }
        }
        System.out.println("Контакт с именем " + name + " не найден.");
    }

    public void deleteContact(String name) {
        for (int i = 0; i < count; i++) {
            if (contacts[i].getFirstName().equalsIgnoreCase(name) || contacts[i].getLastName().equalsIgnoreCase(name)) {
                System.arraycopy(contacts, i + 1, contacts, i, count - i - 1);
                contacts[--count] = null;
                saveContacts();
                System.out.println("Контакт удален.");
                return;
            }
        }
        System.out.println("Контакт с именем " + name + " не найден.");
    }

    public void listAllContacts() {
        if (count == 0) {
            System.out.println("Телефонная книга пуста.");
            return;
        }
        for (int i = 0; i < count; i++) {
            printContact(contacts[i]);
        }
    }

    private void saveContacts() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < count; i++) {
                Contact contact = contacts[i];
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(contact.getFirstName());
                joiner.add(contact.getLastName());
                joiner.add(contact.getPhoneNumber());
                joiner.add(contact.getEmail());
                out.println(joiner.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    Contact contact = new Contact(data[0], data[1], data[2], data[3]);
                    contacts[count++] = contact;
                }
            }
        } catch (IOException e) {
            System.out.println("Не удалось загрузить контакты. Создается новая телефонная книга.");
        }
    }

    private void printContact(Contact contact) {
        System.out.println("Имя: " + contact.getFirstName());
        System.out.println("Фамилия: " + contact.getLastName());
        System.out.println("Телефон: " + contact.getPhoneNumber());
        System.out.println("Email: " + contact.getEmail());
        System.out.println("----------------------------------");
    }
}