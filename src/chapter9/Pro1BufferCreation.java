package chapter9;

import java.nio.IntBuffer;
import java.util.Arrays;

public class Pro1BufferCreation {
    public static void main(String[] args) {
        // Step 1: Allocate an IntBuffer of size 10
        IntBuffer buffer1 = IntBuffer.allocate(10);
        System.out.println("Original buffer created with capacity: " + buffer1.capacity());

        // Step 2: Fill the IntBuffer with values
        for (int i = 0; i < buffer1.capacity(); i++) {
            buffer1.put(i + 1); // Fill with values 1-10
        }
        System.out.println("Buffer filled with values 1-10");

        // Prepare buffer for reading (sets position to 0 and limit to current position)
        buffer1.flip();

        // Step 3: Convert the IntBuffer to an int array
        int[] array = new int[buffer1.remaining()];
        buffer1.get(array);
        System.out.println("Converted to int array: " + Arrays.toString(array));

        // Step 4: Create another IntBuffer using the wrap method
        IntBuffer buffer2 = IntBuffer.wrap(array);
        System.out.println("Created new IntBuffer by wrapping the array");

        // Step 5: Check if the IntBuffers are equal
        buffer1.rewind(); // Reset position to beginning

        boolean areEqual = buffer1.equals(buffer2);
        System.out.println("\nAre the buffers equal? " + areEqual);

        // Display buffer contents for verification
        buffer1.rewind();
        buffer2.rewind();

        System.out.print("Buffer1 content: ");
        while (buffer1.hasRemaining()) {
            System.out.print(buffer1.get() + " ");
        }

        System.out.print("\nBuffer2 content: ");
        while (buffer2.hasRemaining()) {
            System.out.print(buffer2.get() + " ");
        }
    }
}

// Output:
/*
Original buffer created with capacity: 10
Buffer filled with values 1-10
Converted to int array: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Created new IntBuffer by wrapping the array

Are the buffers equal? true
Buffer1 content: 1 2 3 4 5 6 7 8 9 10
Buffer2 content: 1 2 3 4 5 6 7 8 9 10
 */
