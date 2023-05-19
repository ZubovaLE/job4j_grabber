package ru.job4j.lsp.exampleThree;

public class ThirdRule {
    public static void main(String[] args) {
        Participant participant = new FinalStageParticipant(new Passport(1, 11));
        participant.setPassport(
                new Passport(-1, -1)
        );
    }
}
