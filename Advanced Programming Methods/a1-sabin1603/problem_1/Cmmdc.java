package problem_1;

import javax.swing.*;

public class Cmmdc {
    // Function to return gcd of a and b
    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // Function to find gcd of array of
    // numbers
    static int findGCD(int arr[], int n)
    {
        int result = arr[0];
        for (int element: arr){
            result = gcd(result, element);

            if(result == 1)
            {
                return 1;
            }
        }

        return result;
    }

    public static void main(String[] args)
    {
        // Checking if length of args array is
        // greater than 0
        if (args.length > 0) {

            int size = args.length;
            int [] arr = new int [size];
            for (int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(args[i]);
            }
            System.out.println(findGCD(arr, size));
        }
        else {
            System.out.println("no args");
        }
    }

}
