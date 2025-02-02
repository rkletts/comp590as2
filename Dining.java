public class Dining {
    public static void main(String[] args){

        Forks F = new Forks();

        new Philosopher(F).start();
        new Philosopher(F).start();
        new Philosopher(F).start();
        new Philosopher(F).start();
        new Philosopher(F).start();

    }
}