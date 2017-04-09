package br.com.teampenha.quizsocial.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionGenerator {
    private List<AlternativeQuestion> mWomanAlternatives;
    private List<AlternativeQuestion> mGeneralAlternatives;

    private ObjectMapper mMapper;

    public QuestionGenerator(String womanJson, String generalJson) throws IOException {
        mMapper = new ObjectMapper();

        mWomanAlternatives = generateAlternatives(womanJson);
        mGeneralAlternatives = generateAlternatives(generalJson);
    }


    public ArrayList<Question> generateQuestions(final int totalQuestions) {
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < totalQuestions; i++) {
            AlternativeQuestion.Category category = getRandomCategory();
            AlternativeQuestion one = generateRandomAlternativeBy(category, mWomanAlternatives);
            AlternativeQuestion two = generateRandomAlternativeBy(category, mGeneralAlternatives);

            questions.add(new Question(one, two));
        }

        return questions;
    }

    private AlternativeQuestion.Category getRandomCategory() {
        AlternativeQuestion.Category[] categories = AlternativeQuestion.Category.values();
        Random categoryRandom = new Random();
        int categoryIndex = categoryRandom.nextInt(categories.length);
        return categories[categoryIndex];
    }

    private AlternativeQuestion generateRandomAlternativeBy(AlternativeQuestion.Category category,
                                                            List<AlternativeQuestion> alternatives) {

        List<AlternativeQuestion> filteredAlternatives = new ArrayList<>();
        for (AlternativeQuestion alternative : alternatives) {
            if (alternative.getCategory().equals(category)) {
                filteredAlternatives.add(alternative);
            }
        }

        return getRandomAlternative(filteredAlternatives);
    }

    private AlternativeQuestion getRandomAlternative(List<AlternativeQuestion> alternatives) {
        Random random = new Random();
        int randomIndex = random.nextInt(alternatives.size());
        return alternatives.get(randomIndex);
    }

    private List<AlternativeQuestion> generateAlternatives(String json) throws IOException {
        TypeReference<List<AlternativeQuestion>> typeReference = new TypeReference<List<AlternativeQuestion>>() {};
        List<AlternativeQuestion> alternatives = extractListFrom(json, typeReference);
        return alternatives;
    }

    private <T> T extractListFrom(String json, TypeReference<T> type) throws IOException {
        return mMapper.readValue(json, type);
    }

}
