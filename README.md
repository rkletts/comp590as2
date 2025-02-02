### COMP590: Assignment 2: Java Threads
### Team Members: Reese Letts and Maria Thomas

# To run the code:

javac Dining.java Philosopher.java Forks.java

java Dining

# How We Designed It
Each philosopher is a separate thread in our program. They have unique IDs and continuously switch between thinking and eating in an infinite loop. To eat, they must grab both forks (the one on their left and the one on their right). We didn’t make a separate "table" class since the interactions between philosophers and forks naturally create the dining environment. The Forks class manages five forks using a boolean array, (using[]), where each index corresponds to a fork. Spaghetti isn’t explicitly coded, as it’s just implied when a philosopher eats.

# Thinking & Eating
Philosophers think for a random time (500ms - 2000ms) and eat (when both forks are picked up) for a random time (500ms - 1500ms). This randomness helps prevent everyone from trying to grab forks at the same time and helps prevent synchronization issues by staggering philosopher actions.

# Avoiding Deadlocks & Starvation
Deadlocks (where everyone gets stuck waiting for forks) are prevented by using a queue based system for forks. A philosopher can only grab forks if they’re first in line and both forks are available. This prevents circular waiting. Also, we synchronized fork access so multiple philosophers don’t interfere with each other. The pickUpFork() and putDownFork() methods in Forks are synchronized to avoid philosophers interfering with each other. Starvation (where some philosophers never get to eat) is prevented because philosophers take turns in order, or in other words, it is prevented by the queue-based system. When a philosopher puts down their forks, they notify everyone else to check if it’s their turn to eat. Philosophers are served in the order they requested forks, preventing any one philosopher from being continuously skipped. The notifyAll() in putDownFork() wakes up all waiting philosophers, ensuring they get a chance to check fork availability.

# Are deadlocks and/or starvations still possible (and just improbable)?
Technically, deadlocks and starvation could still happen in rare cases, but it’s very unlikely because of the fairness in our design. The algorithm significantly reduces the likelihood of both deadlocks and starvation, but they are still theoretically possible under extreme timing conditions. However, the probability is very low due to the queue mechanism.

We used threads for philosophers, a queue system for fairness, and synchronized fork access to keep things running smoothly. Random wait times also help prevent everyone from acting at once. Overall, our approach makes deadlocks and starvation really unlikely while keeping the simulation realistic.
