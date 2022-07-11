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
        String queryFindAllByUserId = "SELECT * FROM useranswerstatus WHERE users=?1";
        Query nativeQueryFindAllByUserId = entityManager.createNativeQuery(queryFindAllByUserId, UserAnswerStatus.class);
        nativeQueryFindAllByUserId.setParameter(1, id);
        return (ArrayList<UserAnswerStatus>) nativeQueryFindAllByUserId.getResultList();
    }

}