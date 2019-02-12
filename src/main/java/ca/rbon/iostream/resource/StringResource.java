package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import ca.rbon.iostream.CodeFlowError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>
 * StringPicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class StringResource extends Resource<String> {

    /** Constant <code>DEFAULT_CAPACITY=-1</code> */
    public static final int DEFAULT_CAPACITY = -1;

    private static final String NO_STRING_SET = "No string was provided for this operation.";

    final String str;

    final int capacity;

    StringWriter writer;

    /** {@inheritDoc} */
    @Override
    public String getResource() {
        if (writer != null) {
            return writer.toString();
        }
        if (str == null) {
            throw new CodeFlowError(NO_STRING_SET);
        }
        return str;
    }

    /** {@inheritDoc} */
    @Override
    protected Reader getReader() throws IOException {
        if (str == null) {
            throw new CodeFlowError(NO_STRING_SET);
        }
        return new StringReader(str);
    }

    /** {@inheritDoc} */
    @Override
    protected Writer getWriter() throws IOException {
        if (str != null) {
            writer = capacity > DEFAULT_CAPACITY
                    ? new StringWriter(str.length() + capacity)
                    : new StringWriter(str.length());
            writer.write(str);

        } else {
            writer = capacity > DEFAULT_CAPACITY
                    ? new StringWriter(capacity)
                    : new StringWriter();
        }
        return writer;
    }

}
