public class CalculatorImpl implements Calculator {
    @Cacheable
    @Override
    public int fibonacci(int n) {
        int n0;
        int n1 = 0;
        int n2 = 1;
        for (int i = 1; i < n; ++i) {
            n0 = n1;
            n1 = n2;
            n2 = n0 + n1;
        }
        return n2;
    }
}
