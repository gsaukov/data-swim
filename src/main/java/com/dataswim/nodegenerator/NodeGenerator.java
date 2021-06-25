package com.dataswim.nodegenerator;

import java.util.UUID;
import com.dataswim.nodegenerator.model.Node;
import org.springframework.stereotype.Service;


@Service
public class NodeGenerator {

    public Node generate(Node parent, int childCount, int depth) {
        Node node = new Node(Integer.toString(depth), parent);

        return node;
    }
}
