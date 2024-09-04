package com.example.entity;

/**
 * 管理员
 */
public class College {

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
    private String content;
    /**
     * 最低学分
     */
    private Integer score;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
