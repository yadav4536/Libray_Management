import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Book {
    private String title;
    private String author;
    private boolean checkedOut;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.checkedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}

class Library {
    private ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    // Other methods remain unchanged
}

public class LibraryManagementSystem {
    private JFrame frame;
    private Library library;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new LibraryManagementSystem().initialize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void initialize() {
        library = new Library();
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnAddBook = new JButton("Add New Book");
        JButton btnDisplayBooks = new JButton("Display Books");
        JButton btnCheckOutBook = new JButton("Check Out Book");
        JButton btnCheckInBook = new JButton("Check In Book");
        JButton btnSearchBooks = new JButton("Search Books");

        JPanel panel = new JPanel();
        panel.add(btnAddBook);
        panel.add(btnDisplayBooks);
        panel.add(btnCheckOutBook);
        panel.add(btnCheckInBook);
        panel.add(btnSearchBooks);

        btnAddBook.addActionListener(e -> addBook());
        btnDisplayBooks.addActionListener(e -> displayBooks());
        btnCheckOutBook.addActionListener(e -> checkOutBook());
        btnCheckInBook.addActionListener(e -> checkInBook());
        btnSearchBooks.addActionListener(e -> searchBooks());

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog(frame, "Enter book title:");
        String author = JOptionPane.showInputDialog(frame, "Enter author name:");

        if (title != null && author != null) {
            library.addBook(new Book(title, author));
            JOptionPane.showMessageDialog(frame, "Book added successfully.");
        }
    }

    private void displayBooks() {
        ArrayList<Book> books = library.getBooks();
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No books in the library.");
        } else {
            StringBuilder message = new StringBuilder("Books in the library:\n");
            for (Book book : books) {
                message.append("Title: ").append(book.getTitle()).append(", Author: ").append(book.getAuthor())
                        .append(", Status: ").append(book.isCheckedOut() ? "Checked Out" : "Available").append("\n");
            }
            JOptionPane.showMessageDialog(frame, message.toString());
        }
    }

    private void checkOutBook() {
        String title = JOptionPane.showInputDialog(frame, "Enter the title of the book to check out:");
        if (title != null) {
            for (Book book : library.getBooks()) {
                if (book.getTitle().equalsIgnoreCase(title) && !book.isCheckedOut()) {
                    book.setCheckedOut(true);
                    JOptionPane.showMessageDialog(frame, "Book checked out successfully.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not available for checkout or already checked out.");
        }
    }

    private void checkInBook() {
        String title = JOptionPane.showInputDialog(frame, "Enter the title of the book to check in:");
        if (title != null) {
            for (Book book : library.getBooks()) {
                if (book.getTitle().equalsIgnoreCase(title) && book.isCheckedOut()) {
                    book.setCheckedOut(false);
                    JOptionPane.showMessageDialog(frame, "Book checked in successfully.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not available for check-in or already checked in.");
        }
    }

    private void searchBooks() {
        String keyword = JOptionPane.showInputDialog(frame, "Enter search keyword:");
        if (keyword != null) {
            ArrayList<Book> searchResults = new ArrayList<>();
            for (Book book : library.getBooks()) {
                if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                    searchResults.add(book);
                }
            }
            if (searchResults.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No matching books found.");
            } else {
                StringBuilder message = new StringBuilder("Search results:\n");
                for (Book book : searchResults) {
                    message.append("Title: ").append(book.getTitle()).append(", Author: ").append(book.getAuthor()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, message.toString());
            }
        }
    }
}
