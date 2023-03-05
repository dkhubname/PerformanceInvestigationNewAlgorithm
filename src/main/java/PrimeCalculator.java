import java.util.*;

public class PrimeCalculator {
    public static void main(String[] args)  {

        int value = 0;
        try {
            value = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException ex)
        {}

        if (value<1)
        {
            System.out.println("Please enter a number greater 0 and less than or equal to " + Integer.MAX_VALUE);
        }
        else
        {
            printPrimes(value);
        }


    }


    public static void   printPrimes(int length)  {
        // using Atkin algorithm
        System.out.println("Please wait, program is working");

        Boolean isMaxPrimeMaxInt = false;
        if (length == Integer.MAX_VALUE) {
            isMaxPrimeMaxInt = true;
            length--;
        }

        BitSet sieve = new BitSet();
        for (long x2 = 1L, dx2 = 3L; x2 < length; x2 += dx2, dx2 += 2L)
            for (long y2 = 1L, dy2 = 3L, n; y2 < length; y2 += dy2, dy2 += 2L) {
                // n = 4x² + y²
                n = (x2 << 2L) + y2;
                if (n <= length && (n % 12L == 1L || n % 12L == 5L))
                    sieve.flip((int)n);
                // n = 3x² + y²
                n -= x2;
                if (n <= length && n % 12L == 7L)
                    sieve.flip((int)n);
                // n = 3x² - y² (при x > y)
                if (x2 > y2) {
                    n -= y2 << 1L;
                    if (n <= length && n % 12L == 11L)
                        sieve.flip((int)n);
                }
            }
        int r = 5;
        for (long r2 = r * r, dr2 = (r << 1L) + 1L; r2 < length; ++r, r2 += dr2, dr2 += 2L)
            if (sieve.get(r))
                for (long mr2 = r2; mr2 < length; mr2 += r2)
                    sieve.set((int)mr2, false);
        if (length >= 2)
            sieve.set(2, true);
        if (length >= 3)
            sieve.set(3, true);

        for (int i = 2; i <= length; i++) {
            if (sieve.get(i)) {
                System.out.println(i);
            }
        }
        if (isMaxPrimeMaxInt) {
            System.out.println(Integer.MAX_VALUE);
        }

        System.out.println("Program finished");
    }
}