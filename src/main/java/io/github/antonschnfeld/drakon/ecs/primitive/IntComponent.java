package io.github.antonschnfeld.drakon.ecs.primitive;

public interface IntComponent extends PrimitiveComponent {
    void add(long entity, int i1);

    void add(long entity, int i1, int i2);

    void add(long entity, int i1, int i2, int i3);

    void add(long entity, int i1, int i2, int i3, int i4);

    void add(long entity, int i1, int i2, int i3, int i4, int i5);

    void add(long entity, int i1, int i2, int i3, int i4, int i5, int i6);

    void add(long entity, int i1, int i2, int i3, int i4, int i5, int i6, int i7);

    void add(long entity, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    void add(long entity, int... ints);

    int get(long entity, int index);

    void get(long entity, int[] ints);

    int[] get(long entity);
}
