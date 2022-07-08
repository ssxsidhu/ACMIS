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
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.User;


public class DataAccessObject implements DataAccess{
	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4, rs5;

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

//						System.out.println(city);

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
		return null;
	}

	public User getUserObject(String username) {
		return null;
	}

	public Flight getFlightObject(int flightNumber) {
		return null;
	}

	public Booking getBooking(int bookingId) {
		return null;
	}

	public String getUserBookings(User user, ArrayList<Booking> userBookings) {
		return null;
	}

	public Booking removeBooking(int bookingId) {
		return null;
	}





//	public String getStudentSequential(List<Student> studentResult)
//	{
//		Student student;
//		String myID, myStudentName, myAddress;
//		myStudentName = EOF;
//		myAddress = EOF;
//		myID = EOF;
//
//		result = null;
//		try
//		{
//			cmdString = "Select * from Students";
//			rs2 = st1.executeQuery(cmdString);
//			//ResultSetMetaData md2 = rs2.getMetaData();
//		}
//		catch (Exception e)
//		{
//			processSQLError(e);
//		}
//		try
//		{
//			while (rs2.next())
//			{
//				myID = rs2.getString("StudentID");
//				myStudentName = rs2.getString("Name");
//				myAddress = rs2.getString("Address");
//				student = new Student(myID, myStudentName, myAddress);
//				studentResult.add(student);
//			}
//			rs2.close();
//		}
//		catch (Exception e)
//		{
//			result = processSQLError(e);
//		}
//
//		return result;
//	}
//
//	public ArrayList<Student> getStudentRandom(Student newStudent)
//	{
//		Student student;
//		String myID, myStudentName, myAddress;
//		myID = EOF;
//		myStudentName = EOF;
//		myAddress = EOF;
//		students = new ArrayList<Student>();
//		try
//		{
//			cmdString = "Select * from Students where StudentID=" + newStudent.getStudentID();
//			rs3 = st1.executeQuery(cmdString);
//			// ResultSetMetaData md2 = rs3.getMetaData();
//			while (rs3.next())
//			{
//				myID = rs3.getString("StudentID");
//				myStudentName = rs3.getString("Name");
//				myAddress = rs3.getString("Address");
//				student = new Student(myID, myStudentName, myAddress);
//				students.add(student);
//			}
//			rs3.close();
//		} catch (Exception e)
//		{
//			processSQLError(e);
//		}
//		return students;
//	}
//
//	public String insertStudent(Student currentStudent)
//	{
//		String values;
//
//		result = null;
//		try
//		{
//			values = currentStudent.getStudentID()
//			         +", '" +currentStudent.getStudentName()
//			         +"', '" +currentStudent.getStudentAddress()
//			         +"'";
//			cmdString = "Insert into Students " +" Values(" +values +")";
//			//System.out.println(cmdString);
//			updateCount = st1.executeUpdate(cmdString);
//			result = checkWarning(st1, updateCount);
//		}
//		catch (Exception e)
//		{
//			result = processSQLError(e);
//		}
//		return result;
//	}
//
//	public String updateStudent(Student currentStudent)
//	{
//		String values;
//		String where;
//
//		result = null;
//		try
//		{
//			// Should check for empty values and not update them
//			values = "Name='" +currentStudent.getStudentName()
//			         +"', Address='" +currentStudent.getStudentAddress()
//			         +"'";
//			where = "where StudentID=" +currentStudent.getStudentID();
//			cmdString = "Update Students " +" Set " +values +" " +where;
//			//System.out.println(cmdString);
//			updateCount = st1.executeUpdate(cmdString);
//			result = checkWarning(st1, updateCount);
//		}
//		catch (Exception e)
//		{
//			result = processSQLError(e);
//		}
//		return result;
//	}
//
//	public String deleteStudent(Student currentStudent)
//	{
//		String values;
//
//		result = null;
//		try
//		{
//			values = currentStudent.getStudentID();
//			cmdString = "Delete from Students where StudentID=" +values;
//			//System.out.println(cmdString);
//			updateCount = st1.executeUpdate(cmdString);
//			result = checkWarning(st1, updateCount);
//		}
//		catch (Exception e)
//		{
//			result = processSQLError(e);
//		}
//		return result;
//	}
//
//	public String getCourseSequential(List<Course> courseResult)
//	{
//		Course course;
//		String myID, myCourseName;
//		myID = EOF;
//		myCourseName = EOF;
//
//		result = null;
//		try
//		{
//			cmdString = "Select * from Courses";
//			rs5 = st3.executeQuery(cmdString);
//			// ResultSetMetaData md5 = rs5.getMetaData();
//			while (rs5.next())
//			{
//				myID = rs5.getString("CourseID");
//				myCourseName = rs5.getString("Name");
//				course = new Course(myID, myCourseName);
//				courseResult.add(course);
//			}
//			rs5.close();
//		}
//		catch (Exception e)
//		{
//			result = processSQLError(e);
//		}
//		return result;
//	}
//
//	public ArrayList<Course> getCourseRandom(Course newCourse)
//	{
//		Course course;
//		String myID, myCourseName;
//		myID = EOF;
//		myCourseName = EOF;
//		courses = new ArrayList<Course>();
//		try
//		{
//			cmdString = "Select * from Courses where CourseID='" +newCourse.getCourseID() +"'";
//			rs5 = st3.executeQuery(cmdString);
//			// ResultSetMetaData md5 = rs5.getMetaData();
//			while (rs5.next())
//			{
//				myID = rs5.getString("CourseID");
//				myCourseName = rs5.getString("Name");
//				course = new Course(myID, myCourseName);
//				courses.add(course);
//			}
//			rs5.close();
//		}
//		catch (Exception e)
//		{
//			processSQLError(e);
//		}
//		return courses;
//	}
//
//	public String insertCourse(Course currentCourse)
//	{
//		String values;
//
//		result = null;
//		try
//		{
//			values =  "'" +currentCourse.getCourseID()
//			         +"', '" +currentCourse.getCourseName()
//			         +"'";
//			cmdString = "Insert into Courses " +" Values(" +values +")";
//			//System.out.println(cmdString);
//			updateCount = st1.executeUpdate(cmdString);
//			result = checkWarning(st1, updateCount);
//		}
//		catch (Exception e)
//		{
//			result = processSQLError(e);
//		}
//		return result;
//	}
//
//	public String updateCourse(Course currentCourse)
//	{
//		String values;
//		String where;
//
//		result = null;
//		try
//		{
//			// Should check for empty values and not update them
//			values = "Name='" +currentCourse.getCourseName()
//			         +"'";
//			where = "where CourseID='" +currentCourse.getCourseID() +"'";
//			cmdString = "Update Courses " +" Set " +values +" " +where;
//			//System.out.println(cmdString);
//			updateCount = st1.executeUpdate(cmdString);
//			result = checkWarning(st1, updateCount);
//		}
//		catch (Exception e)
//		{
//			result = processSQLError(e);
//		}
//		return result;
//	}
//
//	public String deleteCourse(Course currentCourse)
//	{
//		String values;
//
//		result = null;
//		try
//		{
//			values = currentCourse.getCourseID();
//			cmdString = "Delete from Courses where CourseID='" +values +"'";
//			//System.out.println(cmdString);
//			updateCount = st1.executeUpdate(cmdString);
//			result = checkWarning(st1, updateCount);
//		}
//		catch (Exception e)
//		{
//			result = processSQLError(e);
//		}
//		return result;
//	}
//
//	public ArrayList<SC> getSC(SC newSC)
//	{
//		String myStudentID, myCourseID, myCourseName, myGrade;
//		SC mySC;
//		int counter;
//
//		myStudentID = EOF;
//		myCourseID = EOF;
//		myGrade = EOF;
//		myCourseName = EOF;
//		counter = 0;
//		scs = new ArrayList<SC>();
//		try
//		{
//			cmdString = "Select * from Courses,StudentsCourses where Courses.CourseID=StudentsCourses.CourseID and StudentID=" + newSC.getStudentID();
//			rs4 = st2.executeQuery(cmdString);
//			// ResultSetMetaData md4 = rs4.getMetaData();
//			while (rs4.next())
//			{
//				myStudentID = rs4.getString("StudentID");
//				myCourseID = rs4.getString("CourseID");
//				myGrade = rs4.getString("Grade");
//				myCourseName = rs4.getString("Name");
//				mySC = new SC(myStudentID, myCourseID, "", myCourseName, myGrade);
//				scs.add(mySC);
//				counter++;
//			}
//			rs4.close();
//		}
//		catch (Exception e)
//		{
//			processSQLError(e);
//		}
//		return scs;
//	}
//
//	public ArrayList<SC> getCS(SC newSC)
//	{
//		String myStudentID, myCourseID, myStudentName, myGrade;
//		SC myCS;
//		int counter;
//
//		myStudentID = EOF;
//		myCourseID = EOF;
//		myGrade = EOF;
//		myStudentName = EOF;
//		counter = 0;
//		scs = new ArrayList<SC>();
//		try
//		{
//			cmdString = "Select * from Students,StudentsCourses where Students.StudentID=StudentsCourses.StudentID and CourseID='" +newSC.getCourseID() +"'";
//			rs4 = st2.executeQuery(cmdString);
//			// ResultSetMetaData md4 = rs4.getMetaData();
//			while (rs4.next())
//			{
//				myStudentID = rs4.getString("StudentID");
//				myCourseID = rs4.getString("CourseID");
//				myGrade = rs4.getString("Grade");
//				myStudentName = rs4.getString("Name");
//				myCS = new SC(myStudentID, myCourseID, myStudentName, "", myGrade);
//				scs.add(myCS);
//				counter++;
//			}
//			rs4.close();
//		}
//		catch (Exception e)
//		{
//			processSQLError(e);
//		}
//		return scs;
//	}

	public String checkWarning(Statement st, int updateCount)
	{
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

	public String processSQLError(Exception e)
	{
		String result = "*** SQL Error: " + e.getMessage();

		// Remember, this will NOT be seen by the user!
		e.printStackTrace();

		return result;
	}
}
