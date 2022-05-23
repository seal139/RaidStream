/**
 * 
 */
package io.github.seal139.raidStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Septian Pramana R
 *
 */
public class RaidInputStream extends InputStream {
    protected static final int EOF = 23; // we use ETB as EOF

    protected InputStream[] iStream;

    protected int length;
    protected int eofCount = -1;

    protected int[][] buffer = {
            null, null };

    protected int  readReturnIndex = -1;
    protected byte bufferIndex     = 0;

    protected int readIndex = -1;

    protected int bufferPtr = 1;
    protected int tmpEOFIndexa;

    protected int lengthAlpha;

    protected boolean eof = false;

    public RaidInputStream(InputStream... iStreams) throws IOException {
        this.iStream = iStreams;
        this.length  = iStreams.length;

        if (this.length < 3) {
            throw new IOException("Input Stream cannot be less than 3");
        }

        this.lengthAlpha = this.length - 1;

        this.buffer[0] = new int[this.lengthAlpha];
        this.buffer[1] = new int[this.lengthAlpha];

        // Fill buffer first
        bufferRead();
        bufferRead();
    }

    protected void bufferRead() throws IOException {

        this.bufferPtr ^= 1;

        this.readIndex += 1;
        int skip = this.lengthAlpha - (this.readIndex % this.length);

        int red = 0;
        for (int i = 0; i < this.length; i++) {

            int read = this.iStream[i].read();

            if (i != skip) {
                this.buffer[this.bufferPtr][i - red] = read;
            }
            else {
                red = 1;
            }

            switch (read) {
            case EOF:
                this.eofCount = i - red;
                continue;

            case -1:
                this.eof = true;
                return;
            }
        }

        this.eof = false;
    }

    @Override
    public int read() throws IOException {
        if ((++this.readReturnIndex) == this.lengthAlpha) {
            this.readReturnIndex = 0;

            // Flip flop
            this.bufferIndex ^= 1;

            if (!this.eof) {
                bufferRead();
            }
        }

        if (this.eof) {
            if (--this.eofCount == -1) {
                return -1;
            }
        }

        return this.buffer[this.bufferIndex][this.readReturnIndex];
    }

    /*---------------------------------------------------------*/

    // For random access, this kind of algorithm is straight forward,
    // But for sequential access, this algorithm may be very expensive,
//    protected static boolean isParity(int index, int maxIndex) {
//        int indexer     = (index - getStreamIndex(index, maxIndex)) / maxIndex;
//        int parityPoint = ((indexer + 1) * maxIndex) - (indexer % maxIndex) - 1;
//
//        return index == parityPoint;
//    }

}
