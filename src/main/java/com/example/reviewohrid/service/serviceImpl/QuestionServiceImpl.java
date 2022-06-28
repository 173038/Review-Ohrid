package com.example.reviewohrid.service.serviceImpl;

import com.example.reviewohrid.DTO.QuestionDTO;
import com.example.reviewohrid.DTO.UserQuestionDTO;
import com.example.reviewohrid.exceptions.InvalidCreatorException;
import com.example.reviewohrid.model.Question;
import com.example.reviewohrid.model.User;
import com.example.reviewohrid.repository.QuestionRepository;
import com.example.reviewohrid.repository.UserRepository;
import com.example.reviewohrid.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService
{
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean checkTitle(String email)
    {
        if ("".equals(email))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkQuestion(String question)
    {
        if ("".equals(question))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkEmail(String email)
    {
        if ("".equals(email) || email == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<String> validateAndPost(QuestionDTO questionDTO)
    {
        ArrayList<String> res = new ArrayList<>();
        if (checkTitle(questionDTO.getTitle()) == false)
        {
            res.add("Title is empty. Please enter title");
        }
        if (checkQuestion(questionDTO.getQuestion()) == false)
        {
            res.add("Question is empty. Please enter question");
        }
        if (checkEmail(questionDTO.getEmail()) == false)
        {
            res.add("You are not logged in. Please log in to ask a question");
        }
        if (res.isEmpty())
        {
            Question question = new Question();
            question.setCreator(questionDTO.getEmail());
            question.setModifier(questionDTO.getEmail());
            question.setTitle(questionDTO.getTitle());
            question.setQuestion(questionDTO.getQuestion());
            question.setCreatedDate(Instant.now()); //TODO: ovde probvi
            questionRepository.save(question);
        }
        return res;
    }

    @Override
    public List<Question> getAllQuestions()
    {
        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteQuestion(UserQuestionDTO userQuestionDTO) throws InvalidCreatorException
    {
        Question questionToDelete = questionRepository.findById(userQuestionDTO.getQuestionId());
        User user = userRepository.findById(userQuestionDTO.getUserId());
        if (questionToDelete.getCreator().equals(user.getEmail()))
        {
            String queryDeleteQuestion = "DELETE FROM question WHERE question.id=?1";
            Query nativeQueryDeleteQuestion = entityManager.createNativeQuery(queryDeleteQuestion);
            nativeQueryDeleteQuestion.setParameter(1, questionToDelete.getId());
            nativeQueryDeleteQuestion.executeUpdate();
        }
        else
        {
            throw new InvalidCreatorException(
                    "Unable to delete this question. User `" + user.getEmail() + "` is not the creator of the question!");
        }
    }
}
