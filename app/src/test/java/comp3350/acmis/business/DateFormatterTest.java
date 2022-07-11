package comp3350.acmis.business;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateFormatterTest extends TestCase {

    @Test
    public void testNullDate() {
        System.out.println("Starting testDateFormatter: null date");

        DateFormatter date;

        //null
        try {
            date = new DateFormatter(null);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {}

        System.out.println("Finished testDateFormatter: null date");
    }

    @Test
    public void testStringSize() {
        System.out.println("Starting testDateFormatter: string size");

        DateFormatter date;

        //too long
        try {
            date = new DateFormatter("834HOFHinsdigniksd83458");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}

        //too short
        try {
            date = new DateFormatter("");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}

        System.out.println("Finished testDateFormatter: string size");
    }


    @Test
    public void testDashes() {
        System.out.println("Starting testDateFormatter: dashes");

        DateFormatter date;

        //dash in not in index 4
        try {
            date = new DateFormatter("121-843-86");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        //dash in not in index 7
        try {
            date = new DateFormatter("1-11153-54");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        System.out.println("Finished testDateFormatter: dashes");
    }

    @Test
    public void testValidCharacters() {
        System.out.println("Starting testDateFormatter: valid characters");

        DateFormatter date;

        try {
            date = new DateFormatter("a111-22-33");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}
        try {
            date = new DateFormatter("1%11-22-33");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}
        try {
            date = new DateFormatter("11+1-22-33");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}
        try {
            date = new DateFormatter("111--22-33");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}
        try {
            date = new DateFormatter("1111-?2-33");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}
        try {
            date = new DateFormatter("1111-2K-33");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}
        try {
            date = new DateFormatter("1111-22->3");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}
        try {
            date = new DateFormatter("1111-22-3;");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}

        System.out.println("Finished testDateFormatter: valid characters");
    }

    @Test
    public void testZeroDates() {
        System.out.println("Starting testDateFormatter: zero dates");

        DateFormatter date;

        //zeroes as dates
        try {
            date = new DateFormatter("2022-00-07");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}
        try {
            date = new DateFormatter("2022-07-00");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}
        try {
            date = new DateFormatter("0000-07-00");
            fail("Expected a IllegalArgumentException");
        } catch (IllegalArgumentException unused) {}

        System.out.println("Finished testDateFormatter: zero dates");

    }

    @Test
    public void testValidMonthDay() {
        System.out.println("Starting testDateFormatter: valid month/day");

        DateFormatter date;

        //month and day too high
        try{
            date = new DateFormatter("2022-07-50");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}
        try{
            date = new DateFormatter("2022-50-20");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}

        //test non-leap year
        try{
            date = new DateFormatter("2022-02-29");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}

        System.out.println("Finished testDateFormatter: valid month/day");

    }


    @Test
    public void testFormat() {
        System.out.println("Starting testDateFormatter: format");


        DateFormatter date = new DateFormatter("2022-06-16");
        assertEquals("June 16, 2022", date.format());

        date = new DateFormatter("2024-02-29");
        assertEquals("February 29, 2024", date.format());

        System.out.println("Finished testDateFormatter: format");

    }

    public static class RouteManager extends TestCase {

        @Before
        public void setup() {

        }

        @After
        public void tear() {

        }

        @Test
        public void testSearchRouteDirect() {

        }

        @Test
        public void testSearchRouteConnected() {

        }
    }
}
