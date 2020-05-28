package group1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * RectangleReader class reads a file and checks the commands
 * in the file to see what to print in the output file.
 * 
 * @author Kurt Karpin (kkarp9)
 * @author Joshua Mathew (josh827)
 *
 * @version 2020.2.17
 */
public class RectangleReader {
    private RectangleBST<Rectangle> recTree;


    /**
     * Rectangle Reader constructor
     * 
     * @param file
     *            the input file
     * @throws IOException
     */
    public RectangleReader(String file) throws IOException {
        recTree = new RectangleBST<Rectangle>();
        inputReader(file);
    }


    /**
     * Reads the commands of the file and prints out the correct
     * output using the BST for organizing.
     * 
     * @param fileName
     *            name of the file
     * @throws FileNotFoundException
     */
    public void inputReader(String fileName)
        throws FileNotFoundException,
        IOException {
        try {
            // Create our new scanner
            Scanner sc = new Scanner(new File(fileName));

            // While the scanner has information to read
            while (sc.hasNext()) {

                String cmd = sc.next(); // Read the next term
                String name;
                int x;
                int y;
                int width;
                int height;
                switch (cmd) {

                    case "insert": // Found an add command
                        name = sc.next();
                        x = sc.nextInt();
                        y = sc.nextInt();
                        width = sc.nextInt();
                        height = sc.nextInt();

                        Rectangle newRectangle = new Rectangle(name, x, y,
                            width, height);

                        if (!Character.isLetter(name.charAt(0)) || String
                            .valueOf(name).matches("[^a-zA-Z0-9^_]")) {

                            System.out.println("Rectangle rejected: "
                                + newRectangle.toString());
                            break;
                        }

                        if (!recTree.insert(newRectangle)) {
                            System.out.println("Rectangle accepted: "
                                + newRectangle.toString());
                        }

                        else {
                            System.out.println("Rectangle rejected: "
                                + newRectangle.toString());
                        }

                        break;

                    case "regionsearch":// Found a regionsearch command

                        x = sc.nextInt();
                        y = sc.nextInt();
                        width = sc.nextInt();
                        height = sc.nextInt();

                        System.out.print(recTree.regionsearch(x, y, width,
                            height));

                        break;

                    case "intersections": // Found a intersections command
                        System.out.println("Intersection pairs:");
                        String intersects = recTree.intersections();
                        if (!intersects.isEmpty()) {
                            System.out.print(intersects);
                        }
                        break;

                    case "search":

                        name = sc.next();
                        System.out.print(recTree.search(name));
                        break;

                    case "remove":
                        try {
                            x = sc.nextInt();
                        }

                        // catches if the name gets put into an int
                        catch (InputMismatchException e) {
                            String nameRec = sc.next();
                            if (!recTree.remove(nameRec)) {
                                System.out.println("Rectangle rejected "
                                    + nameRec);
                            }
                            break;
                        }
                        y = sc.nextInt();
                        width = sc.nextInt();
                        height = sc.nextInt();
                        if (!recTree.remove(x, y, width, height)) {
                            System.out.println("Rectangle rejected (" + x + ", "
                                + y + ", " + width + ", " + height + ")");
                        }
                        // remove
                        break;

                    case "dump":
                        System.out.println(recTree.dump());
                        // dump
                        break;

                    default:// Found an unrecognized command
                        System.out.println("Unrecognized input " + cmd);
                        break;
                }
            }
            sc.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
