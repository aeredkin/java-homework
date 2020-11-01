import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CacheInvocationHandler implements InvocationHandler {
    private final Calculator calculator;
    Database database;

    CacheInvocationHandler(Calculator calculator) {
        this.calculator = calculator;
        database = new Database();
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (!method.isAnnotationPresent(Cacheable.class)) {
            return method.invoke(calculator, objects);
        }
        int key = (Integer) objects[0];
        Object result = database.load(key);
        if ((Integer) result == 0) {
            result = method.invoke(calculator, objects);
            database.save(key, (Integer) result);
        }
        return result;
    }
}
