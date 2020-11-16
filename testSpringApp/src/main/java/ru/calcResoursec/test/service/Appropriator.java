package ru.calcResoursec.test.service;

public class Appropriator {
    public int getCheckNum(boolean[] array) {
        int checkNum;
        boolean isBusy;

        for (int i = 1; i < array.length; i++) {
            isBusy = array[i];

            if (!isBusy) {
                checkNum = i;
                return checkNum;
            }
        }
        return -1;
    }
    public void removeCheckNum(boolean[] array) {

    }
}
