package com.twb.itemrecommender.data.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

public class Post {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private Date createdDate;
    @SerializedName("lastModifiedBy")
    @Expose
    private String lastModifiedBy;
    @SerializedName("lastModifiedDate")
    @Expose
    private Date lastModifiedDate;
    @SerializedName("status")
    @Expose
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(content, post.content) &&
                Objects.equals(createdBy, post.createdBy) &&
                Objects.equals(createdDate, post.createdDate) &&
                Objects.equals(lastModifiedBy, post.lastModifiedBy) &&
                Objects.equals(lastModifiedDate, post.lastModifiedDate) &&
                Objects.equals(status, post.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, createdBy, createdDate, lastModifiedBy, lastModifiedDate, status);
    }
}