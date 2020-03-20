package com.nerdSpace;

import com.nerdSpace.GUI.GuiDelegator;

public class Divergence{
    GuiDelegator delegator;
    Divergence() {
        delegator = GuiDelegator.init(true);

    }
    public static void main(String[] args) {
        Divergence divergence = new Divergence();
        divergence.delegator.reload();
    }


}
