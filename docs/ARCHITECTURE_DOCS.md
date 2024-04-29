# Architectural Documentation

This documentation is a compiled information of the architecture of the Handsy application for the benefit of development and maintenance efforts, formatted based from [arc42 template](https://www.workingsoftware.dev/software-architecture-documentation-the-ultimate-guide/) standards, complemented with guidelines provided by [c4 model](https://c4model.com/?ref=workingsoftware.dev) for abstraction visualization.

## Introduction and Goals

This section introduces the task and outlines the objectives pursued by Handsy: Filipino Sign Language

### 1.1 Requirements Overview

#### What is Handsy?

Handsy is an Android-based mobile application aiming to be one of the catalysts towards a more inclusive community for people with hearing and/or speaking disability here in the Philippines, achieved through an accessible hand sign tutorial mobile application powered by Artifical Intelligence.

#### Essential Features

- Visualized lessons for Filipino Sign Language (FSL) through pictures
- AI Gesture recognition through in-built device camera for learning assessment

### 1.2 Quality Goals

The following quality goals listed below with its description are the 

### Requirements Overview
1. Offline-based Android application usable within areas of no or low internet connection
2. Accurate and visualized tutorials for Filipino Sign Language alphabet
3. Camera functionality for hand sign recognition via AI model with acceptable recognition accuracy 

### Quality Goals

Three architectural goals of high importance for the stated purpose of the project

#### Functional Suitability

Application offers visualized lessons for the Filipino Sign Language alphabet and a functional camera component for recognizing hand signs shown by the user on phone's camera.

#### Usability

Application has an accessible and quality user interface and user experience.

#### Performance Efficiency

Application utilizes the available resources offer by the native device to the best of its ability to ensure acceptable performance at worst for the end users.

## Architectural Constraints

These are requirement constrains for design and implementation decisions for the application.

* Operates on an Android OS with a minimum version of 8.1
* Offline-first operation with no support for online utilization

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