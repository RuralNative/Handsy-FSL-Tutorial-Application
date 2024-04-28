# Entry Screen

The EntryScreen() composable is responsible as a bridge between the application initialization and the main content of the application. 

## Purpose

It determines whether the user needs to be directed to the introductory set of screens - UserIntro() and DevsIntro() - for onboarding prior to showing them the app's content; or to simply navigate them to HomeScreen() itself.

It achieves this by checking first if their is a User within the RoomDatabase. If there is at least one - although by design each application in one device can only create/manage one - it will navigate directly to HomeScreen(). If none, it directs the user to the introductory set of screens starting in UserIntro() for onboarding.

## Flowchart

This figure shows the process flowchart visualizing how this Screen achieves its role.

![Process Flowchart](../media/entry_screen_flowchart.svg)

