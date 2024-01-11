package org.spigotmc;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.nbt.NbtAccounter;

public class LimitStream extends FilterInputStream
{

    private final NbtAccounter limit;

    public LimitStream(InputStream is, NbtAccounter limit)
    {
        super( is );
        this.limit = limit;
    }

    @Override
    public int read() throws IOException
    {
        limit.m_128926_( 1 );
        return super.read();
    }

    @Override
    public int read(byte[] b) throws IOException
    {
        limit.m_128926_( b.length );
        return super.read( b );
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException
    {
        limit.m_128926_( len );
        return super.read( b, off, len );
    }
}
