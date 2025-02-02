import java.util.LinkedList;
import java.util.Queue;

class Forks {
    private boolean[] using = {false, false, false, false, false};
    private Queue<Integer> queue = new LinkedList<>();

    public synchronized void pickUpFork(int id) {
        queue.add(id);

        while (queue.peek() != id || using[id] || using[(id + 1) % 5]) {
            try {
                wait();
            }
            catch (InterruptedException e) {

            }
        }

        queue.poll();
        using[id] = true;
        using[(id + 1) % 5] = true;
        System.out.printf("Philosopher %d picks up both of the forks.\n", id + 1);

    }

    public synchronized void putDownFork(int id) {
        using[id] = false;
        using[(id + 1) % 5] = false;
        System.out.printf("Philosopher %d puts down both of the forks.\n", id + 1);
        notifyAll();

    }
}
