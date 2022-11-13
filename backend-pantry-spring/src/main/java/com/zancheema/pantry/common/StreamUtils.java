package com.zancheema.pantry.common;

import java.util.stream.Stream;

public class StreamUtils {
    public static <T> Stream<T> stream(Iterable<T> iterable) {
        return org.springframework.data.util.StreamUtils.createStreamFromIterator(iterable.iterator());
    }
}
