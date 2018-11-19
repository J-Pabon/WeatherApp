package jpabon.com.weatherapp.api;

import java.util.List;

public class Response<T> {
    private int cnt;
    private T list;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}