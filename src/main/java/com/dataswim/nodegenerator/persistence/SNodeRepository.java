package com.dataswim.nodegenerator.persistence;

import com.dataswim.nodegenerator.model.SNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface SNodeRepository extends Neo4jRepository<SNode, UUID> {

}
