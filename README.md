# Electronic-Grade-Book
 Created an application that simulates an electronic GradeBook in which each type of user (student, teacher, assistant, parent) can see different information such as:
### Student
- The student can see each course he is enrolled in and additional information about a certain one can be displayed
course if it is selected
### Teacher and Assistant
- Everyone can see which courses they are teaching and can validate the students' grades
### Parent
- Allows a student's parents to subscribe to the GradeBook to be able to receive notifications when the child is graded by a teacher or by a assistant.

## Implementation
 Used different design patterns such as:
- Singleton (for class Catalog in order to remain unique)
- Factory (for class User to create each user(Student,Teacher,Assistant,Parent)
- Builde (for class Course to create each type of course (partial or full)
- Observer (for class Parent to get notified when a grade is published)
- Strategy (for class Course in order for teaches to get the best student according to a certain criterion)
- Visitor (for class Teacher and Assistent to be able to complete the students' grades in the GradeBook, along the way)
- Memento (for class Course to offer the possibility to make a back-up of the grades)

## GUI and How to Use
 The input data of the Catalog is read by parsing a JSON file using JSON Simple Java Toolkit

 ### Login page
- insert the first name to user and last name to password and press Sign in to display information about the user
- if the User is valid a new page with the info will pop, else a message will be displayed
- can enter the course name and view a more complex detail of the course

  ![Screenshot 2023-07-01 211505](https://github.com/StefanIv21/Electronic-Grade-Book/assets/94042909/31e40ce4-a174-4a21-b528-84eaf016c450)


### Teacher and Assistant page
- the teacher's courses are displayed and the grade to validate
- pressing the button "validare" the grades will validate to the GradeBook and will appear for Students and Parents
- if the grades have been validated and the user returns to the account,nothing will appear in the grades section  and he will no longer be able to press the validation button

## Student page
- upon selecting one of the courses in which the student is signed up, details related to the students's situation in that particular course are provided

## Parent page
- added a "check" button in which will display the received notifications (each parent sees the child's school situation)

## CourseInfo page
- The page displays in detail all the data about a course, the list of students, assistants, grades, as well as the best student of at the course
- Can add a new student to an existing group
= Can add an assistant to a new group
- added a button where can back up the notes and a button that undo the notes

  
