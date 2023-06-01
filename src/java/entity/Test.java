/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Asus
 */
public class Test {
    private int id;
    private String name;
    private int duration;
    private Timestamp startTime, FinishedTime;
    private int numOfQues;
    private double coefficient;
    private int classId;
    int status;

    public Test() {
    }

    public Test(int id, String name, int duration, Timestamp startTime, Timestamp FinishedTime, int numOfQues, double coefficient, int classId, int status) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.startTime = startTime;
        this.FinishedTime = FinishedTime;
        this.numOfQues = numOfQues;
        this.coefficient = coefficient;
        this.classId = classId;
        this.status = status;
    }

    public Test(String name, int duration, Timestamp startTime, Timestamp FinishedTime, int numOfQues, double coefficient, int classId, int status) {
        this.name = name;
        this.duration = duration;
        this.startTime = startTime;
        this.FinishedTime = FinishedTime;
        this.numOfQues = numOfQues;
        this.coefficient = coefficient;
        this.classId = classId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getFinishedTime() {
        return FinishedTime;
    }

    public void setFinishedTime(Timestamp FinishedTime) {
        this.FinishedTime = FinishedTime;
    }

    public int getNumOfQues() {
        return numOfQues;
    }

    public void setNumOfQues(int numOfQues) {
        this.numOfQues = numOfQues;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Test{" + "id=" + id + ", name=" + name + ", duration=" + duration + ", startTime=" + startTime + ", FinishedTime=" + FinishedTime + ", numOfQues=" + numOfQues + ", coefficient=" + coefficient + ", classId=" + classId + ", status=" + status + '}';
    }
    
    
}
