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
    
// Class<?>[] argTypes = null;
// if (args != null) {
// argTypes = new Class<?>[args.length];
// for (int i = 0; i < args.length; i++) {
// argTypes[i] = args[i].getClass();
// }
// }
//
// Method rezMethod = rez.getClass().getMethod(method.getName(), argTypes);
// if (rezMethod == null) {
// throw new CodeFlowError("Method not implemented : " + method);
// }
        
        Object o = method.invoke(rez, args);
        if (o instanceof Resource) {
            return proxy;
        }
        return o;
    }
    
// Method rezMethod = null;
// M:
// for (Method m : rez.getClass().getMethods()) {
// if (m.getName().equals(method.getName())) {
// if (m.getParameterCount() == method.getParameterCount()) {
// for (int i = 0; i < m.getParameterCount(); i++) {
// if (!m.getParameters()[i].getClass().isAssignableFrom(method.getParameters()[i].getClass())) {
// continue M;
// }
// }
// rezMethod = m;
// }
// }
// }
//
// if (rezMethod == null) {
// throw new NoSuchMethodError(method.toString());
// }

}
