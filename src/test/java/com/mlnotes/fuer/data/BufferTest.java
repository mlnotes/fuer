package com.mlnotes.fuer.data;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class BufferTest {
    private Buffer buffer;
    
    @Before
    public void setUp() {
        buffer = new Buffer(1024);
    }

    @Test
    public void testChar() throws IOException {
        char c = 'a';
        buffer.writeChar(c);
        assertEquals('a', buffer.readChar());
    }
    
    @Test
    public void testShort() throws IOException {
        short v1 = 19;
        short v2 = -20;
        buffer.writeShort(v1);
        buffer.writeShort(v2);
        assertEquals(v1, buffer.readShort());
        assertEquals(v2, buffer.readShort());
    }
    
    @Test
    public void testInt() throws IOException {
        int v1 = -100;
        buffer.writeInt(v1);
        assertEquals(v1, buffer.readInt());
    }
    
    @Test
    public void testLong() throws IOException {
        long v1 = -100;
        buffer.writeLong(v1);
        assertEquals(v1, buffer.readLong());
    }
}
