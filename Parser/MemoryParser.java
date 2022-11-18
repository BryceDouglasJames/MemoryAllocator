package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    public class Reverse implements Comparator<MemoryBlock> {
        public int compare(MemoryBlock arg0, MemoryBlock arg1) {
            return arg1.size - arg0.size;
        }
    }

    Scanner mem_scanner;
    public List<MemoryBlock> mem_list;
    public PriorityQueue<MemoryBlock> mem_min_list;
    public PriorityQueue<MemoryBlock> mem_max_queue;
    public ArrayList<MemoryBlock> mem_max_list;
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

        mem_max_queue = new PriorityQueue<>(100, new Reverse());
        for (MemoryBlock i : mem_min_list)
            mem_max_queue.add(i);

        mem_max_list = new ArrayList<>();
        while (!mem_max_queue.isEmpty()) {
            mem_max_list.add(mem_max_queue.poll());
        }
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
