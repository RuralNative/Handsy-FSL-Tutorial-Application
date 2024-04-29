# Architectural Documentation

This documentation is a compiled information of the architecture of the Handsy application for the benefit of development and maintenance efforts, formatted based from [arc42 template](https://www.workingsoftware.dev/software-architecture-documentation-the-ultimate-guide/) standards, complemented with guidelines provided by [c4 model](https://c4model.com/?ref=workingsoftware.dev) for abstraction visualization.

## 1. Introduction and Goals

This section introduces the task and outlines the objectives pursued by Handsy: Filipino Sign Language

### 1.1 Requirements Overview

#### What is Handsy?

Handsy is an Android-based mobile application aiming to be one of the catalysts towards a more inclusive community for people with hearing and/or speaking disability here in the Philippines, achieved through an accessible hand sign tutorial mobile application powered by Artifical Intelligence.

#### Essential Features

- Offline-access application to ensure accessibility for users in remote areas
- Visualized lessons for Filipino Sign Language (FSL) through pictures
- AI Gesture recognition through in-built device camera for learning assessment

### 1.2 Quality Goals

The following goals listed below with its description are the benchmarks to determine the application was built in the highest quality and on par with industry-grade applications built by professionals.

#### Performance

The application's gesture recognition feature, built with AI, is expected to consume the most of the resources of the user's device. It must be designed and developed in a manner that the resources needed for it to run properly without noticeable visual lags is minimal and low-spec phones could still reasonably operate it.

#### Testing

The codebase of the application must be reasonably tested to prevent unhandled errors and catch issues during development. This ensures that the application can operate with no fear of crashes/bugs occurring when used by the end users. 

#### Usability

The application's user interface/user experience must abide to reasonable extents to the concepts and principles provided in Material Design 3 design language to ensure that it is looks and feels user-friendly for the end users. It must also integrate an onboarding section in its application for new users to provide a good impression for them.

## 2. Architectural Constraints

At the beginning of the project various constraints had to be respected within the design of Handsy. They still affect the solution. This section represents this restrictions and explains - where necessary - the motivations

### 2.1 Technical Constraints

**Use of Mobile Devices** - Application is limited to the use of mobile devices as it presents as the most accessible platform for the end users.

**Use of Android as OS of Choice** - Application is limited to the use of mobile devices operating under the Android operating system as it presents the largest share of mobile users

**Preference towards Jetpack Compose and Kotlin** - Application is built upon the Jetpack Compose library with Kotlin as programming language of choice as it is the current standard in the Android development.

### 2.2 Conventions

**Material 3 as Design Langauge** - Utilized the concepts and principles for the design of the User Interface/User Experience of the application

**Modern Android Development Guidelines** - Utilized the concepts and principles of the Modern Android Development provided by Google for ease of development and ensure the utilization of established and well-maintained technologies and tools for future development.

These are requirement constrains for design and implementation decisions for the application.

* Operates on an Android OS with a minimum version of 8.1
* Offline-first operation with no support for online utilization

## 3. Context

This section describes the environment of Handsy, especially its end users.

### 3.1 Business Context

### Learners (users)

Handsy is built for the use of people who has interest in learning the Filipino Sign Language especially the proper signing of it. All features integrated within the application is designed to meet that purpose.

## 4. Solution Strategy

This section contains a highly compact architecture overview of the application, with a contrast of the most important goals and approaches.

## Solution Strategy 

The architectural design and development of the application is built in a philosophy that focuses towards functionality, maintainability, and readability, rather than the inclusion of modern technologies or tools for the sake of it.

Due to this, the project will utilize the standards, libraries, and frameworks stipulated within the [Modern Android Development](https://developer.android.com/topic/architecture/intro) built by Google as it offers long-term support and extensive documentation and examples future development efforts can rely upon. 

Below are the technological solutions utilized for the various application functionality:

* **Keras and MediaPipe** as preferred tools for AI gesture recognition model
* **Room Database** as preferred library for data management, persistence, and storage
* **Dagger Hilt** as preferred library for automated dependency injection
* **CameraX** as preferred API for utilzing native camera functionality
* **Jetpack Compose** as preferred libraries for building user interface
* **Material Design 3** as preferred primary design language for UI/UX design 
* **Compose Navigation** as preferred library for navigating between screens
* **Accompanist** as preferred library to complement Android functionalities non-existent for Jetpack Compose - such as requesting permissions

This architectural approaches and solutions are also effectively utilized to achieved the stipulated quality goals stated on the [Introduction](#quality-goals) section.

## Building Blocks

This section describes the abstract and visualized decomposition of the project codebase, as represented by the packaging within. 

### Architectural Overview

![High Level Architecture](media/high-level-architecture.svg)

The codebase is categorically divided into two layers: the **Data Layer** and the **UI Layer**. 

#### Data Layer

The Data Layer is responsible for handling all data operations, specifically the lessons and the end user's information necessary for the proper performance of the application, and exposes such data to the UI Layer for display.

Below are its categorical components:

* Room Database : Single source of truth for local data storage and responsible for management of overall database access and schema definitions.

* Entities : Representation of a table within the Room Database

* Data Access Object (DAO) : Interface that provides database access methods for each Entity

* Repository : Encapsulates access and modification of Entity data through DAO interfaces, and is the component directly used by the UI Layer.

#### UI Layer

The UI Layer is responsible for providing the necesary functionalities to provide an interactible display on screen, either by utilizing data from the Data Layer or through its own.

Below are its categorical components:

* State : Data classes containing the state of the Composables.

* ViewModel : Handles logic of the UI Layer. It leverages the Data Layer and state (and in some occassion, Utility classes within the UI Layer) to ensure they are stateless and focused on UI render roles.

* Composables : Composable functions responsible for rendering UI to screen