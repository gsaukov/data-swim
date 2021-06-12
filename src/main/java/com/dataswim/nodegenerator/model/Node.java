package com.dataswim.nodegenerator.model;

import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
        this.parentId = parent.getId();
        this.payload = new HashMap<>();
        this.childs = new ArrayList<>();
    }

    public void addChilds(List<Node> childs) {
        this.childs.addAll(childs);
    }

    public void addPayload(Map<String, String> payload) {
        this.payload.putAll(payload);
    }

    public String asSortedText() {
        return id.toString() +
                label +
                parentId.toString() +
                payloadAsSortedText() +
                childsAsSortedText();
    }

    private String payloadAsSortedText() {
        Map<String, String> sortedPayload = new TreeMap<>(payload);
        StringBuilder sortedText = new StringBuilder();
        for (String key : new TreeMap<>(payload).keySet()) { //Keys are sorted ASC
            sortedText.append(key + payload.get(key));
        }
        return sortedText.toString();
    }

    private String childsAsSortedText() {
        List<Node> sortedChilrens = new ArrayList(childs);
        Collections.sort(sortedChilrens);
        StringBuilder sortedText = new StringBuilder();
        for (Node child : sortedChilrens) {
            sortedText.append(child.asSortedText());
        }
        return sortedText.toString();
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
}
