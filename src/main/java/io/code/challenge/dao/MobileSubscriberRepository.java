package io.code.challenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.code.challenge.entities.MobileSubscriber;

@Repository
public interface MobileSubscriberRepository extends JpaRepository<MobileSubscriber,Long>{

}









