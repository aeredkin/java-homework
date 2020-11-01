public interface Calculator {
    @Cacheable
    int fibonacci(int n);
}
