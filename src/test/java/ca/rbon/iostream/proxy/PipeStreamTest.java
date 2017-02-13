package ca.rbon.iostream.proxy;

public class PipeStreamTest {
    
//    @Test
//    @Ignore
//    public void pipeStreams() throws IOException {
//                
//        try (ObjectInputOf<PipedInputStream> pis = IoStream.pipeInput().objectInputStream()) {
//
//            ObjectOutputOf<PipedOutputStream> zos = IoStream.pipeOutput(pis.getResource()).objectOutputStream();
//            try {
//                zos.writeBytes("AA");
//                assertThat(zos.closer.getLinks().get(0)).isInstanceOf(ByteArrayOutputStream.class);
//                assertThat(zos.closer.getLinks().size()).isEqualTo(1);
//            } finally {
//                zos.close();
//            }
//            
//            
//            byte[] bytes = IOUtils.readFully(pis, 2);
//            assertThat(new String(bytes)).isEqualTo("AA");
//        }
//    }   
    
}
