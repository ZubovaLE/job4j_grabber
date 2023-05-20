package ru.job4j.isp.menu.exampleOne;

/**
 * Интерфейс пирогов.
 * Известно, что все пироги необходимо запекать и посыпать сахарной пудрой.
 * Однако не во все пироги потребуется дополнительно добавлять клубнику или варенье.
 * А значит, придётся реализовывать ненужные методы.
 * Поэтому здесь необходимо разделение интерфейсов.
 */
public interface Cake {
    void bake();

    void powderWithSugar();

    void addStrawberry(float kg);

    void addJam(float ml);

    void addDecorations();
}
