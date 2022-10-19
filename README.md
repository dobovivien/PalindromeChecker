Palindrome checker

The application is written in Java 11, using Spring Boot as a foundation.

It has two main functions the User can do:
- add a word to the database
- list all the previously given words.

The Entity used by the database is the WordData Object type which has 4 fields: 
- id
- content
- timestamp
- longest_palindrome_size.

The methods work with DTOs (InputDto and WordDataDto) which have almost the same structure as the Entity, without unnecessary fields. 

The addWord method validates the content to be at least 1 character long and the timestamp to be in the correct date format. If the given input is valid it saves the data to the database and checks if the word contains any palindromes.
The shortest palindrome contains 2 characters. If the user does not provide the timestamp the program saves the current date and time in the right format.

The getAll method lists all the saved words ordered by their palindrome number, from the highest to the lowest. 

The service is covered by Unit Tests.