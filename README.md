Quiz Service API
  This project implements a Quiz Service API using Spring Boot to manage questions, quizzes, and user authentication.

Description
  The Quiz Service API provides endpoints for managing questions, quizzes, and user authentication. It allows users to perform operations such as adding, updating, and deleting questions, signing up as students or teachers, logging in and out, retrieving user information, and fetching previous quiz marks.

Technologies Used
  Java
  Spring Boot
  Spring MVC
  Spring Data
  RESTful API principles
  
API Endpoints
 
  Question Management
    Add Question
    Endpoint: POST /question
    Description: Adds a new question to the system.
    Request Body: JSON object of type Question
    Access: Only accessible to authenticated users with Teacher role.
  
  Update Question
    Endpoint: PUT /question
    Description: Updates an existing question.
    Request Body: JSON object of type Question
    Access: Only accessible to authenticated users with Teacher role.

  Delete Question
    Endpoint: DELETE /question
    Description: Deletes a question by its ID.
    Request Parameter: n (ID of the question to delete)
    Access: Only accessible to authenticated users with Teacher role.

  Find Questions
    Endpoint: GET /question
    Description: Retrieves a specified number of questions, optionally filtered by difficulty.
    Request Parameters:
      n (number of questions to retrieve)
      difficulty (optional parameter to filter questions by difficulty)
    Access: Accessible to all users.

User Management and Authentication
  Student Sign Up
    Endpoint: POST /signup/student
    Description: Registers a new student user.
    Request Parameter: username (username of the new user)
    Access: Accessible to all users.
  
  Teacher Sign Up
    Endpoint: POST /signup/teacher
    Description: Registers a new teacher user.
    Request Parameter: username (username of the new user)
    Access: Accessible to all users.
  User Login
    Endpoint: POST /login
    Description: Authenticates a user login.
    Request Parameter: username (username of the user)
    Access: Accessible to all users.

  Get All Users
    Endpoint: GET /users
    Description: Retrieves all users in the database.
    Access: Only accessible to authenticated users with Teacher role.

  Get User Marks
    Endpoint: GET /marks
    Description: Retrieves previous marks of the current logged-in user.
    Access: Accessible to all users.

  User Logout
    Endpoint: GET /logout
    Description: Logs out the current user session.
    Access: Accessible to all users.

Implementation Details

Controllers:
  QuestionController: Manages CRUD operations for questions and quiz submissions.
  LoginController: Handles user authentication, user management (sign up, login, logout), and retrieval of user-related information.

Services:
  QuestionService: Provides business logic for managing questions and quiz submissions.
  LoginService: Handles user authentication, user management, and session management.
