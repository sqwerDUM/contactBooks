import java.util.Scanner;

public class ContactBook {
    private static ContactManager contactManager = new ContactManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить контакт");
            System.out.println("2 - Просмотреть контакт");
            System.out.println("3 - Обновить контакт");
            System.out.println("4 - Удалить контакт");
            System.out.println("5 - Показать все контакты");
            System.out.println("0 - Выход");

            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    contactManager.listAllContacts();
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void addContact() {
        System.out.println("Добавление нового контакта:");
        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию: ");
        String lastName = scanner.nextLine();
        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        Contact contact = new Contact(firstName, lastName, phoneNumber, email);
        contactManager.addContact(contact);
    }

    private static void viewContact() {
        System.out.print("Введите имя или фамилию контакта для просмотра: ");
        String name = scanner.nextLine();
        contactManager.viewContact(name);
    }

    private static void updateContact() {
        System.out.print("Введите имя или фамилию контакта для обновления: ");
        String name = scanner.nextLine();

        System.out.println("Введите новые данные контакта:");
        System.out.print("Новое имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Новая фамилия: ");
        String lastName = scanner.nextLine();
        System.out.print("Новый номер телефона: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Новый email: ");
        String email = scanner.nextLine();

        Contact updatedContact = new Contact(firstName, lastName, phoneNumber, email);
        contactManager.updateContact(name, updatedContact);
    }

    private static void deleteContact() {
        System.out.print("Введите имя или фамилию контакта для удаления: ");
        String name = scanner.nextLine();
        contactManager.deleteContact(name);
    }
}