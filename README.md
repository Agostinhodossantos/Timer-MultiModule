# Hubstaff Recruitment Challenge

Thank you for taking on Hubstaff's recruitment challenge.

We're eager to see what you can do!

Feel free to reach out to rita.oliveira@hubstaff.com or dmitry.neroba@hubstaff.com for any questions you might have, or to submit your solutions.

## The Objective

Finish implementation of a simple Stopwatch app using provided project.
The project itself is a skeleton of the app, based on modular architecture. 

Among  infrastructure modules it contains 2 service modules:

`authentication` -  contains declaration of [AuthenticationManager](services/authentication/src/main/java/com/netsoft/android/authentication/AuthenticationManager.kt) and it's dummy implementation

`timer` - contains only declaration of [TimeTicker](services/timer/src/main/java/com/netsoft/android/timer/TimeTicker.kt) interface

### App requirements

 - App should consists of 2 screens: "Sign in" and "Timer"

 - And it should be able to keep ticking even if user exit UI and swipe killed it. So when user open it again, timer value includes elapsed time since exit.

#### TimeTicker

- Create the missing  TimeTicker implementation, which updates timer state every second

#### Sign In screen

 - has editable text fields for email and password
   
 - button "sign in"
   
 - let user enter credentials and perform sign in
   
 - screen appears only if user is not signed in yet
   
 - utilize existing AuthenticationManager implementation for all sign in related actions

#### Timer screen

  - utilize TimeTicker implementation, which also needs to be implemented in scope of this task and updates timer state every second   
    
  - display current timer sate in a format "HH:mm:ss"
    
  - let user start/stop timer by click on a button 
    
  - button caption should inform user about action it performs: Start or Stop





## Submission

Implementation needs to be done in Kotlin.

Please follow modular architecture.

Inside modules it is preferable to use MVVM.

UI needs to be implemented in Jetpack Compose.

Use Hilt as DI, it is already presented in a project.

Add at least few tests for implemented classes.

### Criteria

We're primarily interested in seeing what you think is the best way to solve the issues you'll run into.

Of primary focus, we'll consider:
- The architecture and sustainability of your solution
- The clarity and simplicity of your code
- How you communicate with your peers in your code and commits

### Your Repository

In your cloned repo, please check out a separate branch using your own name.  When you're finished, you'll submit a pull request from this branch back to `main`.

Try and separate out unrelated changes into separate commits. Feel free to use commit messages to describe your choices and let us know what you're thinking.

When you're ready, create a *private* GitHub repo to push your changes into and add the following contributors:
- <a class="user-mention" data-hovercard-type="user" data-hovercard-url="https://github.com/users/kanemara/hovercard" href="https://github.com/kanemara">@kanemara</a>
- <a class="user-mention" data-hovercard-type="user" data-hovercard-url="https://github.com/users/ayarotsky/hovercard" href="https://github.com/ayarotsky">@ayarotsky</a>

Now you can create the pull request from your personal branch back to `main` in your GitHub repo, adding the above users as PR reviewers.

Good luck, private!  We're counting on you.
