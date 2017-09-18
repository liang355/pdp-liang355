/**
 * This is part of Assignment 1: Environment Setup and Review of Java for PDP, Fall 2017.
 */
package Assignment1;

/**
 * Vehicle is a simple object that has a velocity and a direction.
 */
// You may not make Ball implement the Comparable interface.
public class Vehicle {

    private double velocity;
    private int direction;

    /**
     * Illegal constructor that creates a new vehicle object.
     */
    public Vehicle() {
        throw new IllegalArgumentException("unexpected number of arguments are provided (expecting two arguments)");
    }

    /**
     * Illegal constructor that creates a new vehicle object.
     */
    public Vehicle(double velocity) {
        throw new IllegalArgumentException("unexpected number of arguments are provided (expecting two arguments)");
    }

    /**
     * Illegal constructor that creates a new vehicle object.
     */
    public Vehicle(String velocity, String directiont) {
        throw new IllegalArgumentException("illegal format of arguments");
    }

    /**
     * Constructor that creates a new vehicle object with the specified velocity and direction.
     * @param velocity Velocity of the new object.
     * @param direction Direction of the new object, where 1 represents eastbound direction, and 2 westbound direction.
     */
    public Vehicle(double velocity, int direction) {
        if(direction != 1 && direction != 2) {
            throw new IllegalArgumentException("direction argument " + direction + " is neither 1 nor 2");
        }
        this.velocity = velocity;
        this.direction = direction;
    }

    /**
     * Returns the velocity of the Vehicle.
     * @return the velocity of the Vehicle.
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * Returns the direction of the Vehicle.
     * @return the direction of the Vehicle.
     */
    public double getDirection() {
        return direction;
    }
}