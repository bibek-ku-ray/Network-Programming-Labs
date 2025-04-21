package chapter9;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Pro2BufferOperations {
    public static void main(String[] args) {
        // Create a ByteBuffer with capacity for 10 integers (40 bytes)
        ByteBuffer originalBuffer = ByteBuffer.allocate(40);

        // Fill the buffer with values
        for (int i = 0; i < 10; i++) {
            originalBuffer.putInt(i + 1); // Values 1-10
        }

        // Prepare buffer for reading
        originalBuffer.flip();
        System.out.println("Original Buffer:");
        System.out.println("Position: " + originalBuffer.position());
        System.out.println("Limit: " + originalBuffer.limit());
        System.out.println("Capacity: " + originalBuffer.capacity());

        // 1. DUPLICATE operation
        ByteBuffer duplicateBuffer = originalBuffer.duplicate();
        System.out.println("\n--- DUPLICATE DEMONSTRATION ---");
        System.out.println("Duplicate Buffer (shares position/limit with original):");
        System.out.println("Position: " + duplicateBuffer.position());
        System.out.println("Limit: " + duplicateBuffer.limit());

        // Move the position of the duplicate buffer
        duplicateBuffer.position(16); // Move to position 16

        System.out.println("\nAfter moving duplicate's position to 16:");
        System.out.println("Original Position: " + originalBuffer.position());
        System.out.println("Duplicate Position: " + duplicateBuffer.position());

        // Show that content is shared
        originalBuffer.putInt(0, 100); // Change first integer
        System.out.println("\nContent sharing demonstration:");
        System.out.println("First integer in original: " + originalBuffer.getInt(0));
        System.out.println("First integer in duplicate: " + duplicateBuffer.getInt(0));

        // 2. SLICE operation
        originalBuffer.position(8);  // Start from 3rd integer
        originalBuffer.limit(24);    // End before 7th integer

        ByteBuffer sliceBuffer = originalBuffer.slice();
        System.out.println("\n--- SLICE DEMONSTRATION ---");
        System.out.println("Slice Buffer (view from position to limit of original):");
        System.out.println("Position: " + sliceBuffer.position());
        System.out.println("Limit: " + sliceBuffer.limit());
        System.out.println("Capacity: " + sliceBuffer.capacity());

        // Show values in slice
        System.out.println("\nValues in slice:");
        IntBuffer sliceView = sliceBuffer.asIntBuffer();
        while (sliceView.hasRemaining()) {
            System.out.print(sliceView.get() + " ");
        }

        // Show content is shared
        sliceBuffer.putInt(0, 555);
        originalBuffer.position(0);
        originalBuffer.limit(originalBuffer.capacity());
        System.out.println("\n\nAfter changing first integer in slice to 555:");
        System.out.println("Value at position 8 in original: " + originalBuffer.getInt(8));

        // 3. VIEW BUFFER operation
        originalBuffer.clear();
        IntBuffer intBufferView = originalBuffer.asIntBuffer();

        System.out.println("\n--- VIEW BUFFER DEMONSTRATION ---");
        System.out.println("IntBuffer view of ByteBuffer:");
        System.out.println("Position: " + intBufferView.position());
        System.out.println("Limit: " + intBufferView.limit());
        System.out.println("Capacity: " + intBufferView.capacity());

        // Show values in view
        System.out.println("\nValues in IntBuffer view:");
        while (intBufferView.hasRemaining()) {
            System.out.print(intBufferView.get() + " ");
        }

        // Show changes in view affect original
        intBufferView.position(0);
        intBufferView.put(2, 777);
        System.out.println("\n\nAfter changing third integer to 777 via view:");
        originalBuffer.position(8);
        System.out.println("Value at position 8 in original ByteBuffer: " + originalBuffer.getInt());
    }
}

// Output:
/*
Original Buffer:
Position: 0
Limit: 40
Capacity: 40

--- DUPLICATE DEMONSTRATION ---
Duplicate Buffer (shares position/limit with original):
Position: 0
Limit: 40

After moving duplicate's position to 16:
Original Position: 0
Duplicate Position: 16

Content sharing demonstration:
First integer in original: 100
First integer in duplicate: 100

--- SLICE DEMONSTRATION ---
Slice Buffer (view from position to limit of original):
Position: 0
Limit: 16
Capacity: 16

Values in slice:
3 4 5 6

After changing first integer in slice to 555:
Value at position 8 in original: 555

--- VIEW BUFFER DEMONSTRATION ---
IntBuffer view of ByteBuffer:
Position: 0
Limit: 10
Capacity: 10

Values in IntBuffer view:
100 2 555 4 5 6 7 8 9 10

After changing third integer to 777 via view:
Value at position 8 in original ByteBuffer: 777
 */