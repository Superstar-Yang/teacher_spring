package com.example.entity;

/**
 * 管理员
 */
public class Speciality {

    /**
     * ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;
    /**
     * 描述
     */
    private Integer collegeId;
    private String collegeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
