# Hotel_Reservation_System

Command to pull files from GitHub:
	git fetch --all
	git reset --hard origin/master

This program will create a hotel reservation system using Java.  Different attributes of a room will be remembered (smoking/non-smoking, what floor, how many beds, cost, etc) and considered when a customer is selecting a room.  Also the system will remember past customers and recommend rooms that are similar to the last room they rented.

Employees of the hotel will have to access the reservation system by logging in using a username and password.  An adminnistrator is able to add/remove employees and other administrators.  There will also be a master account for the manager/boss of the hotel that will have full access to the system, and administrators nor employees will be able to alter the account.  A new employee will need to be added to the system by an administrator or manager by logging into the system with their own credentials and adding the employee.  Each account will be able to change their password, but the user name will remain the same.  Each password must be at least 6 characters and include at least 1 number.  The usernames and corrosponding passwords are then encrypted and saved to an external file for storage.  When a login attempt is made, the reservation system will fetch the external file, decrypt it, and validate the credentials being used.

The key used to encrypt and decrypt passwords will be saved on a separate file.  For the purpose of this project the file will be available on GitHub, but in a more real scenario, the file will be stored in a local computer that will use the reservation system to better protect the passwords.

Whenever a reservation is added, edited, or removed, a log will be created to show which employee is making the change.  Employees will also be able to search for current reserved rooms by the customer's name or room number.