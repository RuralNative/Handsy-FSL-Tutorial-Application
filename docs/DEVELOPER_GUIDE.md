# HANDSY: Developer Guide

This is the Developer Guides for Handsy: Filipino Sign Language Tutorial mobile application. This documentation shall provide comprehensive information on the initial setup and development guide for the overall project efforts.

## Quick Start Guide

_Below is the necessary steps to properly setup your environment to start development efforts with the project._

**Prerequisites**

   * Java Development Kit (JDK) 17 or higher
   * Gradle Build Tool 8.6 or higher
   * Android Studio Hedgehog 2021.3 or higher
   * Android Gradle Plugin version 8.3.0 or higher

**Step 1: Fork the Repository**

- Navigate to Github repository of the project
- Click the "Fork" button in the top-right corner of the page.
- This will create a copy of the repository in your own GitHub account.

**Step 2: Clone the Repository**

- Open Android Studio.
- Click on "File" > "New" > "Project from Version Control".
- In the "Repository URL" field, enter the URL of your forked repository.
- Click "Clone".

**Step 3: Import the Project**

- Android Studio will automatically import the project from the Git repository.
 -Once the import is complete, click on "Build" > "Make Project".
 -This will build the project and download all the necessary dependencies.

**Step 4: Run the App**
- Connect an Android device or emulator to your computer.
- Click on the "Run" button in the Android Studio toolbar.
- Select your device or emulator from the list of available devices.
- The app will be installed and launched on your device or emulator.

**Note:**
_The top-level/modular Gradle build files takes care of the dependency importation for you. You do not need to manually add any dependencies for the project. If you wish to update the versions of the project dependencies you may do so within the Centralized Dependency Management file (libs.versions.toml) found inside the top-level **gradle** directory._