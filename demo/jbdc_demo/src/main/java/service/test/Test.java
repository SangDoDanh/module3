package service.test;

import model.LapTop;
import repository.IRepository;
import repository.impl.LapTopRepository;

public class Test {
    public static void main(String[] args) {
        LapTop lapTop = new LapTop("name", "producer", 999, "color1");
        IRepository<LapTop> repository = new LapTopRepository();
        repository.insert(lapTop);
    }
}
