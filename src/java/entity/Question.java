/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Asus
 */
public class Question {
    private int id;
    private int subjectId;
    private String description;
    private int chapter;

    public Question() {
    }

    public Question(int id, int subjectId, String description, int chapter) {
        this.id = id;
        this.subjectId = subjectId;
        this.description = description;
        this.chapter = chapter;
    }

    public Question(int subjectId, String description, int chapter) {
        this.subjectId = subjectId;
        this.description = description;
        this.chapter = chapter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", subjectId=" + subjectId + ", description=" + description + ", chapter=" + chapter + '}';
    }
    
    
}
