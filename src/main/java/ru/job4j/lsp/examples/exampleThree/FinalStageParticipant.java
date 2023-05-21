package ru.job4j.lsp.examples.exampleThree;

public class FinalStageParticipant extends Participant {
    public FinalStageParticipant(Passport passport) {
        super(passport);
    }

    @Override
    public void setPassport(Passport passport) {
        //something
        this.passport = passport;
    }
}
