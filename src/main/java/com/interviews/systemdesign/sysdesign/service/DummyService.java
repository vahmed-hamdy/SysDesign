package com.interviews.systemdesign.sysdesign.service;

import com.interviews.systemdesign.sysdesign.exception.KeyNotFoundException;
import com.interviews.systemdesign.sysdesign.exception.SanctionedKeyException;
import com.interviews.systemdesign.sysdesign.model.DummySanctionsLists;
import com.interviews.systemdesign.sysdesign.model.Pair;
import com.interviews.systemdesign.sysdesign.repository.DummyRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DummyService {
    private final DummyRepo repo;
    private final DummySanctionsLists dummySanctionsLists;

    public List<Pair> getAll() {
        List<Pair> pairs = repo.findAll();
        return pairs;
    }
    public Long getOne(String key) {
        if (dummySanctionsLists.isSanctioned(key)) {
            throw new SanctionedKeyException("Invalid Key " + key);
        }
        return repo.findAll().stream().filter(pair -> pair.getKey().equals(key)).findFirst().map(Pair::getVal).orElseThrow(() -> new KeyNotFoundException("not found"));
    }

    public Pair addSomething(Pair pair) {
        return repo.save(pair);
    }

    public boolean exists(Pair somePair) {
        return repo.exists(Example.of(somePair));
    }

    public List<Pair> getSorted() {
        return repo.findAll(Sort.by(Sort.Direction.ASC, "val"));
    }

    public List<Pair> getPaged() {
        return repo.findAll(PageRequest.of(0, 2)).getContent();
    }

}
