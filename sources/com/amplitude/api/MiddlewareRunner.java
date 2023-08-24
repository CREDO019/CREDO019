package com.amplitude.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class MiddlewareRunner {
    private final ConcurrentLinkedQueue<Middleware> middlewares = new ConcurrentLinkedQueue<>();

    public void add(Middleware middleware) {
        this.middlewares.add(middleware);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runMiddlewares(final List<Middleware> list, MiddlewarePayload middlewarePayload, final MiddlewareNext middlewareNext) {
        if (list.size() == 0) {
            middlewareNext.run(middlewarePayload);
        } else {
            list.get(0).run(middlewarePayload, new MiddlewareNext() { // from class: com.amplitude.api.MiddlewareRunner.1
                @Override // com.amplitude.api.MiddlewareNext
                public void run(MiddlewarePayload middlewarePayload2) {
                    MiddlewareRunner middlewareRunner = MiddlewareRunner.this;
                    List list2 = list;
                    middlewareRunner.runMiddlewares(list2.subList(1, list2.size()), middlewarePayload2, middlewareNext);
                }
            });
        }
    }

    public boolean run(MiddlewarePayload middlewarePayload) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        run(middlewarePayload, new MiddlewareNext() { // from class: com.amplitude.api.MiddlewareRunner.2
            @Override // com.amplitude.api.MiddlewareNext
            public void run(MiddlewarePayload middlewarePayload2) {
                atomicBoolean.set(true);
            }
        });
        return atomicBoolean.get();
    }

    public void run(MiddlewarePayload middlewarePayload, MiddlewareNext middlewareNext) {
        runMiddlewares(new ArrayList(this.middlewares), middlewarePayload, middlewareNext);
    }
}
