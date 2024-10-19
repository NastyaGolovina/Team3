package UPT_PL.Team_3;

import java.util.Scanner;

public class ProjectHelper {
	/** Static variable Scanner **/
	private static Scanner input = new Scanner(System.in);
	
	
	/**
     * Input string
     * 
     * @param message which will output during the ask
     * @return String
     */
    public static String inputStr(String message) {
        String line;
        System.out.println(message);
        line = input.next();
        while (line.equals("")) {
            System.out.println("Empty string!");
            System.out.println(message);
            line = input.next();
        }
        return line;
    }

    /**
     * Input Double
     * 
     * @param massage which will output during the ask
     * @return double
     */
    public static double inputDouble(String message) {
    	double line;
        System.out.println(message);
        line = input.nextDouble();
        while (line <= 0) {
            System.out.println("Value less or equal 0");
            System.out.println(message);
            line = input.nextDouble();
        }
        return line;
    }
    
    /**
     * Input Integer
     * 
     * @param massage which will output during the ask
     * @return integer
     */
    public static int inputInt(String message) {
        int line;
        System.out.println(message);
        line = input.nextInt();
        while (line < 0) {
            System.out.println("Value less or equal 0");
            System.out.println(message);
            line = input.nextInt();
        }
        return line;
    }
    /**
     * printTypes
     */
    public static void printTypes() {
    	System.out.println("(1) - All countries");
    	System.out.println("(2) - Country");
    	System.out.println("(3) - Product");
    }

    
    /**
     * printTypes
     */
    public static void showMenuInfo() {
    	System.out.println("\n******************************");
    	System.out.println("(0) - Exsit");
    	System.out.println("(1) - Add product");
    	System.out.println("(2) - Add country");
    	System.out.println("(3) - Add transport");
    	System.out.println("(4) - Add logistics site to country");
    	System.out.println("(5) - Add product to country");
    	System.out.println("(6) - Add logistics supply chain");
    	System.out.println("(7) - Calculate supply request");
    	System.out.println("(8) - Calculate logistics route");
    	System.out.println("(9) - Print RouteLines");
    	System.out.println("(10) - Print Products");
    	System.out.println("(11) - Print Logistics Supply Chains");
    	System.out.println("(12) - Print Countries");
    	System.out.println("(13) - Print Transports");
    	System.out.println("\n******************************");
    }
	
}
