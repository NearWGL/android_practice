package com.near.calendar_schedual;


public class Task {
    private long time;
    private String message;

    public Task(long time, String message) {

        this.time = time;
        this.message = message;
    }

    public Task(){}

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Task{" +
                "time=" + time +
                ", message='" + message + '\'' +
                '}';
    }
}
