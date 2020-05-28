package group1;

/**
 * This class is the Rectangle object class where it stores its x and y
 * coordinates along with its width and height.
 * 
 * @author Kurt Karpin (kkarp9)
 * @author Joshua Mathew (josh827)
 *
 * @version 2020.2.17
 */
public class Rectangle {
    private String name; // name of the rectangle
    private int xCoordinate; // xCoordinate of the rectangle
    private int yCoordinate; // yCoordinate of the rectangle
    private int width; // width of the rectangle
    private int height; // height of the rectangle
    private int xMax; // max x point for the rectangle placement
    private int yMax; // max y point for the rectangle placement


    /**
     * Rectangle constructor that creates the rectangle object.
     * Here we assume the rectangle will be placed in
     * the "world box" with its top left edge corresponding to the x and y
     * coordinate in the parameters.
     * 
     * @param name
     *            the name of the rectangle to be placed
     * @param x
     *            the x coordinate for the rectangle to be placed
     * @param y
     *            the y coordinate for the rectangle to be placed
     * @param w
     *            the width of the rectangle
     * @param h
     *            the height of the rectangle
     */
    public Rectangle(String name, int x, int y, int w, int h) {
        this.name = name;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.width = w;
        this.height = h;
        xMax = x + w;
        yMax = y + h;
    }


    /**
     * Getter method for the max x coordinate of a rectangle
     * 
     * @return int the maxX
     */
    public int getXMax() {
        return this.xMax;
    }


    /**
     * Getter method for the max y coordinate of a rectangle
     * 
     * @return int the maxY
     */
    public int getYMax() {
        return this.yMax;
    }


    /**
     * Getter method to return the name of this rectangle
     * 
     * @return the name of the rectangle
     */
    public String getName() {
        return this.name;
    }


    /**
     * Getter method to return the x Coordinate
     * 
     * @return the x coordinate of this rectangle
     */
    public int getXCoordinate() {
        return this.xCoordinate;
    }


    /**
     * Getter method to return the y Coordinate
     * 
     * @return the y coordinate of this rectangle
     */
    public int getYCoordinate() {
        return this.yCoordinate;
    }


    /**
     * Getter method to return the width
     * 
     * @return the width of this rectangle
     */
    public int getWidth() {
        return this.width;
    }


    /**
     * Compares one rectangle with another
     * to return values based on comparing the
     * coordinates and the size. Note we exclude
     * the name comparison because we can
     * just use the string version if we wish to compare names.
     * 
     * @param comp
     *            the other rectangle
     * @param compareName
     *            checks the names of the rectangle
     * @return 0 if all parameters match -1 or 1
     *         depending which one is bigger in
     *         parameter or alphabetically in order
     */
    public int compareTo(Rectangle comp, boolean compareName) {
        if (compareName) {
            if (name.compareTo(comp.getName()) < 0) {
                return -1;
            }
            else if (name.compareTo(comp.getName()) > 0) {
                return 1;
            }
        }
        if (xCoordinate < comp.xCoordinate) {
            return -1;
        }
        else if (xCoordinate > comp.xCoordinate) {
            return 1;
        }
        else if (yCoordinate < comp.yCoordinate) {
            return -1;
        }
        else if (yCoordinate > comp.yCoordinate) {
            return 1;
        }
        else if (width < comp.width) {
            return -1;
        }
        else if (width > comp.width) {
            return 1;
        }
        else if (height < comp.height) {
            return -1;
        }
        else if (height > comp.height) {
            return 1;
        }
        return 0;
    }


    /**
     * checks if a rectangle is in the region of the other paramaters of a
     * rectangle
     * 
     * @param recRegion
     *            rectangle
     * @return boolean true or not
     */
    public boolean inRange(Rectangle recRegion) {

        // compares the xCoordinate, xMax coordinate, yCoordinate, and yMax
        // coordinate
        // of the two different rectangles to make sure no areas overlap with
        // the other
        return ((this.getXCoordinate() < recRegion.getXCoordinate() || this
            .getXCoordinate() < recRegion.getXMax()) && (this
                .getXMax() > recRegion.getXCoordinate() || this
                    .getXMax() > recRegion.getXMax()) && (this
                        .getYCoordinate() < recRegion.getYCoordinate() || this
                            .getYCoordinate() < recRegion.getYMax()) && (this
                                .getYMax() > recRegion.getYCoordinate() || this
                                    .getYMax() > recRegion.getYMax()));
    }


    /**
     * Getter method to return the height
     * 
     * @return the height of this rectangle
     */
    public int getHeight() {
        return this.height;
    }


    /**
     * Returns a String version of the Rectangle
     * in this format: Name: *name*, (x,
     * y), Width: *w*, Height: *h*
     * 
     * @param rectangle
     * @return String result
     */
    @Override
    public String toString() {
        //uses a String Builder to return the rectangle's parameters 
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(getName());
        builder.append(", ");
        builder.append(Integer.toString(getXCoordinate()));
        builder.append(", ");
        builder.append(Integer.toString(getYCoordinate()));
        builder.append(", ");
        builder.append(Integer.toString(getWidth()));
        builder.append(", ");
        builder.append(Integer.toString(getHeight()));
        builder.append(")");
        return builder.toString();
    }
}
