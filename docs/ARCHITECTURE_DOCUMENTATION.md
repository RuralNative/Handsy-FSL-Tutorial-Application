# HANDSY: Architecture Documentation

Official author-based documentation for the Android application serving as a clear and comprehensive reference for the PROJECT ARCHITECTURE, ensuring convenient and easier collaboration efforts for maintenance and future development efforts.

The developer of the Handsy Mobile Application, John Berlin Leonor (RuralNative), ensures that the project follows the standard principles adhered to by professional developers in the industry to achieve scalability and robustness of application


## Architecture Overview

The application follows the principles of *Clean Architecture* which defines the boundaries between the application components and the responsibilities each part should have. The principles followed in the development of this project are as follows:
- Separation of Concern
- Drive UI from Data Models
- Single Source of Truth (SSOT)
- Unidirectional Data Flow

The mobile application follows the **Model-View-ViewModel (MVVM)** architectural pattern which separates the application into three main components:
- **Model**: This represents the data and the business logic of the application. It includes the database, network calls, and other data sources.
- **View**: This represents the user interface of the application. It includes Activities, Fragments, and other UI components.
- **ViewModel**: This acts as a bridge between the Model and the View. It handles the business logic and updates the View based on changes in the Model.

The mobile application is structured into two (2) distinct layers:
- **UI Layer**: This layer is responsible for presenting information to the user and handling user interactions. It includes Activities, Fragments, and Views.
- **Data Layer**: This layer is responsible for handling data operations. It includes Repositories, Data Sources, and Entities.

The mobile application implements the standard **Jetpack Compose Navigation** implementation and principles to manage the navigation functionalities of the application which will be explained in depth later in the documentation.

The application uses the **Room Database** for local database storage, further utilizing other Android Architecture Components such as ViewModel and LiveData for managing and observing in a lifecycle-conscious manner.

The mobile application is designed to work **OFFLINE**. 

## Layer Descriptions

The application are separated into two (2) layers of separate concerns/role as discussed below.

### Data Layer

**Description**

The Data Layer is responsible for handling all data operations, ensuring efficient and reliable access to information both online and offline. It adheres to the Single Source of Truth (SSOT) principle, guaranteeing consistency and coherence across the application.

**Responsibilities**

* Fetching data from application database.
* Managing and manipulating data through persistence.
* Exposing data to the UI Layer.

**Components**

* Repositories: The central point of access for data within the layer that encapsulate data sources and provide a clean and unified interface for fetching, saving, and updating data.
* Data Access Objects (DAO): Interfaces that determines how data is accessed from the local SQLite-based database
* Entities: Data classes representing the database tables.
  Interactions:

**Interactions**

The Data Layer interacts primarily with the UI Layer through ViewModels:

* Data Requests: ViewModels request data from Repositories, specifying the required entities and parameters.
* Data Retrieval: Repositories fetch data through Data Access Objects (DAO) and may map such data to Entities.
* Data Exposure: Repositories expose the retrieved data through observables like LiveData, allowing the ViewModel to observe and update the UI.

**Technologies**

Our mobile application utilizes the following technologies within its Data Layer:

* Room Database: For local database implementation and persistence
* Kotlin Coroutines and Flow: For asynchronous access, manipulation, and live observation of data

## Data Layer

### Room Database:

**AppDatabase**: This class defines your single source of truth for local data storage, housing the DAOs for each entity and managing overall database access and schema definitions. It leverages the provided code snippet and functionality.

### Entities:

**User**: Represents a user in the system
**AlphabetLesson**: Represents an single alphabet lesson
**PhrasesLesson**: Represents a single phrase lesson

### Data Access Objects (DAO):

**UserDao**: Provides low-level database access methods for user data
**AlphabetLessonDao**: Provides database access methods for alphabet lesson data
**PhrasesLessonDao**: Provides database access methods for phrases lesson data

### Repositories:

**UserRepository**: Encapsulates access to User data, leveraging the UserDao for retrieving, saving, and updating user information.
**AlphabetLessonRepository**: Encapsulates access to AlphabetLesson data through the AlphabetLessonDao for retrieving, saving, and updating lesson information.
**PhrasesLessonRepository**: Encapsulates access to PhrasesLesson data through the PhrasesLessonDao for retrieving, saving, and updating lesson information.

### User Interface Layer

The application adheres to the modern standards as stated in the Android Developers documentations in building its User Interface Layer. It uses several community-supported libraries to achieve this and maintain high quality and performance of the application which this application aims to meet while being highly accessible for a variety of users.

Information necessary to understand the UI Layer of this application is discussed in the following sub-sections.

### UI Toolkit

#### Design Language

#### Navigation

## Design Patterns

(CONTENT)

## Diagrams

(CONTENT)