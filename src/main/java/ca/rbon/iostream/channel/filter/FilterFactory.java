package ca.rbon.iostream.channel.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The filter factory interface.
 *
 * @author fralalonde
 */
public interface FilterFactory {

    /**
     * @param input the input stream to filter
     * @return the filtered input stream
     * @throws IOException if the filter could not be inserted
     */
    InputStream filterInput(InputStream input) throws IOException;

    /**
     * @param output the output stream to filter
     * @return the filtered output stream
     * @throws IOException if the filter could not be inserted
     */
    OutputStream filterOutput(OutputStream output) throws IOException;

}
