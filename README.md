# 📚 Library Management System  
![Library Management System](https://img.shields.io/badge/Version-1.0-blue) ![Spring Boot](https://img.shields.io/badge/SpringBoot-3.0-green) ![Java](https://img.shields.io/badge/Java-17-orange) ![MySQL](https://img.shields.io/badge/MySQL-8.0-blue) ![Hibernate](https://img.shields.io/badge/Hibernate-6.5-yellow) ![Maven](https://img.shields.io/badge/Maven-3.9-red) ![JWT](https://img.shields.io/badge/JWT-Auth-purple) ![REST API](https://img.shields.io/badge/REST-API-lightgrey)  

## 🚀 Overview  
The **Library Management System** is a **RESTful API-based library management application** built using **Spring Boot**. It allows users to **manage books, register users, and borrow/return books** while enforcing authentication and access control.  

## ✨ Features
- 🔐 **User Registration & Authentication** - Secure login and role-based access control.
- 📚 **Book Management** - Admins and librarians can add, update, and delete books.
- 📖 **Book Borrowing & Returning** - Members can borrow and return books.
- 📋 **Book Listings** - Publicly accessible endpoints for viewing book details.
- 🛡️ **Security** - JWT-based authentication and role-based authorization.
- 🌐 **Thymeleaf UI** - Simple web interface for viewing books.

---

## 📱 API Endpoints  
🔹 **Authentication**  
- `POST /register/` → Register a new user (Admin, Librarian, Member).  
- `POST /login/` → User login (JWT authentication).  

🔹 **Book Management (Admin/Librarian Only)**  
- `POST /books/` → Add a new book.  
- `PUT /books/{id}/` → Update book details.  
- `DELETE /books/{id}/` → Delete a book.  

🔹 **Book Borrowing (Members Only)**  
- `POST /borrow/{book_id}/` → Borrow a book (if available).  
- `POST /return/{book_id}/` → Return a borrowed book.  

🔹 **Public Access**  
- `GET /books/` → List all books.  
- `GET /books/{id}/` → Retrieve book details.  

---

## 🛠 Installation & Setup  

### 1️⃣ **Clone the Repository**  
```sh
 git clone https://github.com/your-username/LibraryManagementSystem.git
 cd LibraryManagementSystem
```

### 2️⃣ **Set Up the Database**  
Create a **MySQL database** named `library_db` and update `application.properties`:  
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ **Build & Run the Application**  
```sh
mvn clean install
mvn spring-boot:run
```
The server will start at **http://localhost:8080**  

---

## 🏠 Technologies Used  
- **Spring Boot 3.0** (Backend Framework)  
- **Spring Security & JWT** (Authentication & Authorization)  
- **Hibernate & JPA** (Database ORM)  
- **MySQL** (Relational Database)  
- **Thymeleaf** (UI Template Engine)  
- **Maven** (Build Automation)  

---

## 🔒 Security Measures  
✔ **JWT-based authentication** to secure API endpoints  
✔ **Role-based access control (RBAC)**  
✔ **Input validation & exception handling**  
✔ **Spring Security for request filtering**  

---

## 🚀 Future Enhancements  
🔹 Implement **Email Notifications** for due dates.  
🔹 Add **Fine Calculation System** for overdue books.  
🔹 Enhance UI with React.  
🔹 Implement **Book Reservation System** for members.  

---

## 🤝 Contributing  
Want to improve this project? Feel free to fork, create a pull request, or raise an issue!  

🔗 **GitHub Repository**: [LibraryManagementSystem](https://github.com/solocoder9/Library-Management-System)  

💎 **Contact**: [Solo Coder](mailto:solocoder9@gmail.com)  

---

🎯 **If you like this project, don't forget to ⭐ star the repository!** 🚀

