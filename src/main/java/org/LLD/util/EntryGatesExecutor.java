package org.LLD.util;

import org.LLD.model.Ticket;

import java.util.concurrent.*;

public class EntryGatesExecutor {
    private final ExecutorService executor;
    private final ConcurrentHashMap<Integer, Future<?>> activeThreads;

    public EntryGatesExecutor(int threadCount, ConcurrentHashMap<Integer, Future<?>> activeThreads) {
        this.executor = Executors.newFixedThreadPool(threadCount);
        this.activeThreads = activeThreads;
    }
    public Future<?> park(int entryGateId, Callable<Ticket> task) {
        return activeThreads.computeIfAbsent(entryGateId, gate -> {
            System.out.println("Creating thread for Entry Gate: " + entryGateId);
            return executor.submit(() -> {
                try {
                    return  task.call();
                } finally {
                    activeThreads.remove(entryGateId); // Remove after execution
                }
            });
        });
    }


}
