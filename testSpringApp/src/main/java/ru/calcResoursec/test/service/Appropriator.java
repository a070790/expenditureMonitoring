package ru.calcResoursec.test.service;

public class Appropriator {
    private boolean[] nums;

    public Appropriator() {
        nums = new boolean[100];
        initArray(nums);
    }

    private void initArray(boolean[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = false;
        }
    }

    public int setNum() {
        int checkNum;
        boolean isBusy;

        for (int i = 1; i < nums.length; i++) {
            isBusy = nums[i];

            if (!isBusy) {
                checkNum = i;
                return checkNum;
            }
        }
        return -1;
    }

    public boolean checkNum(int num) {
        return true;
    }

    public void removeNum(int num) {

    }
}
