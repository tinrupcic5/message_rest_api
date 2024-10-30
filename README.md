# Message App REST API :bee:

A message REST API is a type of web-based interface that allows developers to access and manipulate data related to messaging through a standardized set of HTTP requests.
This type of API typically provides a range of functionalities, such as sending and receiving messages, managing contacts, and accessing message histories.

## Relationship
```commandline
+---------------------+      +---------------------+       +---------------------+
|      firebase       |      |       users         |       |       chats         |
|---------------------|      |---------------------|       |---------------------|
| firebase_id (PK)    |<---->| user_id (PK)        |<----->| chat_id (PK)        |
| user_id (FK)        |      | username (Unique)   |       | chat_name           |
| firebase_token      |      | password            |       | created_at          |
+---------------------+      | email (Unique)      |       | user_id (FK)        |
                             | phone_number        |       +---------------------+
                             +---------------------+          |
                                     |                        |
                                     |                        |
                                     |                        |
                                     |                        |
+---------------------+              |              +---------------------+
|    messages         |              |              |    participants     |
|---------------------|              |              |---------------------|
| message_id (PK)     |<-------------               | participant_id (PK) |
| message_text        |                             | chat_id (FK)        |
| created_at          |                             | user_id (FK)        |
| chat_id (FK)        |                             +---------------------+
| user_id (FK)        |
| is_read             |
+---------------------+
```
# Setting up Firebase with Spring Boot

This guide will walk you through setting up a Firebase project, downloading the `serviceAccountKey.json` file, and integrating it into a Spring Boot project.

## Prerequisites
- Firebase account ([Firebase Console](https://console.firebase.google.com/))
- Java & Spring Boot installed :tea:
- Project Documentation ([Documentation CRO](https://github.com/tinrupcic5/message_rest_api/blob/dev/src/main/resources/documents/mobilna_aplikacija_za_razmjenu_poruka_tin_rupcic.pdf))

---
## Android app
- Message App Android ([github repository](https://github.com/tinrupcic5/message_app_droid))


## 1. Create a Firebase Project

1. Go to the [Firebase Console](https://console.firebase.google.com/).
2. Click **Add Project**.
3. Enter your project name and follow the setup steps.
4. Once created, you'll be redirected to the project dashboard.

---

## 2. Generate a Service Account Key

1. In the Firebase Console, go to **Project Settings** by clicking the gear icon.
2. Select the **Service accounts** tab.
3. Click **Generate New Private Key**.
4. Confirm to download the JSON file (`serviceAccountKey.json`).

---

## 3. Add `serviceAccountKey.json` to Spring Boot Project

1. Place the downloaded `serviceAccountKey.json` file in the `src/main/resources` directory of your Spring Boot project.
2. Ensure the file is securely stored and not exposed to the public (add to `.gitignore`).

---


3. Configure Firebase in a Application.kt class in your Spring Boot project:

    ```java
    ClassPathResource("src/main/resources/serviceAccountKey.json").inputStream
    ```
4. Add environment variable

    ```commandline
    GOOGLE_APPLICATION_CREDENTIALS = path to serviceAccountKey
    ```

## Running the application


### Prerequisites

* [Docker](https://docs.docker.com/get-docker/) & [docker-compose](https://docs.docker.com/compose/install/)

### Running the application locally

```commandline
$ docker-compose up -d
$ mvn clean install
```

##### To remove docker container
```commandline
$ docker compose down --remove-orphans
```

## Linter - ktlint
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
