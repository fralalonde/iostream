package iostream;

import java.io.IOException;
import java.util.function.Consumer;

@FunctionalInterface
public interface OnCloseIOExceptionHandler extends Consumer<IOException> {

}
