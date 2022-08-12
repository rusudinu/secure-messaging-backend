# Secure Messaging (Backend)

This is the backend for a broadcast-based, encrypted secure messaging app.
This allows for chatting anonymously due to the fact that messages are encrypted and never stored, neither on your phone, nor on the server.


## Self-hosting
You can and should self-host the backend on your own server to guarantee that the messages are not leaked. This is not a requirement, but it is recommended. Then, you connect the front-end to this back-end by updating the server url in the app configuration (front-end).

The repo for front-end can be found [here](https://github.com/xrusu/secure-messaging-frontend).


## How to build
Build this project as any other Gradle project. Clone it in your local machine and run `./gradlew build`.


## Others
For other projects / cool stuff, follow me on
[GitHub](https://github.com/xrusu)
