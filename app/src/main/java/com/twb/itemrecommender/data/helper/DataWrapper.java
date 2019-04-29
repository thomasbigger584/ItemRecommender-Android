package com.twb.itemrecommender.data.helper;

public class DataWrapper<T> {

    private T data;
    private Throwable error;

    public DataWrapper() {
    }

    public DataWrapper(T data) {
        this.data = data;
    }

    public DataWrapper(Throwable error) {
        this.error = error;
    }

    public boolean isPresent() {
        return data != null;
    }

    public boolean hasError() {
        return error != null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "DataWrapper{" +
                "data=" + data +
                ", error=" + error +
                '}';
    }
}
