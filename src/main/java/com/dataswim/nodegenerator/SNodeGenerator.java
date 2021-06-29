package com.dataswim.nodegenerator;

import com.dataswim.nodegenerator.model.SNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SNodeGenerator {

    public SNode generate(SNode parent, int childCount, int depth) {
        SNode snode = new SNode(Integer.toString(depth), parent);
        List<SNode> children = new ArrayList<>();
        if (depth > 0) {
            for (int i = 0; i < childCount; i++) {
                children.add(generate(snode, childCount, depth - 1));
            }
        }
        snode.addChilds(children);
        return snode;
    }
}
