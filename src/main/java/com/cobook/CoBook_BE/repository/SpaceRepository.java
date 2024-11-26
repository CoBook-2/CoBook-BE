package com.cobook.CoBook_BE.repository;

import java.util.List;
import java.util.Optional;
import com.cobook.CoBook_BE.model.Space;

public interface SpaceRepository {

    Space spaceCreate(String sid, String sname, String tag, int budget);
    Optional<Space> findBySpaceName(String sname);
    Optional<Space> findBySpaceTag(String tag);
    Optional<Space> findBySpaceId(String sid);
    List<Space> findAll();
    Space spaceUpdate(String sid, String sname, String tag, int budget);
    Space spaceDelete(String sid, String sname);

}
