package com.dataswim.nodegenerator.model;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter

public class SNode implements Comparable {

    private final UUID id;
    private final String label;
    private final UUID parentId;
    private final Map<String, String> payload;
    private final List<SNode> childs;

    public SNode(String label, SNode parent) {
        this.id = UUID.randomUUID();
        this.label = label;
        this.parentId = parent == null ? null : parent.id;
        this.payload = new HashMap<>();
        this.childs = new ArrayList<>();
    }

    public void addChilds(List<SNode> childs) {
        this.childs.addAll(childs);
    }

    public void addPayload(Map<String, String> payload) {
        this.payload.putAll(payload);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SNode snode = (SNode) o;
        return id.equals(snode.id) &&
                label.equals(snode.label) &&
                Objects.equals(parentId, snode.parentId) &&
                childs.equals(snode.childs) &&
                payload.equals(snode.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, parentId, childs, payload);
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return 1;
        SNode snode = (SNode) o;
        return snode.id.compareTo(this.id);
    }

    @Override
    public String toString() {
        return id + label + parentId + sortedPayloadToString() + sortedChildrenToString();
    }

    private String sortedPayloadToString() {
        Map<String, String> sortedPayload = new TreeMap<>(payload);
        StringBuilder sortedText = new StringBuilder();
        for (String key : new TreeMap<>(payload).keySet()) { //Keys are sorted ASC
            sortedText.append(key + payload.get(key));
        }
        return sortedText.toString();
    }

    private String sortedChildrenToString() {
        List<SNode> sortedChilrens = new ArrayList(childs);
        Collections.sort(sortedChilrens);
        return sortedChilrens.stream().map(Object::toString)
                .collect(Collectors.joining(""));
    }
}
