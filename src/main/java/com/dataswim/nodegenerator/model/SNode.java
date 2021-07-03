package com.dataswim.nodegenerator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;
import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Setter
@Getter
@NoArgsConstructor
@Node("SNode")
//https://github.com/neo4j/neo4j-ogm/blob/934dcd23825a32f6c3538dba8e2f8d6834540fdd/neo4j-ogm-tests/neo4j-ogm-integration-tests/src/test/java/org/neo4j/ogm/domain/tree/Entity.java
//https://github.com/neo4j/neo4j-ogm/blob/934dcd23825a32f6c3538dba8e2f8d6834540fdd/neo4j-ogm-tests/neo4j-ogm-integration-tests/src/test/java/org/neo4j/ogm/persistence/examples/tree/TreeIntegrationTest.java
public class SNode implements Comparable {

    @Id
    private UUID id;
    @Property(name="LABEL")
    private String label;
    @Relationship(type = "REF", direction = OUTGOING)
    private SNode parent;
    @CompositeProperty()
    private Map<String, String> payload;
    @Relationship(type = "REF", direction = INCOMING)
    private List<SNode> childs;

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
