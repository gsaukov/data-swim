package com.dataswim.nodegenerator.model;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Getter
@Node("SNode")
public class SNode implements Comparable {

    @Id
    private final UUID id;
    @Property(name="LABEL")
    private final String label;
    @Relationship(type = "PARENT", direction = OUTGOING)
    private final SNode parent;
    @CompositeProperty()
    private final Map<String, String> payload;
    @Relationship(type = "CHILDS", direction = OUTGOING)
    private final List<SNode> childs;

    public SNode(String label, SNode parent) {
        this.id = UUID.randomUUID();
        this.label = label;
        this.parent = parent;
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
        return id.equals(snode.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
        return id + label + sortedPayloadToString() + sortedChildrenToString();
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
