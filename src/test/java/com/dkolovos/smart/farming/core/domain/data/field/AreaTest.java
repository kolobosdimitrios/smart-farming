/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dkolovos.smart.farming.core.domain.data.field;

import org.junit.jupiter.api.Test;

/**
 *
 * @author dimitrioskolovos
 */
public class AreaTest {

    @Test
    void testCalculationsAreCorrectAndSafe() {
        Area.RectangularArea rectangularArea = new Area.RectangularArea(
                new double[]{0.0, 0.0, 1.0, 1.0},
                new double[]{0.0, 1.0, 1.0, 0.0}
        );
        
        float actual = rectangularArea.getSurfaceSize();
        assert actual == 12363683990f;
    }

}
