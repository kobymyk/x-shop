package com.study.onlineshop.dao;

import java.util.List;

public interface Persistent<T> {
    List<T> getAll();
}
