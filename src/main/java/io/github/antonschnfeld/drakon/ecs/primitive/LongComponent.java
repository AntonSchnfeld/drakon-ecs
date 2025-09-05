package io.github.antonschnfeld.drakon.ecs.primitive;

public interface LongComponent extends PrimitiveComponent {
    void add(long entity, long l1);

    void add(long entity, long l1, long l2);

    void add(long entity, long l1, long l2, long l3);

    void add(long entity, long l1, long l2, long l3, long l4);

    void add(long entity, long l1, long l2, long l3, long l4, long l5);

    void add(long entity, long l1, long l2, long l3, long l4, long l5, long l6);

    void add(long entity, long l1, long l2, long l3, long l4, long l5, long l6, long l7);

    void add(long entity, long l1, long l2, long l3, long l4, long l5, long l6, long l7, long l8);

    void add(long entity, long... longs);

    long get(long entity, int index);

    void get(long entity, long[] longs);

    long[] get(long entity);
}
