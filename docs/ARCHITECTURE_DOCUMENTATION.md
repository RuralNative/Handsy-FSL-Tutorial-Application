# HANDSY: Architecture Documentation

Official author-based documentation for the Android application serving as a clear and comprehensive reference for the PROJECT ARCHITECTURE, ensuring convenient and easier collaboration efforts for maintenance and future development efforts.

The developer of the Handsy Mobile Application, John Berlin Leonor (RuralNative), ensures that the project follows the standard principles adhered to by professional developers in the industry to achieve scalability and robustness of application


## Architecture Overview

The application follows the principles of *Clean Architecture* which defines the boundaries between the application components and the responsibilities each part should have. The principles followed in the development of this project are as follows:
- Separation of Concern
- Drive UI from Data Models
- Single Source of Truth (SSOT)
- Unidirectional Data Flow

The mobile application follows the *Model-View-ViewModel (MVVM)* architectural pattern which separates the application into three main components:
- *Model*: This represents the data and the business logic of the application. It includes the database, network calls, and other data sources.
- *View*: This represents the user interface of the application. It includes Activities, Fragments, and other UI components.
- *ViewModel*: This acts as a bridge between the Model and the View. It handles the business logic and updates the View based on changes in the Model.

The mobile application is structured into two (2) distinct layers:
- *UI Layer*: This layer is responsible for presenting information to the user and handling user interactions. It includes Activities, Fragments, and Views.
- *Data Layer*: This layer is responsible for handling data operations. It includes Repositories, Data Sources, and Entities.

The application uses the *Room Database* for local database storage, further utilizing other Android Architecture Components such as ViewModel and LiveData for managing and observing in a lifecycle-conscious manner.

The mobile application is designed to work *OFFLINE*. 

## Layer Descriptions

(CONTENT)

## Component Descriptions

(CONTENT)

## Design Patterns

(CONTENT)

## Dependency Injection/s

(CONTENT)

## Diagrams

(CONTENT)