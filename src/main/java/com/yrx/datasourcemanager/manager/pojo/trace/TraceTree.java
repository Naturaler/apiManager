package com.yrx.datasourcemanager.manager.pojo.trace;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by r.x on 2019/11/21.
 * 调用树
 */
@Data
public class TraceTree<T> {
    private T value;
    private TraceTree<T> parent;
    private List<TraceTree<T>> son;

    public TraceTree(T value) {
        this.value = value;
    }

    public TraceTree<T> appendSon(T value) {
        if (this.getSon() == null) {
            this.setSon(new ArrayList<>());
        }
        TraceTree<T> son = new TraceTree<>(value);
        son.setParent(this);
        this.getSon().add(son);
        return son;
    }

}
