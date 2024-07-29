# About

This project is a training project to practice some domain driven design concepts using spring boot for the different
services.

The project will handle the backend for an online bookstore application. The next part will enumerate the necessary user
stories.

Sure, I'll format the use cases accordingly:

## User registration and authentication

### Use case: User Registration

`Description`: A new user registers an account with the bookstore.

`Actors`: User

`Preconditions`: User must not already have an account.

`Main Success Scenario`:

* User provides their email, username, and password.
* System validates the information.
* System creates a new account.
* User receives a confirmation email.

### Use case: User Login

`Description`: A registered user logs into their account.

`Actors`: User

`Preconditions`: User must be registered.

`Main Success Scenario`:

* User provides their email and password.
* System validates the credentials.
* User gains access to their account.

## Book Management

### Use case: Add New Book

`Description`: Admin adds a new book to the inventory.

`Actors`: Admin

`Preconditions`: Admin must be logged in.

`Main Success Scenario`:

* Admin provides book details (title, author, price, description, etc.).
* System validates the details.
* System adds the book to the inventory.

### Use case: Update Book Details

`Description`: Admin updates the details of an existing book.

`Actors`: Admin

`Preconditions`: Admin must be logged in and the book must exist in the inventory.

`Main Success Scenario`:

* Admin selects a book to update.
* Admin provides the updated details.
* System validates the updated details.
* System updates the book information in the inventory.

## Shopping Cart and Checkout

### Use case: Add Book to Cart

`Description`: User adds a book to their shopping cart.

`Actors`: User

`Preconditions`: User must be logged in.

`Main Success Scenario`:

* User selects a book to add to the cart.
* System adds the book to the user’s shopping cart.

### Use case: Checkout

`Description`: User checks out their cart to purchase books.

`Actors`: User

`Preconditions`: User must be logged in and have books in their cart.

`Main Success Scenario`:

* User reviews the items in their cart.
* User provides payment details.
* System processes the payment.
* System confirms the order and provides an order summary.

## Order Management

### Use case: View Order History

`Description`: User views their past orders.

`Actors`: User

`Preconditions`: User must be logged in.

`Main Success Scenario`:

* User navigates to their order history.
* System retrieves and displays the user’s past orders.

### Use case: Track Order

`Description`: User tracks the status of an ongoing order.

`Actors`: User

`Preconditions`: User must be logged in and have an ongoing order.

`Main Success Scenario`:

* User selects an order to track.
* System retrieves and displays the current status of the order.

## Reviews and Ratings

### Use case: Write a Review

`Description`: User writes a review for a purchased book.

`Actors`: User

`Preconditions`: User must be logged in and have purchased the book.

`Main Success Scenario`:

* User navigates to the book’s page.
* User provides a review and rating.
* System validates and posts the review.

## Notifications

### Use case: Send Notification

`Description`: System sends notifications to users (e.g., order updates, promotions).

`Actors`: System

`Preconditions`: User must be subscribed to notifications.

`Main Success Scenario`:

* System identifies a trigger for a notification (e.g., order shipped).
* System sends the notification to the user.
