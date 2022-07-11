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


public class DataAccessObject implements DataAccess{
	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4;

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

	public void open(String dbPath)
	{
		String url;
		try
		{
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Opened " +dbType +" database " +dbPath);
	}

	public void close()
	{
		try
		{	// commit all changes to the database
			cmdString = "shutdown compact";
			rs2 = st1.executeQuery(cmdString);
			c1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
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
			rs2 = st1.executeQuery(cmdString);
			while (rs2.next()) {
				id = rs2.getInt("flightID");
				startLocation = rs2.getInt("startLocation");
				endLocation =  rs2.getInt("endLocation");
				year =  rs2.getInt("year");
				month = rs2.getInt("month");
				day = rs2.getInt("day");
				hour = rs2.getInt("hour");
				minute = rs2.getInt("minute");
				seats = rs2.getInt("seats");
				cost = rs2.getInt("cost");
				duration = rs2.getDouble("duration");

				try {
					cmdString = "Select * from Locations where locationID = " + startLocation + " or locationID = " + endLocation;
					rs3 = st2.executeQuery(cmdString);

					while (rs3.next()) {
						String city = rs3.getString("city");
						String country =  rs3.getString("country");
						String airport =  rs3.getString("airport");
						String timezone = rs3.getString("timezone");

						if (rs3.getString("locationID").equals(startLocation + "")) {
							source = new Location(city, ZoneId.of(timezone), country, airport);
						}
						else {
							dest = new Location(city, ZoneId.of(timezone), country, airport);
						}
					}

				} catch (Exception e) {
					result = processSQLError(e);
				}
				rs3.close();

				flight = new Flight(id, source, dest, ZonedDateTime.of(year, month, day, hour, minute, 0, 0, source.getZoneName()), seats, duration, cost );
				resultList.add(flight);
			}
			rs2.close();
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
			rs2 = st1.executeQuery(cmdString);
			while (rs2.next()) {
				id = rs2.getInt("locationID");
				city = rs2.getString("city");
				country =  rs2.getString("country");
				airport =  rs2.getString("airport");
				timezone = rs2.getString("timezone");

				location = new Location(city, ZoneId.of(timezone), country, airport);
				resultList.add(location);
			}
			rs2.close();
		} catch (Exception e) {
			result = processSQLError(e);
		}
		return result;
	}






	public String addBooking(Booking newBooking) {
		int userID = newBooking.getBooker().getUserID();
		String route = newBooking.getRouteDepart().getFlightsCSV();

		result = null;
		try {
			cmdString = "Insert into Bookings values (NULL," + userID + "," + route +");";
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
			rs2 = st1.executeQuery(cmdString);
			while (rs2.next()) {
				userID = rs2.getInt("userID");
				firstname = rs2.getString("firstname");
				lastname = rs2.getString("lastname");
				gender = rs2.getString("gender");
				userName = rs2.getString("username");
				password = "somePassword";
				email = rs2.getString("email");
				phoneNumber = rs2.getString("phoneNumber");

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
			rs2.close();
		} catch (Exception e) {
			result = processSQLError(e);
		}

		return user;
	}


	//TODO currently only does single flight routes
	public String getUserBookings(User user, ArrayList<Booking> userBookings) {
		Booking booking;
		Route route = new Route();
		Location source = null, dest = null;
		Flight flight = null;
		String flightList = EOF;

		result = null;
		try {
			cmdString = "Select * from Bookings where userID = " + user.getUserID();
			rs2 = st1.executeQuery(cmdString);
			while (rs2.next()) {
				flightList = rs2.getString("route");

				try {
					cmdString = "Select * from Flights where flightID = " + flightList;
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
					}

				} catch (Exception e) {
					result = processSQLError(e);
				}
				rs3.close();

				route = new Route(flight);

				booking = new Booking(user, route);
				userBookings.add(booking);
			}
			rs2.close();
		} catch (Exception e) {
			result = processSQLError(e);
		}
		return result;
	}






	public String checkWarning(Statement st, int updateCount) {
		String result;

		result = null;
		try
		{
			SQLWarning warning = st.getWarnings();
			if (warning != null)
			{
				result = warning.getMessage();
			}
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		if (updateCount != 1)
		{
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
