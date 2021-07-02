package com.dataswim.nodegenerator.controller;

import com.dataswim.nodegenerator.model.SNode;
import com.dataswim.nodegenerator.persistence.SNodeRepository;
import com.dataswim.nodegenerator.services.SNodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SNodeController {

    @Autowired
    SNodeRepository repository;

    @GetMapping("/v1/populate")
    public void populate() {
        SNodeGenerator generator = new SNodeGenerator();
        SNode n = generator.generate(null, 2, 4);
        repository.save(n);
    }

}
