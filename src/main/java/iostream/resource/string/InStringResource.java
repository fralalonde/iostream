package iostream.resource.string;

import java.io.IOException;
import java.io.StringReader;

import iostream.CloseChain;
import iostream.flow.CharSource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InStringResource implements CharSource<String> {

    final String str;

    @Override
    public StringReader getReader(CloseChain onClose) throws IOException {
	return onClose.add(new StringReader(str));
    }

    @Override
    public String getResource() {
	return str;
    }


}