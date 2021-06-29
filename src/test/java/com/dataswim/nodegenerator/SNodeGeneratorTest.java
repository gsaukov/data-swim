package com.dataswim.nodegenerator;

import com.dataswim.nodegenerator.model.SNode;
import org.testng.annotations.Test;

import java.util.Arrays;

public class SNodeGeneratorTest {

    @Test
    public void generateTree() {
        SNodeGenerator generator = new SNodeGenerator();
        SNode n = generator.generate(null, 3, 6);
        SNodeTreeCounter counter = new SNodeTreeCounter();
        counter.count(Arrays.asList(n));
    }

}
