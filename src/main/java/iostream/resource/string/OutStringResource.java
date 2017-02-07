package iostream.resource.string;

import java.io.IOException;
import java.io.StringWriter;

import iostream.CloseChain;
import iostream.flow.CharSink;

public class OutStringResource implements CharSink<String> {
    
    private StringWriter wr;
    
    @Override
    public StringWriter getWriter(CloseChain onClose) throws IOException {
	wr = new StringWriter();
	return onClose.add(wr);
    }

    @Override
    public String getResource() {
	return wr.toString();
    }

}