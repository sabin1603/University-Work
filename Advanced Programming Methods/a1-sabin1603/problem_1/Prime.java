package problem_1;

public class Prime {
    public static boolean isPrime(int x) {
        boolean flag = false;
        if (x == 1 || x == 0)
            return false;
        for (int i = 2; i <= x / 2; ++i) {
            // condition for nonprime number
            if (x % i == 0) {
                flag = true;
                break;
            }
        }

        if (!flag)
            return true;
        return false;
    }

    public static void main(String[] args)
    {
        // Checking if length of args array is
        // greater than 0
        if (args.length > 0) {


            for (String val : args) {
                if (isPrime(Integer.parseInt(val))) {
                    System.out.println(val);
                }
            }
        }
        else {
            System.out.println("no args");
        }
    }
}
