package edu.neu.ccs.cs5010;

public class Candy {
    private String size;
    private String type;

    public Candy(String size, String type) {
        this.size = size;
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public boolean equalsTo(Candy c) {
        return this.type.equals(c.type) && this.size.equals(c.size);
    }

    @Override
    public String toString() {
        return "["+ size + ", " + type + "]";
    }
}
