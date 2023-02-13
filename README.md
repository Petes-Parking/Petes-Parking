# Petes-Parking
CS307 Project

Pete’s Parking

Sprint 1 Planning Document

Team 34: Max Fuligni, Alhagie Jaiteh, Sam Zola, Alec Sabia, Naia Echevarria, Aakash Jariwala
GitHub: https://github.com/Petes-Parking

Sprint Overview
During this sprint, our main goal is to get our application up and running. We hope to create the framework of Pete’s Parking so that we can build upon it in future sprints. Since we currently have no code written for our project, we want to build a base for the project before adding any of the complex features. Some of the goals we hope to accomplish are to create a functional login system, have a viewable parking map, and a parking reservation system. To do this, we have chosen user stories that accomplish these tasks.

Scrum Master: Max Fuligni

Meeting Plan: Tuesdays/Thursdays @ 5:30pm

Risks and Challenges:
One of the main challenges we expect to encounter includes developing our frontend UI so that it matches what we designed for our complex UI mockups, as well as overcoming the initial learning curve with HTML and CSS. Because of this, our initial development speed might be slow, but this is anticipated. Another challenge is to have our application up and running after this sprint, which is a relatively large undertaking but still an important goal to reach. It is crucial for us to successfully implement the user stories in this sprint and have a functional application in order to stay on schedule and have successful future sprints.


Current Sprint Detail

User Story #1:

As a user, I would like to register an account on Pete's Parking portal.

#
Description
Estimated Time
Owner
1.
Program the backend to include the logic for student registration
2 hr
Alhagie Jaiteh
2.
Setup the database to enable user information storage
5 hr
Alhagie Jaiteh
3
Setup the backend to include the implementation  for user data authentication
3 hr
Alhagie Jaiteh
4
Bind the backend and the database to sync the client data
1 hr
Alhagie Jaiteh
5
Create a unit test case
10 hr
Alhagie Jaiteh


Acceptance Criteria
Given that the user story is implemented correctly, the user will be able to register an account to the Pete’s Parking Portal
Given that this user story is implemented correctly, the user will be input their information to meet registration requirements
Given that this user story is correctly, user information will be authenticated such that if they don't have an account, they can create an account
Given that this user story is implemented correctly, a record of user information will be stored in our database system. 







User Story #2:

As a user, after creating an account, I would like to login to Pete's Parking portal to access other functionalities.

#
Description
Estimated Time
Owner
1.
Program the backend to include the logic for student login
3 hr
Alhagie Jaiteh
2.
Setup the database to enable user information storage
5 hr
Alhagie Jaiteh
3
Include additional module  to ensure the user login information is authenticated
5 hr
Alhagie Jaiteh
4
Bind the backend and the database to sync the client data
1 hr
Alhagie Jaiteh
5
Create a unit test case
5 hr
Alhagie Jaiteh


Acceptance Criteria
Given that the user story is implemented correctly, the user will be able to login into their Pete's Parking Portal account.
Given that this user story is implemented correctly, the user will be input their information to meet login requirements
Given that this user story is correctly, user information will be authenticated such that if the username/email and password is incorrect, they can have another attempt to login
Given that this user story is implemented correctly, a record of user information will be stored in our database system. 







User Story #3:

I would like to view various parking areas available in Pete's Parking App and make a reservation for a parking slot. 
#
Description
Estimated Time
Owner
1.
Implement the logic for parking lot representation in the backend
3 hr
Alhagie Jaiteh
2.
Program the backend to include a student booking system.
5 hr
Alhagie Jaiteh
3
Include additional module  to ensure the user can select a parking lot the would like to reserve for parking
5 hr
Alhagie Jaiteh
4
Bind the backend and the database to sync the client data
1 hr
Alhagie Jaiteh
5
Setup the database to contain  parking lot information 
3 hr
Alhagie Jaiteh
6
Test and debug the backend
6hr
Alhagie Jaiteh


Acceptance Criteria
Given that the user story is implemented correctly, the user will be able to see all the parking lots available for reservation.
Given that this user story is implemented correctly, the user can select any one of the parking lots.
Given that this user story is correct, users will be able to view all the open slots and slots that are unavailable.
Given that this user story is implemented correctly, a record of user information will be stored in our database system. 



User Story #4:
I would like to register an account to use the application.

#
Description
Estimated Time
Owner
1.
Create the register module and associated view in the front-end
3hr
Max Fuligni
2.
Create the required fields for creating an account: email, password and date of birth.
2hr
Max Fuligni
3. 
Ask for the password field twice to confirm a password.
1 hr
Max Fuligni
4.
Check that the user is old enough to use our application (above 16). 
0.5hr
Max Fuligni
5. 
Send an email to the requested email to confirm the email. 
1hr
Max Fuligni


Acceptance Criteria:
Given that the view is working, the display should ask for the email (email format), password (string, hidden), and date of birth (date)
Display message if one of the fields is not in the correct form
Display message if the email is already registered
Display message if the password does not contain at least 8 digits and an uppercase character.
Given that the passwords do not match, the front-end should prompt the user to enter matching passwords
Given that the user is under 16, the front-end should kick the user out.
Given that the registration fields are valid, an email should be sent out to the user’s email with an authentication link that expires after fifteen minutes that creates the account and sends a request to update the associated databases. 


User Story #5:
I would like to enter my username and password to log into my account.

#
Description
Estimated Time
Owner
1.
Create the login fields for the login page
2hr
Max Fuligni
2.
Create the login panels and set the background for the login page
3hr
Max Fuligni
3. 
Hide password characters with a toggle to show them
1 hr
Max Fuligni
4.
Create a register button that registers a student or an admin depending on the inputted email (e.g. person@petes-parking.com should be an admin
2hr
Max Fuligni
5. 
Display a message telling the user to register with an email if they try to login with an email that is not registered
1hr
Max Fuligni


Acceptance Criteria:
Given that the login fields correctly set up, the login field should be in the format of an email.
Display message if the email is not in email format
Display message if the email is not registered and suggest to register instead
Given that the password field is correctly set up, the password field should change all characters to * to hide characters.



User Story #6:
I would like to view my user profile

#
Description
Estimated Time
Owner
1.
Create the view that will contain all profile fields, such as display name, password, email.
2hrs


2.
Create the car vehicle fields that will be linked to the server when updated
5hrs


3
Create a logout button on the profile view that will end the current session
1hr




Acceptance Criteria:
Given that the user profile is implemented correctly, users can view the information on their profile
Given that the user profile is implemented correctly, users can view the vehicles on their account

User Story #7:
I would like to change my password if I forget it.

#
Description
Estimated Time
Owner
1.
Create a button on the login page for “forgotten” password
1hr


2.
Create a view that takes in an email for input and generates an email to reset their password
3hr


3. 
View pop-ups after the link is clicked, with two input fields for setting a new password.
1hr




Acceptance Criteria:
Given that this user story is implemented correctly, a user can click on a “forgot password” button
Given that this user story is implemented correctly, a user can enter their email into the view that pops up
Given that this user story is implemented correctly, a user will receive an email to reset their password 
Given that the email is not registered, prompt user to input a registered email

User Story #8:
I would like to contact help if I have trouble logging in.

#
Description
Estimated Time
Owner
1.
Create a clickable “Help” button on the login page
1hr


2.
Create a UI with a message section, email section, and send and cancel buttons
3hrs


3
Send the message to the admin email address when the send button is clicked 
2hrs




Acceptance Criteria:
Given that this user story is implemented correctly, when a user clicks on the help button, a UI pops up with a message box and send and cancel buttons
Given that this user story is implemented correctly, a user can write an email inside the message box
Given that this user story is implemented correctly, when a user clicks send, an email is sent to the admin email address containing the message written by the user
Given that this user story is implemented correctly, when a user clicks cancel, the message is deleted and the UI closes

User Story #9:
I would like to delete my account.


#
Description
Estimated Time
Owner
1.
Create a button in the profile view to delete an account that contains a view prompting the user to enter their password to confirm
2 hrs


2.
Clean our database and server of the deleted user’s data 
2hrs




Acceptance Criteria:
Given that the user story is implemented correctly, a user should input their password to confirm that they wish for all of their information to be erased, and that this is a permanent action that cannot be undone once they have requested for deletion. 
Given that we correctly store user information, a user should be in control of their data and at their request, all localized data on our backend should be deleted and their account be logged out of permanently.

User Story #10:
I would like to view the parking map.

#
Description
Estimated Time
Owner
1.
Create the UI map and upload the map image
1 hr
Alec
2.
Create selectable buttons for each parking area
5 hr
Alec
3
Create panel UI when a parking area is selected
4 hr
Alec


Acceptance Criteria:
Given that the login system is complete, when a user logs in they will be able to see the parking map in a default position on the right hand side.
Given that the user successfully logs in, when they look at the map they will be able to determine availability by selecting it.
Given that the map is implemented properly, when a user selects an area they will be able to see the information unique to that area in the panel UI.
Given that the parking area info panel is functional, when a user selects an area they can scroll along the panel to view all information.

User Story #11:
I would like to scroll and zoom on the parking map.

#
Description
Estimated Time
Owner
1.
Add pan/scroll functionality to sweep across map image
2 hr
Alec
2.
Add zoom functionality to be able to select small areas
2 hr
Alec


Acceptance Criteria:
Given that the map is implemented properly, when a user scrolls on their mouse wheel it will zoom into the map image.
Given that the map is implemented properly, when a user clicks and drags it will pan their view across the image.
Given that the map is implemented properly, when a user pans or zooms to an extreme they will be met with a limit to which they cannot pan or zoom anymore.

User Story #12:
I would like to click on a parking area and see accurate information

#
Description
Estimated Time
Owner
1.
Make each parking area clickable and have it display a menu detailing the parking area
5hr


2.
Create a button to park your car in the currently selected parking lot
2hr


3
Update parking areas to be dynamic and contain newly updated information from our server.
10hr



Given that the user story is done correctly, every parking area on our map should be clickable to the user and contain a pop-up that display data about the selected parking area
Given that the user story is done correctly, the user should be able to signify that they are parking their car in this area assuming that there is availability for the parking area.
Given that the user story is done correctly, the user should see real-time information from other self-reported users indicating the amount of volume at any given parking area. 

User Story #13:
I would like to add and edit personal information on my account.

#
Description
Estimated Time
Owner
1.
Create a user account UI that includes personal information.
3hr


2.
Make an edit button for each of the personal information boxes.
1hr


3.
Make the boxes editable when the edit button is clicked and save the edited information
1hr




Acceptance Criteria:
Given that the user account features are implemented properly, a user can view the information on their user profile.
Given that the user account features are implemented properly, a user can click a box to edit the information on their user profile.
Given that the user account features are implemented properly, a user can save the information inputted into their user profile.

User Story #14:
I would like to add, edit, and delete a profile picture from my account.

#
Description
Estimated Time
Owner
1.
Create a box for a profile picture on the user profile.
0.5hr
Naia Echevarria
2.
Create an edit button on the user profile that will allow the user to upload a new image.
1 hr
Naia Echevarria


Accept jpeg and png file formats for profile pictures
1 hr
Naia Echevarria
3
Create a trash can icon that will prompt the user if they are sure they want to delete their profile picture.
0.5 hr
Naia Echevarria
4








Acceptance Criteria:
Given that the profile box is implemented properly,  the user will view their picture on the left side of the navigation panel.
Given that the edit button is implemented correctly, the user will be able to replace their picture as many times as they’d like.
Given that the trash icon is implemented correctly, the user will be able to delete their picture from their profile.

User Story #15:
I would like to add and edit information about my car(s).

#
Description
Estimated Time
Owner
1.
Create a list of cars on the user profile.
1hr


2.
Create an add car button that brings up a UI with fields for make, model, color and license plate.
2hr


3.
Create a delete car button that removes the car from the list & database.
1hr


4.






5.








User Story #16:
I would like to edit my settings to change the display of the application

#
Description
Estimated Time
Owner
1.
Create a view for the settings button that contains a UI for our application settings
2 hr


2.
Be able to update the layout of the application and input hex codes for the colors of different panels within the application 
3hrs


3
Be able to switch to a dark mode that inverts all of the colors of the UI
2hrs




User Story #17:
I would like to search for other student accounts.

#
Description
Estimated Time
Owner
1.
Create a username search field that a user can fill out and a search button 
1hr


2.
Create a view that returns the public profile of the user specified in the search field, if an account with that email / display name exists
3hrs


3
Create a view that returns a “User not found” message if an account with the username specified in the field does not exist
1hr




Acceptance Criteria:
Given that the user search field and search button are implemented properly, the user will either find the user that they searched for, or they will receive a “User not found” message
Given that the user searches for an incomplete name, all potential candidates that fit the search will be displayed. 
Given that an account with the searched username exists, the user will be able to view the public profile of that user attached to that account


User Story #18:
I would like to receive points when I park in a location recognized by our application.

#
Description
Estimated Time
Owner
1.
Create a points system that rewards an amount of points to a user based on their frequency of parking
1hr


2.
Automate points by incrementing user’s points total everytime they self-report themselves as having parked on our application
3hr


3
Display the user’s total points on their profile view
1.5hr


4. 
Add functionality to update point counter asynchronously







User Story #19:
I would like to view and select the options on the sidebar.

#
Description
Estimated Time
Owner
1.
Create the sidebar with necessary options
2 hr
Alec
2.
Allow dropdown menu functionality for options
2 hr
Alec
3
Make buttons direct you to appropriate UI pages
2 hr
Alec
4
Make the sidebar dynamic and adapt to selected pages
4 hr
Alec
5. 
When hovered over an option, the color changes to indicate that it is clickable.
1 hr
Alec


Acceptance Criteria:
Given that the sidebar exists, when a user selects an option on the side panel it will direct them to the appropriate page.
Given that the sidebar functions properly, when a user selects an option the side panel will update to not include the option they had selected.
Given that the sidebar is functional, when a user expands an option it will allow them to select one of the suboptions.
Given that the sidebar is functional, when a user selects a main page button it will direct them back to the main page.
Given that the sidebar options are present and clickable, when a user hovers over an option it will change colors to indicate it is a button.





Backlog

Functional Requirements
User Account Creation & Login
As a user,
I would like to register an account on Pete's Parking portal.
I would like to enter my username and password to log into my account.
I would like to log out of my account.
I would like to change my password if I forget it.
I would like to contact help if I have trouble logging in.
I would like to delete my account.
I would like to personalize the appearance of my app.
		As an admin,
I would like to edit a student account.
I would like to delete a student account.
I would like to give admin privileges to a student account.
Account Information & Parking Pals
As a student,
I would like to add and edit personal information on my account.
I would like to add and edit information about my car.
I would like to add, edit, and delete a profile picture from my account.
I would like to search for other student accounts.
I would like to send a request to another student to become a parking pal.
I would like to accept or deny a parking pal request.
I would like to remove students as parking pals.
I would like to view a list of my parking pals.
I would like to create a carpool group with my parking pals.
		As an admin,	
I would like to view and edit information on student accounts.
Main Menu & Parking Map
As a user,
I would like to view the parking map.
I would like to view and select options on the side panel.
I would like to scroll and zoom on the parking map.
I would like to view detailed images of the map when I select a parking area.
I would like to view parking availability of a selected area.
I would like to view popular times for a selected area.
I would like to view any required parking permits for a selected area.
I would like to view the parking leaderboard.
		As an admin,
I would like to edit sections of the parking map if there are changes to certain parking lots.
I would like to edit the parking leaderboard.

Parking Reservations & Reports
As a student,
I would like to reserve a parking spot to guarantee a space.
I would like to cancel my parking reservation.
I would like to view my past reservations.
I would like to bookmark a parking area for future use.
I would like to view my list of bookmarks.
I would like to report a student who has an expired timer.
I would like to report a student who has poorly parked by submitting an image along with my report.
		As an admin,
I would like to view a student’s parking history.
I would like to cancel a student’s parking reservation.
I would like to have reservations reported to me after 24 hours of expiration.
I would like to be able to generate reports about parking usage.
I would like to review student-submitted reports.
I would like to remove a student’s reporting privileges.
Points & Levels
As a student,
I would like to be awarded points for accurate reports of other students.
I would like to be awarded points for creating a group reservation with my parking pals.
I would like to be awarded points for reaching level milestones.
I would like to send a referral to another person and receive points if successful.
I would like the option to input someone’s referral code when creating my account and receive points.
I would like to receive levels when I park using the app a certain amount of times.
I would like to use my points to reserve a spot in advance before others.
		As an admin,
I would like to manage points on student accounts.
I would like to manage levels on student accounts.

Notifications
As a student,
I would like to turn email notifications on and off.
I would like to have the option to receive an email when my parking timer is almost expired.
I would like to have the option to receive an email when I am reported by a user.
I would like to have the option to receive an email when a student sends me a parking pal request.

Non-Functional Requirements
Security
As a developer,
I would like for any user data on all accounts to be protected within our system.
Usability
As a developer,
I would like for the user interface to be aesthetically pleasing.
I would like for the application to be easy for users to navigate.
Performance
As a developer, 
I would like the web application to be able to support 10000 users concurrently
I would like the user interface to load our parking map in under 5 seconds
Server Requirements
As a developer,
I would like the server to send RESTful API responses from our database in real-time to synchronize with our front-end.
I would like the server to send RESTful API from our frontend to synchronize our database when new data is collected.
I would like the server to have a 98% uptime usage rate.
Appearance
		As a user,
I would like the map to be aesthetically pleasing and concise with information. 
(If time permits) I would like to customize the color mapping of our map 

