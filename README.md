## Overall

This is a simple online bookstore inventory system. The inventory consists of books, and support:

1. Add a book to the inventory.

2. Remove a book from the inventory.

3. Update the quantity in stock for a given book.

4. Retrieve the quantity in stock for a given book.

5. List all books in the inventory.

6. Some basic book search based on author, title, ISBN, price range and quantlity range.


## Set Up

1. In this project, we use MySQL server (5.7) for storing the book information and authentication. So please initiate related schema and tables first:

   (1) Create a new MySQL instance with username: root and password: 123456
   (2) Create a new Schema with name as "inventory"
   (3) Run the scripts book.sql and user.sql (should be in folder: 'sql') to create required tables

2. Run the Spring Boot service in local IDE with default settings.
