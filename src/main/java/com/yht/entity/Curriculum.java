package com.yht.entity;

/**
 * @ClassName Curriculum
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/26 14:53
 */
public class Curriculum {
    private Integer id;
    private String classNo;
    private String className;
    private String classRoom;
    private Integer classHour;
    private Integer classGrade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public Integer getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(Integer classGrade) {
        this.classGrade = classGrade;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", classHour=" + classHour +
                ", classGrade=" + classGrade +
                '}';
    }
}