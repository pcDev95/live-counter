package com.livecounter.lc.service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CounterService {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(0L);

        emitters.add(emitter);
        emitter.onCompletion(() -> {
            emitters.remove(emitter);
            log.info("SSE emitter completed and removed");
        });
        emitter.onTimeout(() -> {
            emitters.remove(emitter);
            log.info("SSE emitter timed out and removed");
        });
        emitter.onError((e) -> {
            emitters.remove(emitter);
            log.info("SSE emitter error and removed: {}", e.getMessage());
        });

        log.info("New SSE subscriber added. Total subscribers: {}", emitters.size());
        return emitter;
    }

    public void broadcast(int count) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().name("countUpdate").data(count));
            } catch (IOException e) {
                log.error("Failed to send to emitter (client likely disconnected): {}", e.getMessage());
            } catch (Exception e) {
                log.error("Unexpected error broadcasting to emitter: {}", e.getMessage());
            }
        }
    }

}
