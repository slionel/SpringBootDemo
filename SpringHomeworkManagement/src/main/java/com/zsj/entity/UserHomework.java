package com.zsj.entity;

import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author zsj55
 */
@Entity
public class UserHomework {
    @Id
    @Column(length = 30)
    private String userId;
    private String HomeworkmasterId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHomeworkmasterId() {
        return HomeworkmasterId;
    }

    public void setHomeworkmasterId(String homeworkmasterId) {
        HomeworkmasterId = homeworkmasterId;
    }
}
