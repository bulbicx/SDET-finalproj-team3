# Choonz Final Project

Choonz is a Music Library Web Application that we have been tasked to create for the final project of our SDET course. The project aims to be able to sign in users based on 
differing levels of authority and each user can then CRUD different entities depending on their authentication. This is a full stack application using MySQL for the database, Spring-Boot 
framework for the Backend and a combination of HTML, CSS and JavaScript for the Frontend.

## Concept

The Choonz website allows a vistor to read all of the entities, allows logged in users to CRUD their own playlists and an admin user to CRUD all the entities.

- MUST HAVE - Track, Artist, Album
- SHOULD HAVE - Genre, Playlist
- COULD HAVE - Login system with different authentication depending on type of user

### Client Requirements

#### General
- Multiple users can sign up to the system
- The styling of the entire site should be consistent 
- Users can browse the system without logging in, but won’t be able to CRUD
- It would be nice to be able to search for a specific track, album, or artist
#### User Home Screen
- Users can CRUD albums, artists, tracks and genres
- Users should see cards for each playlist on their home screen
#### Albums
- Users should view each album on its own page
- The album should contain a list of songs
- The albums page should contain a link to the artist page
- Each track should link to a track page
#### Artists
- Users should view each artist on its own page
- The artist should contain a list of albums
- Each album should link to an album page
#### Tracks
- Users should view each track on its own page
- The track page should show the name, lyrics, and genre
- The track page should contain links to the relevant album and artist
#### Genres
- Users should view each genre on its own page
- The genre should contain a list of tracks
- Each track should link to a track page
#### Playlists
- A user can CRUD as many playlists as they like on their home screen
- Users should CRUD their own playlists either by song id, name, or genre
#### Non-Functional Testing
- Response times should be <10 milliseconds
- Latency should be <2 seconds at 10000 concurrent users 
- Throughput rate should be >300/s 
- RAM allocation should be minimal, with few (if any) memory leaks
- The application should be spike-, load-, stress-, and soak-tested
 

## Prerequisites:

To use this application you will need:
- Java Verison 11
- Eclipse or IntelliJ IDE installed
- MySQL Server 8.0+
- Visual Studio Code
- Spring Boot and Maven installed locally

For testing purposes:
- JMeter installed locally 

## Getting Started

Given that you achieve the prerequisites, to set up development you will need to:
1. Clone this repository into an IDE of your choice, preferably Eclipse.
2. Convert the project into a Maven Project (optional depending on how you cloned the repository).
3. Run the project as a Spring Boot Application.
4. The application is hosted as 'localhost:8082/' OR can be run as a live server from the index page using Visual Studio Code.

## Running the Tests

The tests can be ran the IDE, on Eclipsen this can be done by right-clicking on the project and running as a JUnit test. If certain User Acceptance Tests are needed to be ran individually, tags are available in features, which can be added to the cucumber runner so that only that specific test is ran.
Non-functional testing is covered in the src/test/resouces/jmeter. The .jmx files can be opened and ran inside JMeter.

## Test Coverage

TBD

## Authors

### Training Team

- **Client** - [**Angelica Charry**](https://github.com/acharry) - **Software Delivery Manager**
- **Product Owner** - [**Nick Johnson**](https://github.com/nickrstewarttds) - **Initial work (backend & frontend development, specification)**
- **Product Owner** - [**Edward Reynolds**](https://github.com/Edrz-96) - **Initial work (testing, specification)**
- [**Jordan Harrison**](https://github.com/JHarry444) - **General Java wizardry**
- [**Alan Davies**](https://github.com/MorickClive)
- [**Savannah Vaithilingham**](https://github.com/savannahvaith)
- [**Vinesh Ghela**](https://github.com/vineshghela)
- [**Piers Barber**](https://github.com/PCMBarber)

### Development Team

- Team 3
- **Scrum Master** - [**Leaf Cooper**](https://github.com/leaf-cooper-qa)
- [**Marco Castellana**](https://github.com/bulbicx)
- [**Niall Duggan**](https://github.com/nduggan-dev)
- [**David Indiongco**](https://github.com/dindiongco) 

## Acknowledgements

TBD
