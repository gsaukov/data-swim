package com.dataswim.nodegenerator;

import com.dataswim.nodegenerator.model.SNode;
import com.dataswim.nodegenerator.services.SNodeGenerator;
import com.dataswim.nodegenerator.services.SNodeTreeCounter;
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
