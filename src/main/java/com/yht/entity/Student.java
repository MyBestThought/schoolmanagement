package com.yht.entity;

/**
 * @ClassName Student
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/25 8:35
 */
public class Student {
    private Integer id;
    private String icon;
    private String stuNo;
    private String name;
    private Integer gender;
    private String classNo;
    private String birth;
    private String phone;
    private String email;
    private String address;
    private Integer classCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getClassCount() {
        return classCount;
    }

    public void setClassCount(Integer classCount) {
        this.classCount = classCount;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", stuNo='" + stuNo + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", classNo='" + classNo + '\'' +
                ", birth='" + birth + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", classCount=" + classCount +
                '}';
    }
}