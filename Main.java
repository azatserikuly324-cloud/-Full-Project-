import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrowBook() {
        available = false;
    }

    public void returnBook() {
        available = true;
    }

    public void showInfo() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available: " + available);
        System.out.println("----------------------");
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }

        for (Book book : books) {
            book.showInfo();
        }
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(String title) {
        Book book = searchBookByTitle(title);

        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isAvailable()) {
            System.out.println("This book is already borrowed.");
        } else {
            book.borrowBook();
            System.out.println("Book borrowed successfully!");
        }
    }

    public void returnBook(String title) {
        Book book = searchBookByTitle(title);

        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isAvailable()) {
            System.out.println("This book was not borrowed.");
        } else {
            book.returnBook();
            System.out.println("Book returned successfully!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        library.addBook(new Book(1, "Java Basics", "John Smith"));
        library.addBook(new Book(2, "Clean Code", "Robert Martin"));
        library.addBook(new Book(3, "OOP Principles", "Alan Kay"));

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Show all books");
            System.out.println("2. Add book");
            System.out.println("3. Search book");
            System.out.println("4. Borrow book");
            System.out.println("5. Return book");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                library.showAllBooks();

            } else if (choice == 2) {
                System.out.print("Enter book ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter book title: ");
                String title = scanner.nextLine();

                System.out.print("Enter author: ");
                String author = scanner.nextLine();

                library.addBook(new Book(id, title, author));

            } else if (choice == 3) {
                System.out.print("Enter title: ");
                String title = scanner.nextLine();

                Book book = library.searchBookByTitle(title);

                if (book != null) {
                    book.showInfo();
                } else {
                    System.out.println("Book not found.");
                }

            } else if (choice == 4) {
                System.out.print("Enter title to borrow: ");
                String title = scanner.nextLine();
                library.borrowBook(title);

            } else if (choice == 5) {
                System.out.print("Enter title to return: ");
                String title = scanner.nextLine();
                library.returnBook(title);

            } else if (choice == 0) {
                System.out.println("Program finished.");
                break;

            } else {
                System.out.println("Wrong option!");
            }
        }
    }
}
