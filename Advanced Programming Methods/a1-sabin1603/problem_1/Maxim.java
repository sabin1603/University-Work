package problem_1;

public class Maxim {
    public static void main(String[] args)
    {
        // Checking if length of args array is
        // greater than 0
        if (args.length > 0) {
            double maximm = Double.parseDouble(args[0]);

            for (String val : args) {
                if (Double.parseDouble(val) > maximm){
                    maximm = Double.parseDouble(val);
                }
            }
            System.out.println(maximm);
        }
        else {
            System.out.println("no args");
        }
    }
}
