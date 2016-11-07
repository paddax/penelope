package com.winbro.penelope;

import javax.vecmath.Matrix3d;

/**
 * Created by Peter Davis on 05/11/2016.
 */
public class MatrixTest {

    public static void main(String[] args) {
        Matrix3d m = new Matrix3d();
        m.m00 = 1;
        m.m10 = -4;
        m.m20 = -2;
        m.m01 = 2;
        m.m11 = 1;
        m.m21 = 2;
        m.m02 = 3;
        m.m12 = 2;
        m.m22 = -1;
        System.out.println(m);

        Matrix3d m1 = new Matrix3d();
        m1.m00 = 1;
        m1.m10 = -4;
        m1.m20 = -2;
        m1.m01 = 2;
        m1.m11 = 1;
        m1.m21 = 2;
        m1.m02 = 3;
        m1.m12 = 2;
        m1.m22 = -1;
        m1.invert();

        m1.mul(m);
        System.out.println(m1);
    }
}
