package com.reactivepractice.topic3;

import java.util.Iterator;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class IteratorPublisher<T> implements Publisher<T> {

    private final Iterator<T> source;

    public IteratorPublisher(Iterator<T> source) {
        this.source = source;
    }

    @Override
    public void subscribe(Subscriber<? super T> subscriber) {
        subscriber.onSubscribe(new Subscription() {

            private volatile boolean isCancelled = false;
            private long requested = 0;

            @Override
            public void request(long n) {

                if (n <= 0) {
                    subscriber.onError(new IllegalArgumentException(
                        "Request must be positive"));
                    return;
                }

                requested += n;

                while (!isCancelled && requested > 0 && source.hasNext()) {
                    requested--;
                    subscriber.onNext(source.next());
                }

                if (!isCancelled && !source.hasNext()) {
                    subscriber.onComplete();
                }
            }

            @Override
            public void cancel() {
                isCancelled = true;
            }
        });
    }
}
