package service;

import exception.ResourceAllocationException;
import model.LibraryBook;
import model.PatronReservation;

import java.util.*;

public class LibraryCirculationFlow {
    private final List<LibraryBook> catalogue = new ArrayList<>();
    private final Set<LibraryBook> registry = new LinkedHashSet<>();
    private final Deque<PatronReservation> generalWaitlist = new ArrayDeque<>();
    private final PriorityQueue<PatronReservation> priorityWaitlist = new PriorityQueue<>();

    public void addBooks(Collection<LibraryBook> books) {
        for (LibraryBook b : books) {
            if (registry.add(b)) {
                catalogue.add(b);
            }
        }
    }

    public void enqueueReservation(PatronReservation req, boolean isPriority) {
        if (isPriority) {
            priorityWaitlist.offer(req);
        } else {
            generalWaitlist.offerLast(req);
        }
    }

    public PatronReservation dispatchNext(boolean checkPriorityFirst) throws ResourceAllocationException {
        if (checkPriorityFirst && !priorityWaitlist.isEmpty()) {
            return priorityWaitlist.poll();
        }
        if (!generalWaitlist.isEmpty()) {
            return generalWaitlist.pollFirst();
        }
        throw new ResourceAllocationException("Waitlist is completely empty, no pending reservations.");
    }

    public int auditAndPurgeOverdue(int maxOverdueDays) {
        int count = 0;
        Iterator<LibraryBook> it = catalogue.iterator();
        while (it.hasNext()) {
            LibraryBook b = it.next();
            if (b.getOverdueDays() > maxOverdueDays) {
                it.remove();
                registry.remove(b);
                count++;
            }
        }
        return count;
    }

    public List<LibraryBook> getCatalogue() {
        return Collections.unmodifiableList(catalogue);
    }
}
