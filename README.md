
# Queue List Challenge

## Guidelines

- Create a new git repository from this existing project
- Solve the levels in ascending order
- Only do one commit per level and include the .git directory when submitting your test
- Please use Kotlin

## Pointers

You can have a look at the higher levels, but please do the simplest thing that could work for the level you’re currently solving.

We are interested in seeing code that is clean, simple and robust.

Feel free to use any libs and technics you are confortable with but again, keep it simple and do not make a showcase app for every library you know.

A **bonus** is given to people using Unit Test to solve the challenges.


## Sending Your Results

Once you are done, please send your results directly to the person you are talking to.
You can send your GitHub project link or zip your directory and send it via email. If you do not use GitHub, don’t forget to attach your `.git` folder.

## Good luck!

## Challenge

A `QueueList` is a central element at Deezer. In fact, the whole player is based on this `QueueList`. The challenge here will be to build a simple `QueueList`.

The core of an app like Deezer is the player: the component that enable us to have the wanted Audio output.
This component need to get elements to play. Those elements, called `Track` (you can find a preview of it's structure in the json `data/tracks.json`) are stacked into a list that represent our `QueueList`. To be more explicit, a `QueueList` is a container that can represent an Album or a Playlist but stay flexible (add or remove elements, reorder them, ... ).
The aim of this `QueueList` is to provide access to the `Tracks` it contains whenever the player need it. 

### Level 1:

In this first challenge you will create this expected `QueueList`:
- It should be able to contain `Track` elements
- Have a method `getCurrentTrack` which enable us to get the currently playing `Track`
- A method `next` which will return the next track and update the current track
- A method `previous` which will return the previous track and update the current track

**N.B.** you don't need to deserialize the json right now, this is for the Level 3. Here the json is to give you an idea of elements we will have.

### Level 2

Now you will add 2 new methods to our `QueueList`:

- `add`: enable us to add one or more items at the end of the `QueueList`
-  `removeAt`: enable us to remove an item anywhere in `QueueList`

### Level 3

Here we go, now you can use and deserialize the `data/tracks.json` to fill the QueueList. 
To do so, we recommend using the `kotlinx-serialization-json` library (Available [here](https://github.com/Kotlin/kotlinx.serialization)), but if you are more familiar with another one, feel free to use it.

