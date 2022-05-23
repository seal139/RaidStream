/**
 * 
 */
package io.github.seal139.raidStream;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Septian Pramana R
 *
 */
public class RaidOutputStream extends OutputStream {

    OutputStream[] oStream;
    int            length;

    public RaidOutputStream(OutputStream... oStreams) throws IOException {
        this.oStream = oStreams;
        this.length  = this.oStream.length;

        if (this.length < 3) {
            throw new IOException("Output Stream cannot be less than 3");
        }
    }

    @Override
    public void close() throws IOException {
        // TODO Auto-generated method stub
        super.close();
    }

    @Override
    public void write(int b) throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void flush() throws IOException {
        // TODO Auto-generated method stub
        super.flush();
    }

    // For random access, this kind of algorithm is straight forward,
    // But for sequential access, this algorithm may be very expensive,
    protected static int getStreamIndex(int index, int maxIndex) {
        return index % maxIndex;
    }

    protected static boolean isParity(int index, int maxIndex) {
        int indexer     = (index - getStreamIndex(index, maxIndex)) / maxIndex;
        int parityPoint = ((indexer + 1) * maxIndex) - (indexer % maxIndex) - 1;

        return index == parityPoint;
    }
}
