# COMP590: Assignment 2: Java Threads
# Team Members: Reese Letts and Maria Thomas

In your readme file for the zipfile, explain your design rationale. What features and structures are you using to represent the philosophers? To represent the table, the forks, the spaghetti? To represent eating phase of a philosophers "life"... the thinking phase?

What does your algorithm do to help prevent deadlocks and starvation? Are deadlocks and/or starvations still possible (and just improbable)?




To run the code:

javac Dining.java Philosopher.java Forks.java

java Dining



Design Rationale

Representation of Philosophers

Philosophers are represented as threads using the Philosopher class. Each philosopher has an ID and runs an infinite loop where they alternate between thinking and eating. The philosopher will attempt to pick up forks before eating and put them down after finishing.

Representation of the Table, Forks, and Spaghetti

Table: The table itself is abstract and does not have a dedicated class. It is implied by the interactions between the philosophers and the forks.

Forks: The Forks class represents the five forks as a boolean array (using[]), where each index corresponds to a fork. A philosopher must acquire two forks (left and right) before eating.

Spaghetti: The spaghetti is not explicitly represented in the program but is implied during the eating phase when a philosopher has acquired both forks.

Representation of Thinking and Eating

Thinking: When a philosopher is thinking, they sleep for a random amount of time (500ms - 2000ms).

Eating: When a philosopher successfully picks up both forks, they eat for a random amount of time (500ms - 1500ms).

These time variations simulate a more natural flow and help prevent synchronization issues by staggering philosopher actions.

Deadlock and Starvation Prevention

Preventing Deadlocks

Deadlocks occur when each philosopher is holding one fork and waiting indefinitely for the other. The solution in this implementation includes:

Queue-based Fork Management: Each philosopher requests forks in a queue. A philosopher only picks up forks if they are at the front of the queue and both forks are available. This prevents circular waiting, a key cause of deadlocks.

Synchronized Fork Access: The pickUpFork() and putDownFork() methods in Forks are synchronized to avoid multiple philosophers interfering with each other.

Preventing Starvation

Starvation happens when a philosopher waits indefinitely because others keep eating. The queue-based approach ensures fairness:

Philosophers are served in the order they requested forks, preventing any one philosopher from being continuously skipped.

The notifyAll() in putDownFork() wakes up all waiting philosophers, ensuring they get a chance to check fork availability.

Are Deadlocks or Starvation Still Possible?

While the algorithm significantly reduces the likelihood of deadlocks and starvation, they are still theoretically possible under extreme timing conditions. However, the probability is very low due to the fairness introduced by the queue mechanism.

Summary

This implementation ensures fairness using a queue, synchronizes fork access, and randomizes thinking and eating times to simulate real-world behavior. These techniques help prevent deadlocks and starvation, making the solution efficient for concurrent execution.
