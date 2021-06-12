package com.dataswim.nodegenerator.model;

import lombok.Getter;

import java.util.*;

@Getter
public class Node {

    private final UUID id;
    private final String label;
    private final UUID parentId;
    private final List<Node> childs;
    private final Map<String, String> payload;

    public Node(String label, Node parent) {
        this.id = UUID.randomUUID();
        this.label = label;
        this.parentId = parent.getId();
        this.childs = new ArrayList<>();
        this.payload = new HashMap<>();
    }

    public void addChilds(List<Node> childs) {
        this.childs.addAll(childs);
    }

    public void addChilds(Map<String, String> payload) {
        this.payload.putAll(payload);
    }
}
