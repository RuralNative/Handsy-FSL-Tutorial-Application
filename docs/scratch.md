# Architectural Documentation

This documentation is a compiled information of the architecture of the Handsy application for the benefit of development and maintenance efforts, formatted based from [arc42 template](https://www.workingsoftware.dev/software-architecture-documentation-the-ultimate-guide/) standards and principles.

## Introduction

Handsy is an Android-based mobile application aiming to be one of the catalysts towards a more inclusive community for people with hearing and/or speaking disability here in the Philippines, achieved through an accessible hand sign tutorial mobile application powered by Artifical Intelligence.

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

