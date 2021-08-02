package com.yht.entity;

/**
 * @ClassName ClassEntity
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/29 8:40
 */
public class ClassEntity {
    private Integer id;
    private String classNo;
    private String major;
    private Integer classGrade;
    private Integer isExcellent;
    private Integer stuCount;

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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(Integer classGrade) {
        this.classGrade = classGrade;
    }

    public Integer getIsExcellent() {
        return isExcellent;
    }

    public void setIsExcellent(Integer isExcellent) {
        this.isExcellent = isExcellent;
    }

    public Integer getStuCount() {
        return stuCount;
    }

    public void setStuCount(Integer stuCount) {
        this.stuCount = stuCount;
    }

    @Override
    public String toString() {
        return "ClassEntity{" +
                "id=" + id +
                ", classNo='" + classNo + '\'' +
                ", major='" + major + '\'' +
                ", classGrade=" + classGrade +
                ", isExcellent=" + isExcellent +
                ", stuCount=" + stuCount +
                '}';
    }
}