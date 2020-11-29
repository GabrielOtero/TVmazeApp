# TVmazeApp

An application for listing TV series, using the API provided by the TVMaze
website.

You can find the API [here](https://www.tvmaze.com/api).

## Features
 - List all of the series contained in the API used by the paging scheme provided by the
API.
 - Allow users to search series by name.
 - The listing and search views must show at least the name and poster image of the
series.
  - After clicking on a series, the application should show the details of the series, showing the following information
    - Name
    - Poster
    - Days and time during which the series airs
    - Genres
    - Summary
    - List of episodes separated by season
  - After clicking on an episode, the application should show the episode’s information,
including:
    - Name
    - Number
    - Season
    - Summary
    - Image, if there is one
    
## Bonus
  - Allow the user to save a series as a favorite.
  - Allow the user to delete a series from the favorites list.
  - Allow the user to browse their favorite series in alphabetical order, and click on one to see its details
  - Unit Tests
    
## Stack
### Kotlin
All features were developed using Kotlin with Jetpack Android components like LyfeCycle, LiveData, ViewModel and Coroutines.

### Dependency Injection
[Koin](https://insert-koin.io/) was the choosen as DI framework

"*A pragmatic lightweight dependency injection framework for Kotlin developers.*"

### Unit Tests
There are unit tests for almost everything

[MockK](https://mockk.io/) allows creating and stubing objects within test code. Mocking objects allows testing in isolation other objects



### MVVM Architecture

![alt text](https://storage.stfalcon.com/uploads/images/586bc9ea08a59.png)

### Clean Architecture

The chosen approach was CA layers in a single module

Its simplest approach that have 3 packages (presentation, domain, data) inside the app module

![alt text](https://miro.medium.com/max/1400/1*u1_5RzcpjsKqQrgGNbxmog.png) 


There are others way to implement Clean Architecture like one CA layer per module or CA layers inside the feature module or even CA layers per feature modules, but since its a very simple app an approach like this is enought
