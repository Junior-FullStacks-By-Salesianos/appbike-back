package com.salesianos.triana.appbike.revision;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RevisionService {
    private final RevisionRepository revisionRepository;

    public List<Revision> findAll() {
        return revisionRepository.findAll();
    }
}
