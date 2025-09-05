package io.github.antonschnfeld.drakon.ecs.primitive;

public interface FloatComponent extends PrimitiveComponent {
    void add(long entity, float f1);

    void add(long entity, float f1, float f2);

    void add(long entity, float f1, float f2, float f3);

    void add(long entity, float f1, float f2, float f3, float f4);

    void add(long entity, float f1, float f2, float f3, float f4, float f5);

    void add(long entity, float f1, float f2, float f3, float f4, float f5, float f6);

    void add(long entity, float f1, float f2, float f3, float f4, float f5, float f6, float f7);

    void add(long  entity, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8);

    void add(long entity, float... floats);

    float get(long entity, int index);

    void get(long entity, float[] floats);

    float[] get(long entity);
}
