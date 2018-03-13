package algorithms.DIPLOMA.util.read_write;

import algorithms.DIPLOMA.model.Genome;

import java.util.ArrayList;
import java.util.Map;

public interface Writer {
    void write(Map<String, ArrayList<Genome>> data);
}
