/**
 * This code is not used in the first iteration. It is provided as
 * an example of usage of HSQLDB (for iteration 2).
 */

package comp3350.acmis.persistence;

import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.util.ArrayList;

import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.User;


public class DataAccessObject implements DataAccess {
	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs1, rs2, rs3, rs4;

	private String dbName;
	private String dbType;


	private String cmdString;
	private int updateCount;
	private String result;
	private static String EOF = "  ";

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
	}

	public void open(String dbPath) {
		String url;
		try {
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();
		}
		catch (Exception e) {
			processSQLError(e);
		}
		System.out.println("Opened " +dbType +" database " +dbPath);
	}

	public void close() {
		try {	// commit all changes to the database
			cmdString = "shutdown compact";
			rs1 = st1.executeQuery(cmdString);
			c1.close();
		}
		catch (Exception e) {
			processSQLError(e);
		}
		System.out.println("Closed " + dbType + " database " + dbName);
	}

	public String getAllFlights(ArrayList<Flight> resultList) {
		Flight flight;
		int id, startLocation, endLocation, year, month, day, hour, minute, seats, cost;
		double duration = -1;
		Location source = null, dest = null;
		id = -1;
		startLocation = -1;
		endLocation = -1;
		year = -1;
		month = -1;
		day = -1;
		hour= -1;
		minute = -1;
		seats = -1;
		cost = -1;

		result = null;
		try {
			cmdString = "Select * from Flights";
			rs1 = st1.executeQuery(cmdString);
			while (rs1.next()) {
				id = rs1.getInt("flightID");
				startLocation = rs1.getInt("startLocation");
				endLocation =  rs1.getInt("endLocation");
				year =  rs1.getInt("year");
				month = rs1.getInt("month");
				day = rs1.getInt("day");
				hour = rs1.getInt("hour");
				minute = rs1.getInt("minute");
				seats = rs1.getInt("seats");
				cost = rs1.getInt("cost");
				duration = rs1.getDouble("duration");

				try {
					cmdString = "Select * from Locations where locationID = " + startLocation + " or locationID = " + endLocation;
					rs2 = st2.executeQuery(cmdString);

					while (rs2.next()) {
						String city = rs2.getString("city");
						String country =  rs2.getString("country");
						String airport =  rs2.getString("airport");
						String timezone = rs2.getString("timezone");

						if (rs2.getString("locationID").equals(startLocation + "")) {
							source = new Location(city, ZoneId.of(timezone), country, airport);
						}
						else {
							dest = new Location(city, ZoneId.of(timezone), country, airport);
						}
					}

				} catch (Exception e) {
					result = processSQLError(e);
				}
				rs2.close();

				flight = new Flight(id, source, dest, ZonedDateTime.of(year, month, day, hour, minute, 0, 0, source.getZoneName()), seats, duration, cost );
				resultList.add(flight);
			}
			rs1.close();
		} catch (Exception e) {
			result = processSQLError(e);
		}
		return result;
	}

	public String getLocations(ArrayList<Location> resultList) {
		Location location;
		int id = -1;
		String city, country, airport, timezone;
		city = EOF;
		country = EOF;
		airport = EOF;
		timezone = EOF;

		result = null;
		try {
			cmdString = "Select * from Locations";
			rs1 = st1.executeQuery(cmdString);
			while (rs1.next()) {
				id = rs1.getInt("locationID");
				city = rs1.getString("city");
				country =  rs1.getString("country");
				airport =  rs1.getString("airport");
				timezone = rs1.getString("timezone");

				location = new Location(city, ZoneId.of(timezone), country, airport);
				resultList.add(location);
			}
			rs1.close();
		} catch (Exception e) {
			result = processSQLError(e);
		}
		return result;
	}

	public String addBooking(Booking newBooking) {
		int userID = newBooking.getBooker().getUserID();
		int numPassengers = newBooking.getNumPassengers();
		String routeDepart = newBooking.getRouteDepart().getFlightsCSV();
		String routeReturn = null;

		if (newBooking.checkForReturn()) {
			routeReturn = newBooking.getRouteReturn().getFlightsCSV();
		}

		result = null;
		try {
			cmdString = "Insert into Bookings values (" +
					"NULL" + ", " +
					userID + ", " +
					routeDepart + ", " +
					routeReturn + ", " +
					numPassengers +
					");";

			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);

		} catch (Exception e) {
			processSQLError(e);
		}

		return null;
	}

	public User getUserObject(String username) {
		User user = null;
		User.Gender finalGender = null;
		int userID = -1;
		String firstname, lastname, gender, userName, password, email, phoneNumber;
		firstname = EOF;
		lastname = EOF;
		gender = EOF;
		userName = EOF;
		password = EOF;
		email = EOF;
		phoneNumber = EOF;

		try {
			cmdString = "Select * from Users where username = '" + username + "'";
			rs1 = st1.executeQuery(cmdString);
			while (rs1.next()) {
				userID = rs1.getInt("userID");
				firstname = rs1.getString("firstname");
				lastname = rs1.getString("lastname");
				gender = rs1.getString("gender");
				userName = rs1.getString("username");
				password = "somePassword";
				email = rs1.getString("email");
				phoneNumber = rs1.getString("phoneNumber");

				if (gender.equals("MALE")) {
					finalGender = User.Gender.MALE;
				}
				else if (gender.equals("FEMALE")) {
					finalGender = User.Gender.FEMALE;
				}
				else {
					finalGender = User.Gender.OTHER;
				}

				user = new User(userID, firstname, lastname, finalGender, userName, password, email, phoneNumber);
			}
			rs1.close();
		} catch (Exception e) {
			result = processSQLError(e);
		}

		return user;
	}

	public String getUserBookings(User user, ArrayList<Booking> userBookings) {
		Booking booking;
		Route routeDepart = new Route();
		Route routeReturn = new Route();
		int numPassengers = -1;
		Location source = null, dest = null;
		Flight flight = null;
		String departureFlights = EOF, returnFlights = EOF;

		result = null;
		try {
			cmdString = "Select * from Bookings where userID = " + user.getUserID();
			rs1 = st1.executeQuery(cmdString);
			while (rs1.next()) {
				numPassengers = rs1.getInt("numPassengers");

				departureFlights = rs1.getString("routeDepart");
				String departureList[] = departureFlights.split(",");
				for (int i = 0; i < departureList.length; i++) {
					String currentFlightID = departureList[i];

					try {
						cmdString = "Select * from Flights where flightID = " + currentFlightID;
						rs3 = st2.executeQuery(cmdString);

						while (rs3.next()) {
							int id = rs3.getInt("flightID");
							int startLocation = rs3.getInt("startLocation");
							int endLocation =  rs3.getInt("endLocation");
							int year =  rs3.getInt("year");
							int month = rs3.getInt("month");
							int day = rs3.getInt("day");
							int hour = rs3.getInt("hour");
							int minute = rs3.getInt("minute");
							int seats = rs3.getInt("seats");
							int cost = rs3.getInt("cost");
							double duration = rs3.getDouble("duration");

							try {
								cmdString = "Select * from Locations where locationID = " + startLocation + " or locationID = " + endLocation;
								rs4 = st2.executeQuery(cmdString);

								while (rs4.next()) {
									String city = rs4.getString("city");
									String country =  rs4.getString("country");
									String airport =  rs4.getString("airport");
									String timezone = rs4.getString("timezone");

									if (rs4.getString("locationID").equals(startLocation + "")) {
										source = new Location(city, ZoneId.of(timezone), country, airport);
									}
									else {
										dest = new Location(city, ZoneId.of(timezone), country, airport);
									}
								}

							} catch (Exception e) {
								result = processSQLError(e);
							}
							rs4.close();

							flight = new Flight(id, source, dest, ZonedDateTime.of(year, month, day, hour, minute, 0, 0, source.getZoneName()), seats, duration, cost );
							routeDepart.addToRoute(flight);
						}

					} catch (Exception e) {
						result = processSQLError(e);
					}
					rs3.close();
				}

				returnFlights = rs1.getString("routeReturn");
				if (returnFlights != null) {
					String returnList[] = returnFlights.split(",");
					for (int i = 0; i < departureList.length; i++) {
						String currentFlightID = returnList[i];

						try {
							cmdString = "Select * from Flights where flightID = " + currentFlightID;
							rs3 = st2.executeQuery(cmdString);

							while (rs3.next()) {
								int id = rs3.getInt("flightID");
								int startLocation = rs3.getInt("startLocation");
								int endLocation =  rs3.getInt("endLocation");
								int year =  rs3.getInt("year");
								int month = rs3.getInt("month");
								int day = rs3.getInt("day");
								int hour = rs3.getInt("hour");
								int minute = rs3.getInt("minute");
								int seats = rs3.getInt("seats");
								int cost = rs3.getInt("cost");
								double duration = rs3.getDouble("duration");

								try {
									cmdString = "Select * from Locations where locationID = " + startLocation + " or locationID = " + endLocation;
									rs4 = st2.executeQuery(cmdString);

									while (rs4.next()) {
										String city = rs4.getString("city");
										String country =  rs4.getString("country");
										String airport =  rs4.getString("airport");
										String timezone = rs4.getString("timezone");

										if (rs4.getString("locationID").equals(startLocation + "")) {
											source = new Location(city, ZoneId.of(timezone), country, airport);
										}
										else {
											dest = new Location(city, ZoneId.of(timezone), country, airport);
										}
									}

								} catch (Exception e) {
									result = processSQLError(e);
								}
								rs4.close();

								flight = new Flight(id, source, dest, ZonedDateTime.of(year, month, day, hour, minute, 0, 0, source.getZoneName()), seats, duration, cost );
								routeReturn.addToRoute(flight);
							}

						} catch (Exception e) {
							result = processSQLError(e);
						}
						rs3.close();
					}
				}

				if (routeReturn.getRoute().size() != 0) {
					booking = new Booking(user, routeDepart, routeReturn, numPassengers);
				}
				else {
					booking = new Booking(user, routeDepart, null, numPassengers);
				}

				userBookings.add(booking);
				routeDepart = new Route();
				routeReturn = new Route();
			}
			rs1.close();
		} catch (Exception e) {
			result = processSQLError(e);
		}
		return result;
	}

	public String checkWarning(Statement st, int updateCount) {
		String result = null;
		try {
			SQLWarning warning = st.getWarnings();
			if (warning != null) {
				result = warning.getMessage();
			}
		}
		catch (Exception e) {
			result = processSQLError(e);
		}
		if (updateCount != 1) {
			result = "Tuple not inserted correctly.";
		}
		return result;
	}

	public String processSQLError(Exception e) {
		String result = "*** SQL Error: " + e.getMessage();

		// Remember, this will NOT be seen by the user!
		e.printStackTrace();

		return result;
	}
}
