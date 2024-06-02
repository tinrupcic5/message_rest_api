# Message App

A message REST API is a type of web-based interface that allows developers to access and manipulate data related to messaging through a standardized set of HTTP requests.
This type of API typically provides a range of functionalities, such as sending and receiving messages, managing contacts, and accessing message histories.

## Description of relationship

This schema represents a database for a chat application. The database has four tables: users, chats, messages, and participants.

The users table stores information about the users of the application, including a unique user_id, username, password, display_name, and phone_number.

The chats table stores information about the chats in the application, including a unique chat_id, chat_name, the user_id of the creator of the chat, and the creation timestamp. The user_id is a foreign key that references the user_id column in the users table. This relationship ensures that every chat is associated with a user who created it.

The messages table stores information about the messages sent in the chats, including a unique message_id, message_text, the chat_id of the chat the message was sent in, the user_id of the sender of the message, and the creation timestamp. The chat_id and user_id columns are foreign keys that reference the chat_id and user_id columns in the chats and users tables, respectively. These relationships ensure that every message is associated with a chat and a user.

The participants table stores information about the participants in the chats, including a unique participant_id, the chat_id of the chat the participant is in, and the user_id of the participant. The chat_id and user_id columns are foreign keys that reference the chat_id and user_id columns in the chats and users tables, respectively. These relationships ensure that every participant is associated with a chat and a user.

To explain how the chat will work with the participants, let's consider the following scenario:
Suppose a user wants to send a message in a chat. The chat application will first retrieve the chat_id of the chat the user wants to send a message in. Then, the application will insert a new row in the messages table with the message_text, chat_id, user_id, and creation timestamp. The message will now be associated with the chat and the user who sent it.
If the user wants to create a new chat, the application will first insert a new row in the chats table with the chat_name, user_id, and creation timestamp. The chat will now be associated with the user who created it. The application will also insert a new row in the participants table with the chat_id and user_id of the creator of the chat. This ensures that the user who created the chat is also a participant in it.
If the user wants to join an existing chat, the application will insert a new row in the participants table with the chat_id and user_id of the user who wants to join. This ensures that the user is now a participant in the chat.
Overall, the relationships between the tables ensure that the chat application can track the chats, messages, and participants in a consistent and reliable way.


## Running the application

### Prerequisites

In short:
* OpenJDK 18
* [Maven 3.6](http://maven.apache.org/)
* [Docker](https://docs.docker.com/get-docker/) & [docker-compose](https://docs.docker.com/compose/install/)
* [Git](https://git-scm.com/)

### Running the application locally

```commandline
$ docker-compose up -d
$ mvn clean install
```

##### To remove docker container
```commandline
$ docker compose down --remove-orphans
```

## ktlint
#### Install the ktlint plugin in IntelliJ:

Go to "Settings" or "Preferences". Navigate to "Plugins" on the left side.
Click on the "Marketplace" tab, search for "ktlint" and install it.
Follow the prompts to complete the installation and restart IntelliJ.

#### Enable format on save:

Go to "Settings" or "Preferences". Navigate to "Tools" -> "ktlint".
On the right side check the "Run ktlint --format on save" checkbox. Save the changes.

#### Run 'spotless:check' to check for violations or 'mvn spotless:apply' to fix these violations.

```commandline
mvn spotless:check
```
```commandline
mvn spotless:apply
```
