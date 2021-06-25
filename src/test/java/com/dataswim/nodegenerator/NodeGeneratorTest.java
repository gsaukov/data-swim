package com.dataswim.nodegenerator;

import com.dataswim.nodegenerator.model.Node;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class NodeGeneratorTest {

    @Test
    public void generateTree() {
        NodeGenerator generator = new NodeGenerator();
        Node n = generator.generate(null, 3, 6);
        NodeTreeCounter counter = new NodeTreeCounter();
        counter.count(Arrays.asList(n));
    }

}
