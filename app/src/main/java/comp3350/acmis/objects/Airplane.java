package comp3350.acmis.objects;

public class Airplane {

    private String planeName, mealType;
    private int seats,range;
    private float speed;
    private boolean accessibility;


    //default constructor
    public Airplane(){
        planeName="";
        mealType="";
        seats = 0;
        range = 0;
        speed = 0;
        accessibility = false;
    }

    //input constructor
    public Airplane(String name, int numSeats, int rangeVal, boolean checkAccessibility, String typeOfMeal){
        planeName  = name;
        seats = numSeats;
        range = rangeVal;
        accessibility = checkAccessibility;
        mealType = typeOfMeal;
    }

    public String getPlaneName() {
        return planeName;
    }

    public String getMealType() {
        return mealType;
    }

    public int getSeats() {
        return seats;
    }

    public int getRange() {
        return range;
    }

    public boolean isAccessibility() {
        return accessibility;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "planeName='" + planeName + '\'' +
                ", mealType='" + mealType + '\'' +
                ", seats=" + seats +
                ", range=" + range +
                ", speed=" + speed +
                ", accessibility=" + accessibility +
                '}';
    }
}

