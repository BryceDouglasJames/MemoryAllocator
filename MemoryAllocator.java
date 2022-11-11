import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import Parser.MemoryParser;
import Parser.ProcessParser;

class MemoryAllocator {
    private static Scanner in = new Scanner(System.in);
    private static String answer = "";

    public static void main(String[] args) {
        MemoryParser mem_parser = null;
        ProcessParser process_parser = null;
        try {
            do {

                try {
                    System.out.println("Input the memory file you would like to cordinate: ");
                    answer = "InputFiles/" + in.nextLine();
                    if (!answer.split("\\.")[1].equals("data")) {
                        System.out.println("ERROR IN FILE ARRAY: " + Arrays.toString(answer.split("\\.")));
                        throw new Exception();
                    }

                    mem_parser = new MemoryParser(answer);
                    mem_parser.Parse_Memory_File();

                    // Print list of memory blocks
                    System.out.println(mem_parser.toString());

                    // block for reading in process file
                    System.out.println("Input the process file you would like to cordinate: ");
                    answer = "InputFiles/" + in.nextLine();
                    if (!answer.split("\\.")[1].equals("data")) {
                        throw new Exception();
                    }

                    process_parser = new ProcessParser(answer);
                    process_parser.Parse_Process_File();

                    // Print list of processes
                    System.out.println(process_parser.toString());

                    System.out.print(
                            "Which algorithm would you like to use?\n1: FF\n2: BF\n3: WF\nType '!Q' to exit.\n\n>> ");
                    answer = in.nextLine();
                } catch (Exception e) {
                    System.out.println("Format must have .data extention.");
                }

                switch (answer) {
                    case "1":
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
                                        // System.out.println("HERE WITH PROCESS " + p.id);
                                        current_block.process = p;
                                        current_block.isTaken = true;
                                        p.assigned = true;
                                        break;
                                    }
                                }
                            }
                            process_index += 1;
                        }

                        output_gen("OutputFiles/ffoutput.data", process_parser, mem_parser);
                        break;

                    case "2":
                        /*
                         * Implementation:
                         * 1- Input memory blocks and processes with sizes.
                         * 2- Initialize all memory blocks as free.
                         * 3- Start by picking each process and find the
                         * minimum block size that can be assigned to
                         * current process i.e., find min(bockSize[1],
                         * blockSize[2],.....blockSize[n]) >
                         * processSize[current], if found then assign
                         * it to the current process.
                         * 5- If not then leave that process and keep checking
                         * the further processes.
                         * 
                         */
                        PriorityQueue<Types.MemoryBlock> temp_queue = mem_parser.mem_min_list;
                        process_index = 0;
                        for (int i = 0; i < process_parser.total_processes; i++) {
                            Types.Process p = process_parser.process_list.get(process_index);
                            for (Types.MemoryBlock current_block : temp_queue) {
                                if (!current_block.isTaken) {
                                    if (p.size <= current_block.size) {
                                        //System.out.println("HERE WITH PROCESS " + p.id + "\n");
                                        current_block.process = p;
                                        current_block.isTaken = true;
                                        p.assigned = true;
                                        break;
                                    }
                                }
                            }
                            process_index += 1;
                        }

                        output_gen("OutputFiles/bfoutput.data", process_parser, mem_parser);
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

    public static void output_gen(String out_name, ProcessParser p, MemoryParser m) throws IOException {
        FileWriter Writer = new FileWriter(out_name);
        for (Types.MemoryBlock current_block : m.mem_list) {
            if (current_block.isTaken) {
                Writer.write(current_block.start + " "
                        + (current_block.start + current_block.process.size) + " "
                        + current_block.process.id + "\n");
            } else {
                Writer.write(current_block.start + " " + current_block.end + " -1\n");
            }
        }

        boolean all_assigned = true;
        for (Types.Process process : p.process_list) {
            if (!process.assigned) {
                all_assigned = false;
                break;
            }
        }
        if (all_assigned)
            Writer.write("-0");
        else
            Writer.write("-1");

        System.out.println(out_name + " has been generated successfully!\n");
        Writer.close();
    }
}
