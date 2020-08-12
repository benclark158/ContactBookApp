# ContactBookApp
 
A small project for saving contacts (phone, email and address) in an Android application.

## Justifications

### Activities

3 activities have been developed, these forfill the following tasks: viewing a list of saved contacts, adding a new contact and viewing individual contacts.

#### Contact List

This activity uses a recycler view to create a dynamic list of each contacts name. From here, clicking on a specific contact will open the View Activity, displaying the contact in more detail. The 'plus' in the top right is used to transition to the new contact activity. 

#### New Contact Activity

This is where a user can add a new contact. It uses TextEdit elements for text entry. First name, email and phone number entries are validated if entered by the user. First name must be entered into the app.

#### View Contact Activity

This activity shows the user all of the saved data about an individual contact. It uses 'Text' views to illustrate the data.

### Database

In order for the app to store appropiate data, I implemented a content provider and database handler. I did this to show my understanding over lower level functionality and features compared to higher level data applications just as the 'Room' feature within Android.

### Limitations

As this app was developed in a short time frame (~1 day) the app does have some limitations and isn't what would be expected for a production application. This app focuses on functionality rather than looks/appearance.

For a production app, please see other projects that I have completed.
