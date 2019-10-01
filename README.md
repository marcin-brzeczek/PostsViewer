# PostViewer
Simple post viewer application based on MVI architecture.

## Selected architecture

The whole application is written in kotlin using MVI architecture pattern. This architecture was chosen due to the use cases of the project. There are only two screens, one for preview list of posts and second one for displaying the chosen post. There are not many UI events or input fields where it is worth using MVVM with data binding to observe each field individually.
The MVVM architecture pattern can be too complex for applications whose UI is rather simple. Adding as much level of abstraction in such apps can result in boiler plate code that only makes the underlying logic more complicated. Also, an MVP pattern could be used here, but with patterns such as MVP or MVVM, the business logic and the Views may have a different state at any point. The MVI pattern solves the above issues by making Models represent a state rather than plain old data. Immutable data structures are very easy to handle, it can only be managed in one place and the application will have only one state between all layers.
