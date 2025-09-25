package HARSH;

import java.util.ArrayList;
import java.util.*;

// Book Class
class Book 
{
    private int id;
    private String title;
    private String author;
    private boolean isIssued;
    private User issuedTo;  // track which user issued the book

    public Book(int id, String title, String author) 
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = null;
    }

    public int getId() 
    {
    	return id;
    }
    public String getTitle() 
    { 
    	return title; 
    }
    public String getAuthor() 
    { 
    	return author; 
    }
    public boolean isIssued()
    { 
    	return isIssued; 
    }
    public User getIssuedTo() 
    { 
    	return issuedTo; 
    }

    public void issueBook(User user)
    {
        isIssued = true;
        issuedTo = user;
    }

    public void returnBook() 
    {
        isIssued = false;
        issuedTo = null;
    }

    @Override
    public String toString() 
    {
        if (isIssued) 
        {
            return id + " - " + title + " by " + author + " (Issued to " + issuedTo.getName() + ")";
        } else {
            return id + " - " + title + " by " + author + " (Available)";
        }
    }
}

// User Class
class User 
{
    private int userId;
    private String name;

    public User(int userId, String name) 
    {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() 
    { 
    	return userId; 
    }
    public String getName() 
    { 
    	return name;
    }

    @Override
    public String toString() 
    {
        return userId + " - " + name;
    }
}   


// Library Class
class Library 
{
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public void addBook(Book book)
    {
        books.add(book);
    }

    public void addUser(User user) 
    {
        users.add(user);
    }

    public void showBooks() 
    {
        if (books.isEmpty())
        {
            System.out.println("No books available!");
            return;
        }
        for (Book b : books)
        {
            System.out.println(b);
        }
    }

    public void showUsers() 
    {
        if (users.isEmpty()) 
        {
            System.out.println("No users registered!");
            return;
        }
        for (User u : users) 
        {
            System.out.println(u);
        }
    }
//Issue Book
    public void issueBook(int bookId, int userId)
    {
        for (Book b : books) 
        {
            if (b.getId() == bookId) 
            {
                if (!b.isIssued()) 
                {
                    for (User u : users) 
                    {
                        if (u.getUserId() == userId) 
                        {
                            b.issueBook(u);
                            System.out.println("Book '" + b.getTitle() + "' issued to " + u.getName());
                            return;
                        }
                    }
                    System.out.println("User not found!");
                } else {
                    System.out.println("Book is already issued to " + b.getIssuedTo().getName());
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }
//Return Book
    public void returnBook(int bookId, int userId) 
    {
        for (Book b : books) 
        {
            if (b.getId() == bookId) 
            {
                if (b.isIssued() && b.getIssuedTo().getUserId() == userId) 
                {
                    b.returnBook();
                    System.out.println("Book '" + b.getTitle() + "' returned by User " + userId);
                } else {
                    System.out.println("This book was not issued to this user!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }
    
  //Delete Book
    public void deleteBook(int bookId) 
    {
     for (Book b : books) 
     {
         if (b.getId() == bookId) 
         {
             books.remove(b);
             System.out.println("Book with ID " + bookId + " deleted successfully!");
             return;
         }
     }
     System.out.println("Book not found!");
    }

    //Delete User
    public void deleteUser(int userId) 
    {
     for (User u : users) 
     {
         if (u.getUserId() == userId) 
         {
             users.remove(u);
             System.out.println("User with ID " + userId + " deleted successfully!");
             return;
         }
     }
     System.out.println("User not found!");
    }
}

// Main Class
public class LibraryManagementSystem 
{
    public static void main(String[] args) 
    {
        Scanner s=new Scanner(System.in);
        Library library = new Library();

        // Sample Data
        library.addBook(new Book(1, "Java Basics", "James Gosling"));
        library.addBook(new Book(2, "C Programming", "Dennis Ritchie"));
        library.addBook(new Book(3, "Python Crash Course", "Eric Matthes"));

        library.addUser(new User(101, "Harshu"));
        library.addUser(new User(102, "Ravi"));

        while (true) 
        {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Show Books");
            System.out.println("2. Show Users");
            System.out.println("3. Add Book");
            System.out.println("4. Add User");
            System.out.println("5. Delete Book");
            System.out.println("6. Delete User");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = s.nextInt();
            s.nextLine(); // buffer clear

            switch (choice) 
            {
                case 1:
                	
                    library.showBooks();
                    break;
                case 2:
                	
                    library.showUsers();
                    break;
                    
                case 3: // Add Book
                    System.out.print("Enter Book ID: ");
                    int bookId = s.nextInt();
                    s.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = s.nextLine();
                    System.out.print("Enter Author: ");
                    String author = s.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    System.out.println("Book added successfully!");
                    break;
                    
                case 4: // Add User
                    System.out.print("Enter User ID: ");
                    int userIdNew = s.nextInt();
                    s.nextLine();
                    System.out.print("Enter User Name: ");
                    String userName = s.nextLine();
                    library.addUser(new User(userIdNew, userName));
                    System.out.println("User added successfully!");
                    break;
                    
                case 5: // Delete Book
                    System.out.print("Enter Book ID to delete: ");
                    int delBookId = s.nextInt();
                    library.deleteBook(delBookId);
                    break;
                    
                case 6: // Delete User
                    System.out.print("Enter User ID to delete: ");
                    int delUserId = s.nextInt();
                    library.deleteUser(delUserId);
                    break;
                    
                case 7: // Issue Book
                    System.out.print("Enter User ID: ");
                    int userId = s.nextInt();
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = s.nextInt();
                    library.issueBook(issueId, userId);
                    break;
                    
                case 8: // Return Book
                    System.out.print("Enter User ID: ");
                    int returnUserId = s.nextInt();
                    System.out.print("Enter Book ID to return: ");
                    int returnId = s.nextInt();
                    library.returnBook(returnId, returnUserId);
                    break;
                    
                case 9:
                    System.out.println("Exiting... Goodbye!");
                    s.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}