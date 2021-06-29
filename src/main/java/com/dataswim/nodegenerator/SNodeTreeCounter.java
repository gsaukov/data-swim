package com.dataswim.nodegenerator;

import com.dataswim.nodegenerator.model.SNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SNodeTreeCounter {

    public int count(List<SNode> roots) {
        int counter = 0;
        if(!roots.isEmpty())
            for(SNode root : roots) {
                counter += (count(root.getChilds()) + 1);
            }
        return counter;
    }
}
