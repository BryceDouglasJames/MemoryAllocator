package Types;

public class MemoryBlock {
    public boolean isTaken;
    public int start;
    public int end;
    public int size;
    public Process process;

    public MemoryBlock(int start, int end) {
        this.isTaken = false;
        this.start = start;
        this.end = end;
        this.size = end - start;
        this.process = new Process(-1, -1);
    }

    @Override
    public String toString() {
        return "Size: " + this.size + "\tStart: " + this.start + "\tEnd: " + this.end + "\tisAssigned: " + this.isTaken
                + "\tprocess: " + this.process.toString();
    }

}
