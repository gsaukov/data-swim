package com.dataswim.nodegenerator;

import com.dataswim.nodegenerator.model.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NodeGenerator {

    public Node generate(Node parent, int childCount, int depth) {
        Node node = new Node(Integer.toString(depth), parent);
        List<Node> children = new ArrayList<>();
        if (depth > 0) {
            for (int i = 0; i < childCount; i++) {
                children.add(generate(node, childCount, depth - 1));
            }
        }
        node.addChilds(children);
        return node;
    }
}
