package ar.edu.itba.sia.group3;

public enum CellType {
    FREE(" "), WALL("W"), GOAL("G");

    String name;

    CellType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
