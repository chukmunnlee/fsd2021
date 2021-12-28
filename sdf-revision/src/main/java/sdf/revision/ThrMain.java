package sdf.revision;

public class ThrMain implements Runnable {

    private final Integer id;
    private final Integer rand;

    public ThrMain(int i, int rand) {
        id = i;
        this.rand = rand;
    }

    public void run() {
        final String thrName = Thread.currentThread().getName();
        System.out.printf("Name = %s, Id = %d, rand =%d\n", thrName, id, rand);
    }

    @Override
    public String toString() {
        final String thrName = Thread.currentThread().getName();
        return "Name = %s, Id = %d, rand =%d\n".formatted(thrName, id, rand);
    }
}
