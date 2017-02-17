package ca.rbon.iostream;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import ca.rbon.iostream.resource.Resource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Handler implements InvocationHandler {
    
    final Resource<?> rez;
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(rez, args);
    }
    
}
