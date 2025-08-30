package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.jpa;

import com.statementlabs.prosefa_backend.application.domain.model.Counter;
import com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository.CounterRepository;
import com.statementlabs.prosefa_backend.application.port.out.GenerateCodeSequence;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JpaSequenceGeneratorAdapter implements GenerateCodeSequence {
    private final CounterRepository counterRepository;
    public JpaSequenceGeneratorAdapter(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    @Transactional
    public String generateNewCodeSequence(String prefix) {
        String year = String.valueOf(LocalDate.now().getYear());
        String counterId = prefix + "_" + year + "_sequence";

        Counter counter = counterRepository.findById(counterId)
                .orElseGet(() -> {
                    Counter newCounter = new Counter();
                    newCounter.setId(counterId);
                    newCounter.setSeq(0L);
                    return newCounter;
                }); 
        counter.setSeq(counter.getSeq() + 1);
        counterRepository.save(counter);
        return String.format("%s-%s-%06d", prefix, year, counter.getSeq());
    }
}
