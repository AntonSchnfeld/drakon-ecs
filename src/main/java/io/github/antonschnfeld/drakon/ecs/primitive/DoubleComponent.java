package io.github.antonschnfeld.drakon.ecs.primitive;

public interface DoubleComponent extends PrimitiveComponent {
    void add(long entity, double d1);

    void add(long entity, double d1, double d2);

    void add(long entity, double d1, double d2, double d3);

    void add(long entity, double d1, double d2, double d3, double d4);

    void add(long entity, double d1, double d2, double d3, double d4, double d5);

    void add(long entity, double d1, double d2, double d3, double d4, double d5, double d6);

    void add(long entity, double d1, double d2, double d3, double d4, double d5, double d6, double d7);

    void add(long entity, double d1, double d2, double d3, double d4, double d5, double d6, double d7, double d8);

    void add(long entity, double... doubles);

    double get(long entity, int index);

    void get(long entity, double[] doubles);

    double[] get(long entity);
}
