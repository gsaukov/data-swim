package com.dataswim.nodegenerator.model;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Node implements Comparable {

    private final UUID id;
    private final String label;
    private final UUID parentId;
    private final Map<String, String> payload;
    private final List<Node> childs;

    public Node(String label, Node parent) {
        this.id = UUID.randomUUID();
        this.label = label;
        this.parentId = parent == null ? null : parent.id;
        this.payload = new HashMap<>();
        this.childs = new ArrayList<>();
    }

    public void addChilds(List<Node> childs) {
        this.childs.addAll(childs);
    }

    public void addPayload(Map<String, String> payload) {
        this.payload.putAll(payload);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id.equals(node.id) &&
                label.equals(node.label) &&
                Objects.equals(parentId, node.parentId) &&
                childs.equals(node.childs) &&
                payload.equals(node.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, parentId, childs, payload);
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return 1;
        Node node = (Node) o;
        return node.id.compareTo(this.id);
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
        List<Node> sortedChilrens = new ArrayList(childs);
        Collections.sort(sortedChilrens);
        return sortedChilrens.stream().map(Object::toString)
                .collect(Collectors.joining(""));
    }
}
