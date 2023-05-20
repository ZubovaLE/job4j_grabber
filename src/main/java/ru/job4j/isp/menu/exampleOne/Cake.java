package ru.job4j.isp.menu.exampleOne;

/**
 * Имеется интерфейс пирога.
 * Известно, что все пироги необходимо запекать и посыпать сахарной пудрой (bake(), powderWithSugar()).
 * Однако не во все пироги потребуется дополнительно добавлять клубнику или варенье, или украшения.
 * А значит, придётся реализовывать ненужные методы.
 * Поэтому здесь необходимо разделение интерфейсов (например, оставить интерфейс Cake с первыми двумя методами,
 * добавить интерфейс Berries с методом addBerries(Berry berry),
 * добавить интерфейс Decoration с методом addDecorations(Decoration decoration)).
 */
public interface Cake {
    void bake();

    void powderWithSugar();

    void addStrawberry(float kg);

    void addJam(float ml);

    void addDecorations();
}
