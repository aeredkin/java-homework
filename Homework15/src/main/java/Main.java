import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        Calculator cacheProxy = (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(),
                CalculatorImpl.class.getInterfaces(), new CacheInvocationHandler(new CalculatorImpl()));
        System.out.println(cacheProxy.fibonacci(8));
        System.out.println(cacheProxy.fibonacci(9));
        System.out.println(cacheProxy.fibonacci(8));
        System.out.println(cacheProxy.fibonacci(9));
    }
}
