package dev.lonami.klooni.serializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class BinSerializer {

    // ascii (klooni) and binary (1010b)
    private final static byte[] HEADER = {0x6B, 0x6C, 0x6F, 0x6F, 0x6E, 0x69, 0xa};

    // MODIFY THIS VALUE EVERY TIME A BinSerializable IMPLEMENTATION CHANGES
    // Or unwanted results will happen and corrupt the game in an unknown way.
    private final static int VERSION = 2;

    public static void serialize(final BinSerializable serializable, final OutputStream output)
            throws IOException {
        try (DataOutputStream outputStream = new DataOutputStream(output)) {
            outputStream.write(HEADER);
            outputStream.writeInt(VERSION);
            serializable.write(outputStream);
        }
    }

    public static void deserialize(final BinSerializable serializable, final InputStream input)
            throws IOException {
        try (DataInputStream inputStream = new DataInputStream(input)) {
            // Read the HEADER and the VERSION (checks)
            byte[] savedBuffer = new byte[HEADER.length];
            inputStream.readFully(savedBuffer);
            if (!Arrays.equals(savedBuffer, HEADER))
                throw new IOException("Invalid saved header found.");

            int savedVersion = inputStream.readInt();
            if (savedVersion != VERSION) {
                throw new IOException("Invalid saved version found. Should be " + VERSION + ", not " + savedVersion);
            }

            // Read the saved data if the checks passed
            serializable.read(inputStream);
        }
    }
}
