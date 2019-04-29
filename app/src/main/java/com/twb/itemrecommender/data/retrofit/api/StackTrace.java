package com.twb.itemrecommender.data.retrofit.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StackTrace {

    @SerializedName("methodName")
    @Expose
    private String methodName;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("lineNumber")
    @Expose
    private long lineNumber;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("nativeMethod")
    @Expose
    private boolean nativeMethod;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isNativeMethod() {
        return nativeMethod;
    }

    public void setNativeMethod(boolean nativeMethod) {
        this.nativeMethod = nativeMethod;
    }

}
