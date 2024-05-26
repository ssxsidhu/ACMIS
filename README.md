# Airline and Client Management Information System (ACMIS)
## Group members
Bileski Malcolm, Braden  
Asif, Mahamud Hasan  
Iyer, Prahalad  
Patel, Vrushil Kiritkumar  
Sidhu, Sahilpreet Singh  

---

## Source Code Files:
   ### comp3350.acmis

   #### \> business
   
   * **AccessBookings** - Brings Bookings from persistence into business
   * **AccessLocations** - Brings Locations from persistence into business
   * **AccessRoutes** - Brings Routes from persistence into business
   * **BookingManager** - Creates and cancels Bookings
   * **RouteManager** - Creates a route from a departure location to a destination location. Includes direct and connectint flights
   * **SortRoutes** - Sorts the routes in multiple ways
   * **UseRouteFlights** - Used to access flights in a particular route 
   
   #### \> objects
   * **AdjacencyList** - Used for implementing the Graph. Stores list of locations
   * **Booking** - A Booking connects a User to their Flight
   * **Flight** - A single flight from one location to another
   * **Location** - A location with an airport
   * **MyGraph** - Used to find connecting flights
   * **Node** - Stores a location
   * **Route** - A list of connecting flights
   * **User** - User that uses the app
   
   #### \> persistence
   * **DataAccessStub** - Stub database implementation
   * **DataAccessObject** - HSQLDB implementation
   * **DataAccess** - Interface for Database classes 
   #### \> presentation
   * **BookDetailsFragment** - Contains the details of flight the user wants, such as date, number of passengers, and search button
   * **DepartureFragment** - Shows textbox where the User can choose the departure location
   * **DestinationFragment** - Shows textbox where the User can choose the destination location
   * **FragmentBook** - Contains the above three
   * **FragmentManage** - Shows list of upcoming flights which the user has booked
   * **ManageCardsAdapter** - Adapter to show the list of Bookings
   * **OrderSummary** - Shows the flights which the user has selected to book
   * **RouteDetails** - Shows the details of the Route such as where the flight is stopping
   * **RouteDetailsAdapter** - Adapter to show the list of stops and their details
   * **SearchResults** - Shows a list of search results from the depature location to the destination location
   * **SearchResultsCardsAdapter** - Adapter to show the list of search results and breif details
   * **SortFragment** - Shows a list of options can select to sort the flights from search result
   * **FrontPageActivity** - Shows the starting animation on app launch
   * **MainActivity** - Starts BookFragment and ManageFragment
   * **Messages** - Gives feedback to user
   * **Utils** - Contains function to set the colour of statuc bar and to get a formatted date
   * **ViewPageAdapter** - Adapter for bottom navigation tabs

---

## log.txt
* Located in: /documentation/log.txt

---

## Major Features
Users are able to search for flights by the takeoff and destination location. They are then able to book from those flights returned from the search. This feature is available in the "Book Flights" tab at the bottom of the screen.

Users can also go to the manage flight screen by pressing the "Manage Flights" tab at the bottom. Here, users can look at the details of all of their upcoming flights. The flights here are sorted by their takeoff date and time.

Users will be able to book round flights. Users will also be able to book for a number of passengers ie for the family members.

**New Feature Added:** Added connecting flights. Thus is a use wants to fly A->C, they are now also shown flights A->B->C. Added cancel booking feature. Users can cancel their already booked flights by pressing on the specific flight in the Manage Flights tab, then press Cancel Booking.

---

## Testing Information
The testing is divided into unit tests, integration tests and acceptance tests. Every package has its own test suite that allows the marker to run tests for individual layers of the system. A file called "RunUnitTests" can be used to run all the unit tests for single components as well as accessMehthods. "RunIntegrationTests" can be used to run the tests for the different seams.

---

## Android Versions
Our app was tested on the the Nexus 7 emulator on AndoidStudio using with system image 6.0 (Marshmallow, API level 23). We also tested it on the Nexus 7 tablet.
