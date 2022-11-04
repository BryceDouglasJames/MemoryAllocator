package Types;

public class Process {
    public int id;
    public int size;
    public boolean assigned;

    public Process(int id, int size) {
        this.id = id;
        this.size = size;
        this.assigned = false;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + "\tSize: " + this.size;
    }
}
