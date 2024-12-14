// Quick Fit Memory Allocation Simulation for Registration Number 321422795
// Remainder 5 -> Quick Fit A
import java.util.*;

public class QuickFit {

    // Memory block structure
    static class MemoryBlock {
        int size;
        boolean allocated;

        MemoryBlock(int size) {
            this.size = size;
            this.allocated = false;
        }
    }

    // Predefined list of free lists for fixed block sizes
    private static final Map<Integer, List<MemoryBlock>> freeLists = new HashMap<>();

    // Initialize the memory pool with predefined block sizes
    public static void initializeMemory(int[] blockSizes, int[] blockCounts) {
        for (int i = 0; i < blockSizes.length; i++) {
            int size = blockSizes[i];
            freeLists.put(size, new ArrayList<>());
            for (int j = 0; j < blockCounts[i]; j++) {
                freeLists.get(size).add(new MemoryBlock(size));
            }
        }
    }

    // Allocate memory for a given size
    public static MemoryBlock allocateMemory(int size) {
        if (freeLists.containsKey(size)) {
            for (MemoryBlock block : freeLists.get(size)) {
                if (!block.allocated) {
                    block.allocated = true;
                    System.out.println("Allocated block of size " + size);
                    return block;
                }
            }
        }
        System.out.println("No available block for size " + size);
        return null;
    }

    // Deallocate memory block
    public static void deallocateMemory(MemoryBlock block) {
        if (block != null) {
            block.allocated = false;
            System.out.println("Deallocated block of size " + block.size);
        }
    }

    public static void main(String[] args) {
        // Initialize memory with block sizes 32, 64, 128, and 256
        int[] blockSizes = {32, 64, 128, 256};
        int[] blockCounts = {5, 5, 5, 5};
        initializeMemory(blockSizes, blockCounts);

        // Simulate memory allocation and deallocation
        MemoryBlock block1 = allocateMemory(64);   // Allocate 64
        MemoryBlock block2 = allocateMemory(128);  // Allocate 128
        allocateMemory(256);                       // Allocate 256
        allocateMemory(64);                        // Allocate another 64

        // Deallocate memory
        deallocateMemory(block1);                  // Deallocate 64
        deallocateMemory(block2);                  // Deallocate 128

        // Attempt to allocate again
        allocateMemory(64);                        // Allocate 64 again
    }
}