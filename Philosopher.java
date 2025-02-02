import java.util.Random;


class Philosopher extends Thread {
    private int id;
    private static int counter = 0;
    private Forks fork;
    private Random randomTime = new Random();


    public Philosopher(Forks fork) {
        super();
        this.fork = fork;
        id = counter + 1;
        counter++;

    }


    private void eating() {
        int eatingDuration = randomTime.nextInt(1000) + 500;
        System.out.printf("Philosopher %d is Eating. \n", id);
        try {
            Thread.sleep(eatingDuration);
        }
        catch (InterruptedException e) {

        }

    }

    private void thinking() {
        int thinkDuration = randomTime.nextInt(1500) + 500;
        System.out.printf("Philosopher %d is Thinking. \n", id);
        try {
            Thread.sleep(thinkDuration);
        }
        catch (InterruptedException e) {

        }

    }

    public void run() {
        while (true) {
            thinking();
            fork.pickUpFork(id - 1);
            eating();
            fork.putDownFork(id - 1);
        }

    }
}
