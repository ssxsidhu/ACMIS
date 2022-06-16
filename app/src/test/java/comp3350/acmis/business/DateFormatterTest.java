package comp3350.acmis.business;


import junit.framework.TestCase;
import org.junit.Test;

public class DateFormatterTest extends TestCase {

    @Test
    public void testStringErrors() {
        DateFormatter date;

        //null
        try {
            date = new DateFormatter(null);
            fail("Expected a NullPointerException");
        }catch (NullPointerException unused){}

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
    }


    @Test
    public void testProperCharacters() {
        DateFormatter date;

        //dash in not in index 4
        try {
            date = new DateFormatter("121-843-86");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}

        //dash in not in index 7
        try {
            date = new DateFormatter("1-11153-54");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}

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
    }

    @Test
    public void testProperDates() {
        DateFormatter date;

        //zeroes as dates
        try{
            date = new DateFormatter("2022-00-07");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}
        try{
            date = new DateFormatter("2022-07-00");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}
        try{
            date = new DateFormatter("0000-07-00");
            fail("Expected a IllegalArgumentException");
        }catch (IllegalArgumentException unused){}


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
    }


    @Test
    public void testFormat() {
        DateFormatter date = new DateFormatter("2022-06-16");
        assertEquals("June 16, 2022", date.format());

        date = new DateFormatter("2024-02-29");
        assertEquals("February 29, 2024", date.format());
    }
}
