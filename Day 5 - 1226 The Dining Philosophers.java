import java.util.concurrent.locks.ReentrantLock;
class DiningPhilosophers {
    private ReentrantLock[] forks = new ReentrantLock[5];
    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }
    }
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork)
            throws InterruptedException {
        ReentrantLock left = forks[philosopher];
        ReentrantLock right = forks[(philosopher + 1) % 5];
        if (philosopher % 2 == 0) {
            left.lock();
            right.lock();
        } else {
            right.lock();
            left.lock();
        }
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        left.unlock();
        right.unlock();
    }
}
