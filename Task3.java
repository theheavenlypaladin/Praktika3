package PraktikalWork17;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Task3 {
    static class StudentNode {
        int id;
        String fullName;
        int age;
        StudentNode next;
        void read(Scanner scanner) {
            id = readInt(scanner, "Номер студента (ID): ", 1);
            fullName = readText(scanner, "ФИО: ");
            age = readInt(scanner, "Возраст: ", 1);
        }
        void print() {
            System.out.println(
                    "ID: " + id
                            + ", ФИО: " + fullName
                            + ", возраст: " + age
            );
        }
    }
    static class StudentList {
        private StudentNode head;
        private StudentNode tail;
        StudentList() {
            head = null;
            tail = null;
        }
        boolean isEmpty() {
            return head == null;
        }
        boolean add(StudentNode student) {
            if (find(student.id) != null) {
                return false;
            }
            if (isEmpty()) {
                head = student;
                tail = student;
            } else {
                tail.next = student;
                tail = student;
            }
            tail.next = head;
            return true;
        }
        boolean remove(int id) {
            if (isEmpty()) {
                return false;
            }
            StudentNode current = head;
            StudentNode previous = tail;
            do {
                if (current.id == id) {
                    if (head == tail) {
                        head = null;
                        tail = null;
                    } else {
                        previous.next = current.next;
                        if (current == head) {
                            head = current.next;
                        }
                        if (current == tail) {
                            tail = previous;
                        }
                        tail.next = head;
                    }
                    current.next = null;
                    return true;
                }
                previous = current;
                current = current.next;
            } while (current != head);
            return false;
        }
        StudentNode find(int id) {
            if (isEmpty()) {
                return null;
            }
            StudentNode current = head;
            do {
                if (current.id == id) {
                    return current;
                }
                current = current.next;
            } while (current != head);
            return null;
        }
        boolean printStudent(int id) {
            StudentNode student = find(id);
            if (student == null) {
                return false;
            }
            student.print();
            return true;
        }
        void printAll() {
            if (isEmpty()) {
                System.out.println("Список пуст.");
                return;
            }
            StudentNode current = head;
            do {
                current.print();
                current = current.next;

            } while (current != head);
        }
        void clear() {
            head = null;
            tail = null;
        }
    }
    private static int readInt(
            Scanner scanner, String prompt, int minimum) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= minimum) {
                    return value;
                }
            } catch (NumberFormatException e) {}
            System.out.println(
                    "Введите целое число не меньше " + minimum + "."
            );
        }
    }
    private static String readText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("Строка не должна быть пустой.");
        }
    }
    private static void printMenu() {
        System.out.println("\nОдносвязный кольцевой список");
        System.out.println("1 — Добавить студента");
        System.out.println("2 — Удалить студента по ID");
        System.out.println("3 — Найти и вывести студента по ID");
        System.out.println("4 — Вывести весь список");
        System.out.println("5 — Очистить список");
        System.out.println("6 — Проверить, пуст ли список");
        System.out.println("7 — Создать новый пустой список");
        System.out.println("0 — Выход");
    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StudentList students = new StudentList();
            while (true) {
                printMenu();
                int command = readInt(scanner, "Выберите пункт: ", 0);
                switch (command) {
                    case 1:
                        StudentNode student = new StudentNode();
                        student.read(scanner);

                        if (students.add(student)) {
                            System.out.println("Студент добавлен.");
                        } else {
                            System.out.println(
                                    "Студент с таким ID уже существует."
                            );
                        }
                        break;
                    case 2:
                        int removeId = readInt(
                                scanner, "ID для удаления: ", 1
                        );
                        if (students.remove(removeId)) {
                            System.out.println("Студент удалён.");
                        } else {
                            System.out.println("Студент не найден.");
                        }
                        break;
                    case 3:
                        int searchId = readInt(
                                scanner, "ID для поиска: ", 1
                        );

                        if (!students.printStudent(searchId)) {
                            System.out.println("Студент не найден.");
                        }
                        break;
                    case 4:
                        students.printAll();
                        break;
                    case 5:
                        students.clear();
                        System.out.println("Список очищен.");
                        break;
                    case 6:
                        System.out.println(
                                students.isEmpty()
                                        ? "Список пуст."
                                        : "Список не пуст."
                        );
                        break;
                    case 7:
                        students = new StudentList();
                        System.out.println("Создан новый пустой список.");
                        break;
                    case 0:
                        System.out.println("Работа завершена.");
                        return;
                    default:
                        System.out.println("Такого пункта меню нет.");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nВвод завершён.");
        }
    }
}