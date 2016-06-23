# Promotions App


The project makes use of the following 3rd party packages:

     'com.squareup.retrofit2:retrofit:2.0.0-beta4' // Rest client
     'com.squareup.retrofit2:converter-gson:2.0.0-beta4' // Json parser
     'com.squareup.picasso:picasso:2.5.2' // Image downloading API
     
The application uses the endpoint : https://www.abercrombie.com/anf/nativeapp/Feeds/promotions.json
to download a json response and update the UI and the cache with it.
Tha main screen contains a list of all promotions. Clicking on a promotion item takes the user to its detailed view where they can  use the provided button to navigate to a browser for further details about the promotion.

The project also implements a testcase that features the following use case:

The testcase lands the user inside the main screen. It then clicks on the first item to display its details view. The testcase waits for the screen to synchronize and takes the user back to the promotions list screen. 
The testcase reiterates the above scenario with the second item as well.

To setup working with the testcase, open the project in Android Studio.
Click on "Select Run/Debug Configuration" and then "Edit Configurations.."
Use the "+" icon to add a new configuration.
Select "Android Tests"
Type in a name for this cofiguration.
Set the module as "app"
Choose USB device or Emulator based on your device/emulator choice.
Click OK to save this configuration.
Running the project with the new created configuration will invoke the testcase(s).
