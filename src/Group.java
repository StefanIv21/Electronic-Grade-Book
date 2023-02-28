import java.util.Comparator;
import java.util.TreeSet;

public class Group extends TreeSet<Student> {
    private Assistant asistent;
    private String ID;
    public Group(String ID, Assistant assistant, Comparator<Student> comp) {
        super(comp);
        this.ID=ID;
        this.asistent=assistant;
    }


    public Group(String ID, Assistant assistant)
    {
        this(ID,assistant,null);
    }


    public Assistant getAsistent() {
        return asistent;
    }

    public String getID() {
        return ID;
    }

    public void setAsistent(Assistant asistent) {
        this.asistent = asistent;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    @Override
    public String toString() {
        return "Group{" +
                "asistent=" + asistent +
                ", ID='" + ID + '\'' +
                '}';
    }
}

