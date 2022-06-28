package com.example.reviewohrid.service.serviceImpl;
import com.example.reviewohrid.model.User;
import com.example.reviewohrid.model.UserAnswerStatus;
import com.example.reviewohrid.service.UserAnswerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;


@Service
public class UserAnswerStatusServiceImpl implements UserAnswerStatusService
{
    @Autowired
    private EntityManager entityManager;

    @Override
    public ArrayList<UserAnswerStatus> getAllByUserId(Integer id)
    {
        String queryFindAllByUserId = "SELECT * FROM useranswerstatus WHERE user_id=?1";
        Query nativeQueryFindAllByUserId = entityManager.createNativeQuery(queryFindAllByUserId, UserAnswerStatus.class);
        nativeQueryFindAllByUserId.setParameter(1, id);
        return (ArrayList<UserAnswerStatus>) nativeQueryFindAllByUserId.getResultList();
    }

    @Override
    public Integer getUserId(String email)
    {
        String queryFindUserId = "SELECT * FROM user WHERE email=?1";
        Query nativeQueryFindUserId = entityManager.createNativeQuery(queryFindUserId, User.class);
        nativeQueryFindUserId.setParameter(1, email);
        User user = (User) nativeQueryFindUserId.getSingleResult();
        return user.getId();
    }
}