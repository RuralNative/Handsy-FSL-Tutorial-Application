# Architectural Documentation

This documentation is a compiled information of the architecture of the Handsy application for the benefit of development and maintenance efforts, formatted based from [arc42 template](https://www.workingsoftware.dev/software-architecture-documentation-the-ultimate-guide/) standards, complemented with guidelines provided by [c4 model](https://c4model.com/?ref=workingsoftware.dev) for abstraction visualization.

## 1. Introduction and Goals

This section introduces the task and outlines the objectives pursued by Handsy: Filipino Sign Language

### 1.1. Requirements Overview

#### What is Handsy?

Handsy is an Android-based mobile application aiming to be one of the catalysts towards a more inclusive community for people with hearing and/or speaking disability here in the Philippines, achieved through an accessible hand sign tutorial mobile application powered by Artifical Intelligence.

#### Essential Features

- Offline-access application to ensure accessibility for users in remote areas
- Visualized lessons for Filipino Sign Language (FSL) through pictures
- AI Gesture recognition through in-built device camera for learning assessment

### 1.2. Quality Goals

The following goals listed below with its description are the benchmarks to determine the application was built in the highest quality and on par with industry-grade applications built by professionals.

#### Performance

The application's gesture recognition feature, built with AI, is expected to consume the most of the resources of the user's device. It must be designed and developed in a manner that the resources needed for it to run properly without noticeable visual lags is minimal and low-spec phones could still reasonably operate it.

### Stability

The application's codebase must be built upon well-established and well-maintained tools and libraries to ensure its performance stability and ease of development due to support from such tool's creators/authors.

#### Testing

The codebase of the application must be reasonably tested to prevent unhandled errors and catch issues during development. This ensures that the application can operate with no fear of crashes/bugs occurring when used by the end users. 

#### Usability

The application's user interface/user experience must abide to reasonable extents to the concepts and principles provided in Material Design 3 design language to ensure that it is looks and feels user-friendly for the end users. It must also integrate an onboarding section in its application for new users to provide a good impression for them.

## 2. Architectural Constraints

At the beginning of the project various constraints had to be respected within the design of Handsy. They still affect the solution. This section represents this restrictions and explains - where necessary - the motivations

### 2.1. Technical Constraints

**Use of Mobile Devices** - Application is limited to the use of mobile devices as it presents as the most accessible platform for the end users.

**Use of Android as OS of Choice** - Application is limited to the use of mobile devices operating under the Android operating system as it presents the largest share of mobile users

**Preference towards Jetpack Compose and Kotlin** - Application is built upon the Jetpack Compose library with Kotlin as programming language of choice as it is the current standard in the Android development.

### 2.2. Conventions

**Material 3 as Design Langauge** - Utilized the concepts and principles for the design of the User Interface/User Experience of the application

**Modern Android Development Guidelines** - Utilized the concepts and principles of the Modern Android Development provided by Google for ease of development and ensure the utilization of established and well-maintained technologies and tools for future development.

These are requirement constrains for design and implementation decisions for the application.

* Operates on an Android OS with a minimum version of 8.1
* Offline-first operation with no support for online utilization

## 3. Context

This section describes the environment of Handsy, especially its end users.

### 3.1. Business Context

### Learners (users)

Handsy is built for the use of people who has interest in learning the Filipino Sign Language especially the proper signing of it. All features integrated within the application is designed to meet that purpose.

## 4. Solution Strategy

This section contains a highly compact architecture overview of the application, with a contrast of the most important goals and approaches.

### 4.1. Introduction

The following information below contrasts the quality goals of Handsy (-> [Section 1.2](#12-quality-goals)).

#### Performance

The application will combine the light-weight features provided by the MediaPipe Solutions frameworks with its complementary MediaPipe Model Maker and Tasks API, and CameraX of Jetpack Compose for the design and development of the Camera component with AI-powered Gesture Recognition feature.

### Stability

The application will be built upon the Jetpack Compose libraries and toolings for its entire codebase as it provides performance stability, long-term support, and well-documented examples to derive from the various components of the application and ensure future developments can be done with ease.

#### Testing

For the Android application, the Data Layer will be rigorously tested to ensure it properly functions.

For the ML model, proper tests are made during its training to ensure it is as accurate as possible.

#### Usability

The user interface / user experience will be based upon the concepts and principles of Material Design 3.

### 4.2. Structure

Handsy is implemented as a Kotlin program leveraging Jetpack Compose libraries for its codebase, Material Design 3 as its design language, and MediaPipe Solutions with complementary MediaPipe Model Maker and MediaPipe Tasks API for the gesture recognition model.

It utilizes the concepts and principles discussed within the official documentation of Modern Android Development, such as the utilization of MVVM design pattern and the use of a multi-layered architecture consisting of the Data Layer and the UI Layer.

## 5. Building Blocks

This section describes the abstract and visualized decomposition of the project codebase, as represented by the packaging within. 

### 5.1 Architectural Overview

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

## 6. Runtime View

This section provides a visualization of how the different components of the application operates together to achieve its different roles.

### 6.1. Screen Navigation

Below is the flow representing how the user is navigated through different screens depending on which screen is shown and their actions within it.

![Navigation Runtime View](media/runtime-view.drawio.svg)

### 6.2. Gesture Recognition with Camera

Below is the flow representing how a single image frame from the camera's feed is processed by the AI model for recognition.

![Gesture Recognition Runtime View](media/gesture_logic.drawio.svg)

The figure above shows the overall logic of the gesture recognition feature of the application.

To begin with, the CameraX component contained within the CameraGestureRecognition Composable provides an ImageProxy - an interface that handles image frames captured by the camera - to an ImageAnalysis.Analyzer named GestureAnalysisAnalyzer to begin analysis and recognition. 

The GestureAnalysisAnalyzer serves only as a bridge between the CameraX Image Analysis use case and the actual Kotlin class that handles recognition using MediaPipe Tasks API, and its function analyze() passes this ImageProxy to the GestureRecognizerHelper for recognition.

The GestureRecognizerHelper is responsible for the actual role of transforming the ImageProxy to readable format for the model to interpret with. It utilizes the MediaPipe Tasks API to achieve this. Its function recognizeLiveStream() is responsible for performing transformation and manipulation of the ImageProxy into a readable MPImage compatible for the AI model to recognize. It passes this MPImage to recognizeAsync() which runs this format through the model for recognition. returnLiveStreamResult() extracts the result returned by the AI model and passes this as a ResultBundle to the ViewModel for transformation into something the Screen UI can interpret.

The ViewModel's onResult() transform the ResultBundle into different information with the help of extractGesturesList() and update the State of the Composable screen. Note that the actual gesture is a collection of a specific type that requires different operations before a String format info can be extracted from it. This is due to the fact that the raw result is actually a list of detected gestures and each gesture contains first an outer list of different hands detected, and its inner list containing the actual information for the gesture.

The State, adhering to the principle of Modern Android Development of Unidirectional Flow is passed as a StateFlow through the ViewModel to the CameraGestureRecognition Composable screen to be used to update the ResultContainer within it.