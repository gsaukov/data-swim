package com.dataswim.nodegenerator.batch;

import com.dataswim.nodegenerator.model.SNode;
import org.springframework.batch.item.ItemProcessor;

public class SNodeProcessor implements ItemProcessor<SNode, SNode> {

    @Override
    public SNode process(SNode item) {
        return null;
    }
}
