package com.tsingeye.common.utils.bean;

/**
 * Minio返回对象
 */
public class ObjectItem {
    private String objectName;
    private Long size;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ObjectItem{" +
                "objectName='" + objectName + '\'' +
                ", size=" + size +
                '}';
    }
}