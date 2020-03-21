package com.nerdSpace;

import com.nerdSpace.GUI.GuiDelegator;

public class Divergence{

    public static void main(String[] args) {
        GuiDelegator delegator = new GuiDelegator(true);
        while (true) {
            delegator.reload();
        }

    }


}
