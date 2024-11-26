package com.cobook.CoBook_BE.service;

import com.cobook.CoBook_BE.model.Space;
import com.cobook.CoBook_BE.repository.SpaceRepository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    public SpaceService(SpaceRepository spaceRepository){
        this.spaceRepository = spaceRepository;
    }

    public Space createSpace(Space space) {
        try {
            return spaceRepository.spaceCreate(space.getSid(), space.getSname(), space.getTag(), space.getBudget());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Space> findBySpaceName(String sname) {
        return spaceRepository.findBySpaceName(sname);
    }

    public Optional<Space> findBySpaceId(String sid) {
        return spaceRepository.findBySpaceId(sid);
    }

    public Optional<Space> findBySpaceTag(String tag) {
        return spaceRepository.findBySpaceTag(tag);
    }

    public Space spaceUpdate(String sid, String sname, String tag, int budget) {
        return spaceRepository.spaceUpdate(sid, sname, tag, budget);
    }

    public Space spaceDelete(String sid, String sname){
        return spaceRepository.spaceDelete(sid, sname);
    }

}
