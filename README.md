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
   
   * AccessBookings
   * AccessFlights
   * AccessLocations
   * BookingManager
   * AccessRouteFlights (This will be used in the big user story in it3)
   * RouteManager (This will be used in the big user story in it3)
   
   #### \> objects
   * Booking
   * Flight
   * Location
   * Route
   * User
   * AdjacencyList (Will be used in iteration 3 for connecting flights)
   * MyGraph (Will be used in iteration 3 for connecting flights)
   * Node (Will be used in iteration 3 for connecting flights)
   
   #### \> persistence
   * DataAccessStub
   * DataAccessObject
   * DataAccess
   #### \> presentation
   * bookDetailsFragment
   * CardsAdapter
   * DepartureFragment
   * DestinationFragment
   * FragmentBook
   * FragmentManage
   * FrontPageActivity
   * MainActivity
   * HomePageActivity
   * Messages
   * OrderSummary
   * RouteDetails
   * RouteDetailsAdapter
   * SearchResultsCardsAdapter
   * SearchResults
   * Utils
   * ViewPageAdapter

---

## Private Github Repo:
* https://github.com/ssxsidhu/ACMIS

---
## log.txt
* Located in: /documentation/log.txt

---

## Major Features
Users are able to search for flights by the takeoff and destination location. They are then able to book from those flights returned from the search. This feature is available in the "Book Flights" tab at the bottom of the screen.

Users can also go to the manage flight screen by pressing the "Manage Flights" tab at the bottom. Here, users can look at the details of all of their upcoming flights. The flights here are sorted by their takeoff date and time.

**New Feature added** : Users will be able to book round flights. Users will also be able to book for a number of passengers ie for the family members.

---

*Note*:
1. The flights in the database are within the months of July and August, for ease of marking. Just to make it easier for the marker, here are some of the sample round flights that can be searched for: 
  **This can be tested for using the hsqldb.** 
  1.first flight: Source:Regina Destination:Winnipeg date: 5th august,2022
    return flight: Source:Winnipeg Destination:Regina date: 16th august,2022
    
  2.first flight: Source:Montreal Destination:Toronto date: 23rd July,2022
    return flight: Source:Toronto Destination:Montreal date: 8th august,2022
    
  2.first flight: Source:Calgary Destination:Vancouver date: 16th July,2022
    return flight: Source:Vancouver Destination:Calgary date: 19th august,2022
    
  **This can be tested for using the stubdatabase.**  
  1.first flight: Source:Toronto Destination:Winnipeg date: 8th July,2022
    return flight: Source:WInnipeg Destination:Toronto date: 25th July,2022
    
  2.first flight: Source:Toronto Destination:Calgary date: 1st July,2022
    return flight: Source:Calgary Destination:Toronto date: 12th July,2022
    
  3.first flight: Source:Vancouver Destination:Montreal date: 6th July,2022
    return flight: Source:Montreal Destination:Vancouver date: 18th July,2022
    
2.Changing the stub database to the hsqldb or vice verca can be done in the main class. This is done by      commenting out the database name that is not used. 

3. The MyGraph,Node and AdjacencyList classs in the Objects and the RouteManager in the Business class will be used in the next iteration for connecting flight Big user story.
 

## Android Versions
Our app was tested on the the Nexus 7 emulator on AndoidStudio using with system image 6.0 (Marshmallow, API level 23). We also tested it on the Nexus 7 tablet.
