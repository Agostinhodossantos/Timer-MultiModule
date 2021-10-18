# Hubstaff Recruitment Challenge

Thank you for taking on Hubstaff's recruitment challenge.

We're eager to see what you can do!

Feel free to reach out to rita.oliveira@hubstaff.com or dmitry.neroba@hubstaff.com for any questions you might have, or to submit your solutions.

## The Objective

Hubstaff's application helps people work more efficiently and deliver proof of work to faciliate getting paid for every second you put in.

In this challenge, we'll be asking you to finish the implementation of a simple Stopwatch app using the project provided.

The project itself is a skeleton of the app, based on modular architecture. 

There are 2 service modules in the infrastructure:

`authentication` - which contains the declaration of [AuthenticationManager](services/authentication/src/main/java/com/netsoft/android/authentication/AuthenticationManager.kt) and it's a dummy implementation;

`timer` - which contains only a declaration of [TimeTicker](services/timer/src/main/java/com/netsoft/android/timer/TimeTicker.kt) interface.

### App Requirements

The app should:
 - Consist of 2 screens: "Sign in" and "Timer";

 - Be able to keep ticking even if the user exits the UI and swipe kills it. So when the user opens it again, the timer value includes the elapsed time since the exit.

#### TimeTicker

 - Create the missing TimeTicker implementation, which updates the timer state every second.

#### Sign In screen

The Sign In screen should:
 - Have editable text fields for email and password.
 - Include a "Sign In" button.
 - Allow the user to enter their credentials and perform sign in.
 - Only appear if the user is not signed in yet.
 - Utilize the existing AuthenticationManager implementation for all sign in related actions.

#### Timer screen

The Timer screen should:
 - Utilize the TimeTicker implementation, which also needs to be implemented in the scope of this task and updates the timer state every second.
 - Display the current timer state in the format "HH:mm:ss".
 - Allow users to start/stop timer by clicking on a button. The button caption should inform the users about what action it performs: "Start" or "Stop".


## Submission

For the execution of this challenge, we'd like you to follow these guidelines:
- Implement the challenge in Kotlin.
- Follow a modular architecture.
- Preferably use the MVVM pattern inside modules.
- Implement the UI with Jetpack Compose.
- Use Hilt as DI - it's already configured in the project.
- Add at least a few tests for the implemented classes.


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
