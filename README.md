# HANDSY Filipino Sign Language Tutorial Application with Hand Sign Recognition (AI)

## Description

Handsy is an Android-based mobile application that aims to provide an accessible reference of learning for people to learn how to use the Filipino Sign Language. With simple visual aids to learn how to read and sign the Filipino alphabet and basic phrases for communication and an additional AI-assisted feature of assessing the learnings of the user by letting them show their hand signals through their phone's camera and checking what hand sign, it aims to revolutionize how we can make our communities increasingly inclusive towards people that do not have the ability to communicate through verbal words.

## Development Pre-requisites

Below are the necessary development tools and supporting software for the proper setup of the development environment.

### Android Development

- Java Development Kit 17 and above
- Android Studio Iguana (2023.2.1) and above

### ML Model Development

- Python SDK v3.12.3
- JupyterLab v4.1.8
- MediaPipe Model Maker 0.10.11

Take note that the actual development of the model was done within Google Colabs, due to the fact that developer's laptop is incapable of heavy operations such as this model training.

### Project Dependencies

The version control of dependencies for the project are already taken care by a [Central Dependency Management file](/gradle/libs.versions.toml) found within the Gradle directory - refer to it to the specifics of the project's dependencies - and Gradle build scripts within the project takes care of its importation and other relevant duties.

## Developer's Guide

The project's codebase is built with adherence to the concepts and principles of Clean Code and Modern Android Development guides to ensure it adheres to industry standards, familiar to professional developers. 

To serve as a guide for developer for future development if considered by OTIS Inc., a centralized project architecture file named as [ARCHITECTURE_DOCS](/docs/ARCHITECTURE_DOCS.md) is provided within the Docs directory. It is based upon the concepts and principles provided by [arc42 Documentation](https://arc42.org/?ref=workingsoftware.dev) and [c4model Documentation](https://c4model.com/?ref=workingsoftware.dev) for proper architectural documentation

## Credits

This project will not be achieved without the support of the following individuals, and open-sourced resources from the listed individuals.

- **TecPerson with Sign Language MNIST (American Sign Language) Dataset from Kaggle**
  - The ML model utilized several resources for various letter provided within the dataset due to the similarity of it to Filipino Sign Language
- **John Gabriel Porton with FSL Dataset from Kaggle**
  - This serves as the primary dataset of use for the development of the AI model