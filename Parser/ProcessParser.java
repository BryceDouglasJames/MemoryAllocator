package Parser;

import Types.Process;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ProcessParser {
    Scanner process_scanner;
    public List<Process> process_list;
    public int total_processes = 0;

    public ProcessParser(String filename) throws FileNotFoundException {
        process_scanner = new Scanner(new File("./" + filename));
        process_list = new LinkedList<>();
    }

    public void Parse_Process_File() {
        try {
            total_processes = process_scanner.nextInt();
            for (int i = 0; i < total_processes; i++) {
                int id = process_scanner.nextInt();
                int size = process_scanner.nextInt();
                Process p = new Process(id, size);
                process_list.add(p);
            }
        } catch (Exception e) {
            System.out.println("There is an error in the formatting of you parse input file... please try again.");
        }

        process_scanner.close();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Process p : process_list) {
            s.append(p.toString() + "\n");
        }
        return "\nProcesses that have been parsed: \n" + s.toString();
    }
}
