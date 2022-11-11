package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import Types.MemoryBlock;

class SortBySize implements Comparator<MemoryBlock> {
    public int compare(MemoryBlock a, MemoryBlock b) {
        return a.size - b.size;
    }
}

public class MemoryParser {

    Scanner mem_scanner;
    public List<MemoryBlock> mem_list;
    public PriorityQueue<MemoryBlock> mem_min_list;
    public int total_blocks = 0;

    public MemoryParser(String filename) throws FileNotFoundException {
        mem_scanner = new Scanner(new File("./" + filename));
        mem_list = new LinkedList<>();
        mem_min_list = new PriorityQueue<>(100, new SortBySize());
    }

    public void Parse_Memory_File() {
        try {
            total_blocks = mem_scanner.nextInt();
            for (int i = 0; i < total_blocks; i++) {
                int s = mem_scanner.nextInt();
                int e = mem_scanner.nextInt();
                MemoryBlock block = new MemoryBlock(s, e);
                mem_list.add(block);
                mem_min_list.add(block);
            }
        } catch (Exception e) {
            System.out.println("There is an error in the formatting of you memory input file... please try again.");
        }

        // Collections.sort(mem_list, new SortBySize());

        mem_scanner.close();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (MemoryBlock b : mem_list) {
            s.append(b.toString() + "\n");
        }
        return "\nMemory blocks that have been parsed: \n" + s.toString();
    }

}
