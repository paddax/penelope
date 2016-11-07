package com.winbro.penelope.engine;

import com.winbro.penelope.Part;
import com.winbro.penelope.PartType;

/**
 * Created by Peter Davis on 06/11/2016.
 */
public interface IF4Persistence {

    Part findPart(PartType parttype, String partName);
}
