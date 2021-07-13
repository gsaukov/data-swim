package com.dataswim.nodegenerator.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dataswim.nodegenerator.model.SNode;
import com.dataswim.nodegenerator.persistence.SNodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class SNodeGraphController implements GraphQLQueryResolver {

    private SNodeRepository repository;

    public Page<SNode> getNodes(int count, int offset) {
        return repository.findAll(PageRequest.of(count, offset));
    }
}
