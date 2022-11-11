# Java Memory Allocator

This is a project for my Operating Systems class regarding process and memory management. The goal is to implement three elementary allocation techniques:

* First Fit
* Best Fit
* Worst Fit
* Next Fit (Not implemented...)

This project showcases what happens (kinda) when the OS decides which free block to allocate a process to. 
## Installation

* Install [Java](https://www.java.com/en/download/manual.jsp) 
* Once installed, download source repo and navigate to destination folder. 

## Usage
The import formats are as follows:

```bash
Memory Block Input File

3 (number of free memory slots)
100 400 (addresses of start and end of a free memory slot => size 300)
600 800 (addresses of start and end of a free memory slot => size 200)
1500 1900 (addresses of start and end of a free memory slot => size 400)
```

```bash
Process(es) Input file

3 (number of processes)
1 (ID of process) 190 (size of process)
2 (ID of process) 210 (size of process)
3 (ID of process) 205 (size of process)
```

Afterwards, once in the working directory, you can run the commands
```bash
javac MemoryAllocator.java
java MemoryAllocator
```
To execute program :P

## Output
```bash
Input the memory file you would like to cordinate: 
minput.data

Memory blocks that have been parsed: 
Size: 300       Start: 100      End: 400
Size: 200       Start: 600      End: 800
Size: 400       Start: 1500     End: 1900

Input the process file you would like to cordinate: 
pinput.data

Processes that have been parsed: 
ID: 1   Size: 190
ID: 2   Size: 210
ID: 3   Size: 205

Which algorithm would you like to use?
1: FF
2: BF
3: WF
Type '!Q' to exit.

>> 2
bfoutput.data has been generated successfully!

Generated Output:
100 310 2
600 790 1
1500 1705 3
-0
```

## License

[MIT](https://choosealicense.com/licenses/mit/)