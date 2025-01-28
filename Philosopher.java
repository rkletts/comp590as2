import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;

    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking...");
        Thread.sleep((int) (Math.random() * 1000)); // Simulate thinking time
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating...");
        Thread.sleep((int) (Math.random() * 1000)); // Simulate eating time
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Think
                think();

                // Pick up forks
                leftFork.lock();
                System.out.println("Philosopher " + id + " picked up left fork.");
                rightFork.lock();
                System.out.println("Philosopher " + id + " picked up right fork.");

                // Eat
                eat();

                // Put down forks
                rightFork.unlock();
                System.out.println("Philosopher " + id + " put down right fork.");
                leftFork.unlock();
                System.out.println("Philosopher " + id + " put down left fork.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Philosopher " + id + " was interrupted.");
        }
    }
}
