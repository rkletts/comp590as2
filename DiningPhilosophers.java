import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    public static void main(String[] args) {
        int numberOfPhilosophers = 5;

        // Create forks (shared resources)
        ReentrantLock[] forks = new ReentrantLock[numberOfPhilosophers];
        for (int i = 0; i < numberOfPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }

        // Create and start philosopher threads
        Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];
        for (int i = 0; i < numberOfPhilosophers; i++) {
            // Each philosopher gets the left and right fork
            ReentrantLock leftFork = forks[i];
            ReentrantLock rightFork = forks[(i + 1) % numberOfPhilosophers];

            // To prevent deadlock, always pick up the lower-numbered fork first
            if (i % 2 == 0) {
                philosophers[i] = new Philosopher(i, rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher(i, leftFork, rightFork);
            }

            new Thread(philosophers[i], "Philosopher " + i).start();
        }
    }
}
