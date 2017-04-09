package br.com.teampenha.quizsocial.model;

public class LessFactor implements QuestionFactor {

    private final AlternativeQuestion mAlternativeOne;
    private final AlternativeQuestion mAlternativeTwo;

    public LessFactor(AlternativeQuestion one, AlternativeQuestion two) {
        mAlternativeOne = one;
        mAlternativeTwo = two;
    }

    @Override
    public AlternativeQuestion correctAlternative() {

        AlternativeQuestion correctAlternative = mAlternativeOne;
        if (mAlternativeOne.getValue() > mAlternativeTwo.getValue()) {
            correctAlternative = mAlternativeTwo;
        }

        return correctAlternative;
    }
}
