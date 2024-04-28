# UserIntro and DevsIntro

The UserIntro and DevsIntro Screens are responsible for user onboarding. For this specific set of screens, we have only one documentation file due to the simplicity and similarity of their functions and shall be considered as the introductory set of screens in further dev docs of the repository.

## Purpose

The UserIntro() provides a UI that allows the user to primarily set their user name. This user name will be used as necessary data to complete the UI of the HomeScreen().

The DevsIntro() provides a UI that provides additional information from the developers itself, especially hand sign references.

## Function

Upon initialization, three UI components - header, image, and name input field - are composed in an animated manner. The user then inputs their name on the name input field. Real-time check of the name input are done asynchronously, verifying if the input has less than 11 characters on it. If the input has more than 10 characters, a supporting text is composed with the name input field, showing error message and signifying the input will not be accepted by the application. Successful input allows the user to navigate into the next screen.

## Flowchart

This figure shows the process flowchart visualizing how these Screens achieves its role.

![Process Flowchart](../media/user_and_devs_intro.svg)