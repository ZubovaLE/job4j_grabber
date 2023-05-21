package ru.job4j.isp.examples.exampleTwo;

/**
 * Имеется интерфейс сотового телефона.
 * Известно, что все телефоны могут звонить и отправлять сообщения (call(), sendMessage()), но не все могут подключаться
 * к интернету, включать NFC и сканировать QR-коды (connectToInternet(), turnOnNFC(), scanQRCode()).
 * Это значит, что в определённых моделях телефонов придётся реализовывать ненужные методы.
 * Поэтому здесь необходимо разделение интерфейсов (например, оставить интерфейс CellPhone с первыми тремя методами,
 * добавить интерфейс Internet с методом connect(),
 * добавить интерфейс NFC с методом turnOnNFC()
 * и добавить интерфейс QRCode с методом scanQRCode()).
 */
public interface CellPhone {
    void call();

    void sendMessage();

    void connectToInternet();

    void turnOnNFC();

    boolean scanQRCode();
}
