package com.janbe.quiz.logic.settings;

import com.janbe.quiz.userInterface.quiz.longListQuiz.LongListQuizActivity;
import com.janbe.quiz.userInterface.quiz.multipleChoiceQuiz.MultipleChoiceQuizActivity;
import com.janbe.quiz.data.factory.RepositoryFactory;
import com.janbe.quiz.domain.Subject;
import com.janbe.quiz.logic.quiz.NormalQuiz;
import com.janbe.quiz.logic.quiz.Quiz;
import com.janbe.quiz.logic.quiz.result.NormalResult;
import com.janbe.quiz.logic.quiz.questionAnswerManager.LongListQuestionAnswerManager;
import com.janbe.quiz.logic.quiz.questionAnswerManager.MultipleChoiceQuestionAnswerManager;
import com.janbe.quiz.logic.quiz.questionAnswerManager.QuestionAnswerManager;
import com.janbe.quiz.logic.quiz.score.RationScore;
import com.janbe.quiz.logic.quiz.score.Score;
import com.janbe.quiz.logic.quiz.score.TimedScore;

import java.io.Serializable;

/**
 * Created by janbe on 06-Mar-18.
 */

// QuizSettings stores user inputted options, to eventually create a quiz specific for user
// - Talking to class with QuizSetting enum
public class QuizSettings implements Serializable {

    // Stored quiz values
    private QuizSetting quizType;
    private Subject subject;
    private int amountOfQuestions;
    private QuizSetting scoreType;

    // getQuiz() adjusts quiz to stored values and returns
    // - RepositoryFactory init happens in main
    public Quiz createQuiz(RepositoryFactory repositoryFactory) {

        // Settings in real objects & values
        QuestionAnswerManager questionAnswerManager;
        Subject subject;
        int amountOfQuestions;
        Score score;

        // Getting quiz type
        if (quizType == QuizSetting.SETTING_QUIZ_MULTIPLE_CHOICE) {
            questionAnswerManager = new MultipleChoiceQuestionAnswerManager(repositoryFactory);

        } else {
            questionAnswerManager = new LongListQuestionAnswerManager(repositoryFactory);

        }

        // Subject and amount of questions
        subject = this.subject;
        amountOfQuestions = this.amountOfQuestions;

        // Getting score type
        if (scoreType == QuizSetting.SETTING_SCORE_RATIO_SCORE) {
            score = new RationScore();

        } else {
            score = new TimedScore();

        }

        // Create quiz
        Quiz quiz = new NormalQuiz(
                questionAnswerManager,
                subject,
                amountOfQuestions,
                score,
                new NormalResult());

        // Return quiz
        return quiz;

    }

    public QuizSettings(QuizSetting quizType,
                        Subject subject,
                        int amountOfQuestions,
                        QuizSetting scoreType) {

        setQuizType(quizType);
        this.subject = subject;
        this.amountOfQuestions = amountOfQuestions;
        this.scoreType = scoreType;

    }

    // SETTERS, called from GUI
    public void setQuizType(QuizSetting quizType) {
        this.quizType = quizType;

    }

    public void setSubject(Subject subject) {
        this.subject = subject;

    }

    public void setAmountOfQuestions(int amountOfQuestions) {
        this.amountOfQuestions = amountOfQuestions;

    }

    public void setScore(QuizSetting scoreType) {
        this.scoreType = scoreType;

    }

    // GETTERS
    public Class getQuizScreen() {

        if (quizType == QuizSetting.SETTING_QUIZ_MULTIPLE_CHOICE) {
            return MultipleChoiceQuizActivity.class;

        }

        return LongListQuizActivity.class;

    }

    // Methods to display settings in MainActivity
    public QuizSetting getQuizType() {
        return quizType;

    }

    public Subject getSubject() {
        return subject;

    }

    public int getAmountOfQuestions() {
        return amountOfQuestions;

    }

    public QuizSetting getScoreType() {
        return scoreType;

    }

}