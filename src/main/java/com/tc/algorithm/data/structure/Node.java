package com.tc.algorithm.data.structure;

/**
 * desc TODO
 *
 * @author lvzf
 * @date 2024/8/9
 */
public class Node {

    private Integer key;

    private Integer height;

    public Node () {}

    public Node (Integer key, Integer height) {
        this.key = key;
        this.height = height;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
