# Hotel_Reservation_System

Command to pull files from GitHub:
	git fetch --all
	git reset --hard origin/master

This program will create a hotel reservation system using Java.  Different attributes of a room will be remembered (smoking/non-smoking, floor number, how many beds, cost, etc) and considered when a customer is selecting a room.  Also the system will remember past customers and recommend rooms that are similar to the last room they rented.

Employees of the hotel will have to access the reservation system by logging in using a username and password.  An adminnistrator is able to add/remove employees and other administrators.  A new employee will need to be added to the system by an administrator or manager by logging into the system with their own credentials and adding the employee.  Each account will be able to change their password, but the user name will remain the same.  Each password must be at least 6 characters and include at least 1 number.  The usernames and corrosponding passwords are then hashed with a unique salt sequence and saved to a separate file for storage.  When a login attempt is made, the reservation system will compare the inputted hashed password with the stored hashed password.  If it is a match, the login is successful.  Even though a simple encryption/decryption should be fine, since it will be a closed system, the added security is for insurance if the computer/database is accessed from an outside source.

Whenever a reservation is added, edited, or removed, a log will be created to show which employee is making the change.  Employees will also be able to search for current reserved rooms by the customer's name or room number.