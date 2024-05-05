# Imgur Gallery

## Info

- Imgur Gallery is a small app which will show you the top images of the week on Imgur based on the text you enter in Search bar if it exists.
- You can see the images in either List or in Grid view. By default it is shown in List view.
- When click on the top right icon, it will toggle between List and Grid based on what you want to see.
- Each image shown consists the following details:
  1. Image
  2. Title of that image
  3. Time at which image was uploaded
  4. Number of images in that album
- There is also internet connection checking functionality which will pop up if internet is disconnected. It will automatically shown the images once internet is connected.
- While opening the app, there will be no images as the search is empty. As soon as you enter the text, the images will appear.
- The app is made in Light and Dark modes. It will show the app based on the theme selected as System Default in the app settings.

## Solution Design

- The app has Single Activity MVVM architecture which is recommended by Google and should be used if one has no idea where to start. Here the code is divided into 3 packages:
  1. View - consists of fragments
  2. ViewModel - consists of ViewModel
  3. Model - consists of Model, DataModel, API, and Repository.
- The entire app is connected with dependencies using the Hilt DI framework. Again, it is recommended by the Google and has great developer support along with ease of use and compile-time checks.
- For networking, I have used Retrofit which is the most popular networking library, easy to use, supports all the functionalities and is compatible with Android components.
- Besides that, I have used different modules for different use cases of providing dependencies in the app.
- For navigation, I have used the Jetpack navigation library which helps navigate the fragments very easily and reduces a lot of manual handling of state management and transitions.
- For referencing view, I have used ViewBinding which is faster and much safer compared to other alternatives such as DataBinding, findViewById, and Kotlin synthetics.
- Finally, for the UI part, since it takes a lot of time to design a good UI, I went with the black-and-white approach. The UI will change once the dark mode is changed in the system settings.