package com.example.reviewohrid.service.serviceImpl;

import com.example.reviewohrid.DTO.AnswerDTO;
import com.example.reviewohrid.DTO.UserAnswerStatusDTO;
import com.example.reviewohrid.exceptions.InvalidAnswerException;
import com.example.reviewohrid.model.Answer;
import com.example.reviewohrid.model.Question;
import com.example.reviewohrid.model.User;
import com.example.reviewohrid.model.UserAnswerStatus;
import com.example.reviewohrid.repository.AnswerRepository;
import com.example.reviewohrid.repository.QuestionRepository;
//import com.example.reviewohrid.repository.UserAnswerStatusRepository;
import com.example.reviewohrid.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;



@Service
public class AnswerServiceImpl implements AnswerService
{
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

   @Autowired
    private EntityManager entityManager;

//    private final UserAnswerStatusRepository userAnswerStatusRepository;
//
//    public AnswerServiceImpl(UserAnswerStatusRepository userAnswerStatusRepository) {
//        this.userAnswerStatusRepository = userAnswerStatusRepository;
//    }

    @Override
    public boolean checkEmail(String email)
    {
        if (email == null || "".equals(email))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkAnswer(String answer)
    {
        if (answer == null || "".equals(answer))
        {
            return false;
        }
        return true;
    }

    @Override
    public Answer validateAndSave(AnswerDTO answerDTO) throws InvalidAnswerException
    {
        ArrayList<String> res = new ArrayList<>();
        if (checkEmail(answerDTO.getEmail()) == false)
        {
            throw new InvalidAnswerException("You are not logged in. Please log in to answer the question");
        }
        if (checkAnswer(answerDTO.getAnswer()) == false)
        {
            throw new InvalidAnswerException("Answer is empty. Please enter answer");
        }
        if (res.isEmpty())
        {
            Answer answer = new Answer();
            answer.setAnswer(answerDTO.getAnswer());
            answer.setCreator(answerDTO.getEmail());
            answer.setDownvotes(0);
            answer.setUpvotes(0);
            answer.setCreatedDate(Instant.now());
            Question question = questionRepository.findById(answerDTO.getId());
            if (question == null)
            {
                throw new InvalidAnswerException("Invalid question ID");
            }
            answer.setQuestion(question);
            return answerRepository.save(answer);
        }
        return null;
    }

    @Override
    public ArrayList<Answer> getAllAnswerForQuestion(Integer id)
    {
        return answerRepository.getAllAnswerForQuestion(id);  //ovde trebit question id d ago zejme, vo slucajov 3
    }

    @Override
    @Transactional
    public Integer getUserIdFromEmail(String email)
    {
        String queryFindUserId = "SELECT * FROM user WHERE email=?1";
        Query nativeQueryFindUserId = entityManager.createNativeQuery(queryFindUserId, User.class);
        nativeQueryFindUserId.setParameter(1, email);
        User user = (User) nativeQueryFindUserId.getSingleResult();
        return user.getId();
    }

    @Override
    @Transactional
    public ArrayList<UserAnswerStatus> checkIfPreviousVoted(Integer userId, Integer answerId)
    {
        String queryCheckIfUpVoted = "SELECT * FROM useranswerstatus WHERE answer_id=?1 AND user_id=?2";
        Query nativeQueryCheckIfUpVoted = entityManager.createNativeQuery(queryCheckIfUpVoted, UserAnswerStatus.class);
        nativeQueryCheckIfUpVoted.setParameter(1, answerId);
        nativeQueryCheckIfUpVoted.setParameter(2, userId);
        return (ArrayList<UserAnswerStatus>) nativeQueryCheckIfUpVoted.getResultList();
    }

    @Override
    @Transactional
    public void insertNewUserAnswerStatus(Integer userId, Integer answerId, boolean status)
    {
        String queryToInsert = "INSERT INTO useranswerstatus(answer_id,user_id,status) VALUES (?1,?2,?3)";
        Query nativeQueryToInsert = entityManager.createNativeQuery(queryToInsert);
        nativeQueryToInsert.setParameter(1, answerId);
        nativeQueryToInsert.setParameter(2, userId);
        nativeQueryToInsert.setParameter(3, status);
        nativeQueryToInsert.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteUserAnswerStatus(Integer userId, Integer answerId)
    {
        String queryDeleteRow = "DELETE FROM useranswerstatus WHERE user_id=?1 AND answer_id=?2";
        Query nativeQueryDeleteRow = entityManager.createNativeQuery(queryDeleteRow);
        nativeQueryDeleteRow.setParameter(1, userId);
        nativeQueryDeleteRow.setParameter(2, answerId);
        nativeQueryDeleteRow.executeUpdate();
    }

    @Override
    @Transactional
    public void updateUpVotes(Integer newUpVote, Integer answerId)
    {
        String queryUpdateUpVotes = "UPDATE answer SET upvotes=upvotes+?2 WHERE answer_id=?1";
        Query nativeQueryUpdateUpVotes = entityManager.createNativeQuery(queryUpdateUpVotes);
        nativeQueryUpdateUpVotes.setParameter(1, answerId);
        nativeQueryUpdateUpVotes.setParameter(2, newUpVote);
        nativeQueryUpdateUpVotes.executeUpdate();
    }

    @Override
    @Transactional
    public void updateDownVotes(Integer newDownVote, Integer answerId)
    {
        String queryUpdateDownVotes = "UPDATE answer SET downvotes=downvotes+?2 WHERE answer_id=?1";
        Query nativeQueryUpdateDownVotes = entityManager.createNativeQuery(queryUpdateDownVotes);
        nativeQueryUpdateDownVotes.setParameter(1, answerId);
        nativeQueryUpdateDownVotes.setParameter(2, newDownVote);
        nativeQueryUpdateDownVotes.executeUpdate();
    }

    @Override
    @Transactional
    public void updateUserAnswerStatus(Integer userId, Integer answerId, boolean status)
    {
        String queryUpdateStatusToTrue = "UPDATE useranswerstatus SET status=?3 WHERE answer_id=?1 AND user_id=?2";
        Query nativeQueryUpdateStatusToTrue = entityManager.createNativeQuery(queryUpdateStatusToTrue);
        nativeQueryUpdateStatusToTrue.setParameter(1, answerId);
        nativeQueryUpdateStatusToTrue.setParameter(2, userId);
        nativeQueryUpdateStatusToTrue.setParameter(3, status);
        nativeQueryUpdateStatusToTrue.executeUpdate();
    }

    @Override
    @Transactional
    public void upVoteAnswer(UserAnswerStatusDTO userAnswerStatusDTO)
    {
        Integer userId = getUserIdFromEmail(userAnswerStatusDTO.getUserEmail());
        ArrayList<UserAnswerStatus> userAnswerStatusArrayList = checkIfPreviousVoted(userId, userAnswerStatusDTO.getAnswerId());
        if (userAnswerStatusArrayList.isEmpty())
        {
            insertNewUserAnswerStatus(userId, userAnswerStatusDTO.getAnswerId(), true);
            updateUpVotes(1, userAnswerStatusDTO.getAnswerId());
        }
        else
        {
            if (userAnswerStatusArrayList.get(0).isStatus() == true)
            {
                updateUpVotes(-1, userAnswerStatusDTO.getAnswerId());
                deleteUserAnswerStatus(userId, userAnswerStatusDTO.getAnswerId());
            }
            else if (userAnswerStatusArrayList.get(0).isStatus() == false)
            {
                updateUpVotes(1, userAnswerStatusDTO.getAnswerId());
                updateDownVotes(-1, userAnswerStatusDTO.getAnswerId());
                updateUserAnswerStatus(userId, userAnswerStatusDTO.getAnswerId(), true);
            }
        }
    }

    @Override
    @Transactional
    public void downVoteAnswer(UserAnswerStatusDTO userAnswerStatusDTO)
    {
        Integer userId = getUserIdFromEmail(userAnswerStatusDTO.getUserEmail());
        ArrayList<UserAnswerStatus> userAnswerStatusArrayList = checkIfPreviousVoted(userId, userAnswerStatusDTO.getAnswerId());
        if (userAnswerStatusArrayList.isEmpty())
        {
            insertNewUserAnswerStatus(userId, userAnswerStatusDTO.getAnswerId(), false);
            updateDownVotes(1, userAnswerStatusDTO.getAnswerId());
        }
        else
        {
            if (userAnswerStatusArrayList.get(0).isStatus() == true)
            {
                updateDownVotes(1, userAnswerStatusDTO.getAnswerId());
                updateUpVotes(-1, userAnswerStatusDTO.getAnswerId());
                updateUserAnswerStatus(userId, userAnswerStatusDTO.getAnswerId(), false);
            }
            else if (userAnswerStatusArrayList.get(0).isStatus() == false)
            {
                updateDownVotes(-1, userAnswerStatusDTO.getAnswerId());
                deleteUserAnswerStatus(userId, userAnswerStatusDTO.getAnswerId());
            }
        }
    }
}