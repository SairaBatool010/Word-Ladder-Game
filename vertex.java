import java.util.LinkedList;
import java.util.ListIterator;

public class vertex {
    String word;
    String meaning;
    LinkedList<vertex> connections = new LinkedList<>();

    vertex(String d,String meaning) {
        word = d;
        this.meaning=meaning;

    }

    public String toString(){
        return word; //+"--->"+ "meaning:"+ meaning;
    }
}