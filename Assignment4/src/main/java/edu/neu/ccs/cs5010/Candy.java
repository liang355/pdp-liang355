package edu.neu.ccs.cs5010;

/**
 * A class representation of a candy, which encodes
 * candy size and candy type as fields
 */
public class Candy {
    private String size;
    private String type;

    public Candy(String size, String type) {
        this.size = size;
        this.type = type;
    }

    /**
     * @return the size of the candy
     */
    public String getSize() {
        return size;
    }

    /**
     * @return the type of the candy
     */
    public String getType() {
        return type;
    }

    /**
     * @return the formatted String representation of Candy object
     */
    @Override
    public String toString() {
        return "["+ size + ", " + type + "]";
    }
}
