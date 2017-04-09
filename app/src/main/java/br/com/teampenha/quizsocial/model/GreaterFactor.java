package br.com.teampenha.quizsocial.model;

public class GreaterFactor implements QuestionFactor {

    private AlternativeQuestion mAlternativeOne;
    private AlternativeQuestion mAlternativeTwo;

    public GreaterFactor(AlternativeQuestion one, AlternativeQuestion two) {
        mAlternativeOne = one;
        mAlternativeTwo = two;
    }

    @Override
    public AlternativeQuestion correctAlternative() {
        AlternativeQuestion correctAlternative = mAlternativeOne;
        if (mAlternativeOne.getValue() < mAlternativeTwo.getValue()) {
            correctAlternative = mAlternativeTwo;
        }

        return correctAlternative;
    }
}
