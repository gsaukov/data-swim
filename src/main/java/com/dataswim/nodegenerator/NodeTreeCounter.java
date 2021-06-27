package com.dataswim.nodegenerator;

import com.dataswim.nodegenerator.model.Node;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeTreeCounter {

    public int count(List<Node> roots) {
        int counter = 0;
        if(!roots.isEmpty())
            for(Node root : roots) {
                counter += (count(root.getChilds()) + 1);
            }
        return counter;
    }
}
