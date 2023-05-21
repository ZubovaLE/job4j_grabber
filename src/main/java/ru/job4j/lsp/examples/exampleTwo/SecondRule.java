package ru.job4j.lsp.examples.exampleTwo;

public class SecondRule {
    public static void main(String[] args) {
        ClientInfo clientInfo = new WebClientInfo();
        System.out.println(clientInfo.getBirthDate(202000, 1, 1));
    }
}
