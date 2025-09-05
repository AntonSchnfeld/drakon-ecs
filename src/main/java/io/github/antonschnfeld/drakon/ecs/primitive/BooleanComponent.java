package io.github.antonschnfeld.drakon.ecs.primitive;

public interface BooleanComponent extends PrimitiveComponent {
    void add(long entity, boolean b1);

    void add(long entity, boolean b1, boolean b2);

    void add(long entity, boolean b1, boolean b2, boolean b3);

    void add(long entity, boolean b1, boolean b2, boolean b3, boolean b4);

    void add(long entity, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5);

    void add(long entity, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6);

    void add(long entity, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7);

    void add(long entity, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8);

    void add(long entity, boolean... booleans);

    boolean get(long entity, int index);

    void get(long entity, boolean[] booleans);

    boolean[] get(long entity);
}
