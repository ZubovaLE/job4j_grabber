package ru.job4j.lsp.examples.exampleThree;

public class Participant {
    protected Passport passport;

    public Participant(Passport passport) {
        validate(passport);
        this.passport = passport;
    }

    private void validate(Passport passport) {
        if (passport.getSeries() < 1 || passport.getSeries() > 9999) {
            throw new IllegalArgumentException("Invalid passport series!");
        }
        if (passport.getNumber() < 1 || passport.getNumber() > 999_999) {
            throw new IllegalArgumentException("Invalid passport number!");
        }
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        validate(passport);
        this.passport = passport;
    }
}
