import java.io.FileWriter;
import java.util.Scanner;
import Parser.MemoryParser;
import Parser.ProcessParser;

class MemoryAllocator {
    private static FileWriter Writer;
    private static Scanner in = new Scanner(System.in);
    private static String answer = "";

    public static void main(String[] args) {
        try {
            do {
                System.out.print(
                        "Hello! Which algorithm would you like to use?\n1: FF\n2: BF\n3: WF\nType '!Q' to exit.\n\n>> ");
                answer = in.nextLine();
                switch (answer) {
                    case "1":

                        // Block for reading in memory file
                        System.out.println("Input the memory file you would like to cordinate: ");
                        answer = in.nextLine();
                        MemoryParser mem_parser = new MemoryParser(answer);
                        mem_parser.Parse_Memory_File();

                        // Print list of memory blocks
                        System.out.println(mem_parser.toString());

                        // block for reading in process file
                        System.out.println("Input the process file you would like to cordinate: ");
                        answer = in.nextLine();
                        ProcessParser process_parser = new ProcessParser(answer);
                        process_parser.Parse_Process_File();

                        // Print list of processes
                        System.out.println(process_parser.toString());
                        Writer = new FileWriter("ffoutput.data");

                        // TODO make this into it's own method and break this junk code into pieces
                        /*
                         * Implementation:
                         * 1- Input memory blocks with size and processes with size.
                         * 2- Initialize all memory blocks as free.
                         * 3- Start by picking each process and check if it can
                         * be assigned to current block.
                         * 4- If size-of-process <= size-of-block if yes then
                         * assign and check for next process.
                         * 5- If not then keep checking the further blocks.
                         */
                        int process_index = 0;
                        for (int i = 0; i < process_parser.total_processes; i++) {
                            Types.Process p = process_parser.process_list.get(process_index);
                            for (Types.MemoryBlock current_block : mem_parser.mem_list) {
                                if (!current_block.isTaken) {
                                    if (p.size <= current_block.size) {
                                        System.out.println("HERE WITH PROCESS " + p.id);
                                        current_block.process = p;
                                        current_block.isTaken = true;
                                        p.assigned = true;
                                        break;
                                    }
                                }
                            }
                            process_index += 1;
                        }
                        for (Types.MemoryBlock current_block : mem_parser.mem_list) {
                            if (current_block.isTaken) {
                                Writer.write(current_block.start + " "
                                        + (current_block.start + current_block.process.size) + " "
                                        + current_block.process.id + "\n");
                            } else {
                                Writer.write(current_block.start + " " + current_block.end + " -1\n");
                            }
                        }
                        boolean all_assigned = true;
                        for (Types.Process process : process_parser.process_list) {
                            if (!process.assigned) {
                                all_assigned = false;
                                break;
                            }
                        }
                        if (all_assigned)
                            Writer.write("-0");
                        else
                            Writer.write("-1");

                        // Hardcoded code output to file
                        // Writer.write("100 310 2\n600 790 1\n1500 1705 3\n-0");
                        Writer.close();
                        break;
                    case "!Q":
                        System.exit(1);
                    default:
                        System.out.println("Oh no... That command isn't allowed here.");
                }
            } while (!answer.equals("!Q"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
