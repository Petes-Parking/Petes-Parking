package com.petesparkingmgt.objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maxfuligni
 * @date 2/28/23
 *
 * Wrapper class for date history. Only holds three elements and pushes the oldest element
 */
public class DateQueue {

    int maxSize = 3;

    private List<LocalDate> queue;


    public DateQueue() {
        this.queue = new ArrayList<>();
    }


    public List<LocalDate> getQueue() {
        return queue;
    }

    /**
     * @param date to insert
     *
     *   Shifts elements to the right to insert new element.
     *    Use this method to add new dates
     */
    public void insert(LocalDate date){
        if (!queue.isEmpty()) {
            // Push elements back and pop the last element
            for (int i = 0; i < (maxSize - 1); i++){

                LocalDate current = queue.get(i);
                queue.set(i + 1, current);
            }
            queue.set(0, date);
        } else {
            queue.set(0, date);
        }
    }

}
