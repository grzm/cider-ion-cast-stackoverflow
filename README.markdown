# x.ion-cast

Repro test case for `java.lang.StackOverflowError` when using `datomic.ion.cast`
with CIDER.

`datomic.ion.cast` is a namespace that provides logging for Datomic
Ions via Cloudwatch when running in AWS, or to other such as STDOUT,
STDERR, or a file when [running locally][local-workflow].

[local-workflow]: https://docs.datomic.com/cloud/ions/ions-monitoring.html#local-workflow

Redirecting to `:stdout` causes a StackOverFlow. Redirecting to
`:stderr` or a file works as expected.

## To reproduce

  1. Open `src/com/grzm/x/ion_cast.clj`
  2. Launch CIDER (via `cider-jack-in`)
  3. Eval the current namespace
  4. Redirect cast output to stdout. (command to eval provided in comment)
  5. Cast an event (example provided in comment)

First lines of the resulting StackOverFlow error output:
```

  Show: Project-Only All
  Hide: Clojure Java REPL Tooling Duplicates  (288 frames hidden)

1. Unhandled java.lang.StackOverflowError
   (No message)

                   RT.java:  544  clojure.lang.RT/seqFrom
                   RT.java:  533  clojure.lang.RT/seq
                  core.clj:  137  clojure.core/seq
                  core.clj: 2927  clojure.core/drop/step
                  core.clj: 2932  clojure.core/drop/fn
              LazySeq.java:   42  clojure.lang.LazySeq/sval
              LazySeq.java:   51  clojure.lang.LazySeq/seq
                   RT.java:  531  clojure.lang.RT/seq
                  core.clj:  137  clojure.core/seq
                  core.clj: 2884  clojure.core/take/fn
              LazySeq.java:   42  clojure.lang.LazySeq/sval
              LazySeq.java:   51  clojure.lang.LazySeq/seq
                   RT.java:  531  clojure.lang.RT/seq
              Numbers.java: 1397  clojure.lang.Numbers/byte_array
                   out.clj:   98  cider.nrepl.middleware.out/print-stream/fn
                       nil:   -1  cider.nrepl.middleware.out.proxy$java.io.OutputStream$ff19274a/write
          PrintStream.java:  480  java.io.PrintStream/write
        StreamEncoder.java:  221  sun.nio.cs.StreamEncoder/writeBytes
        StreamEncoder.java:  291  sun.nio.cs.StreamEncoder/implFlushBuffer
        StreamEncoder.java:  295  sun.nio.cs.StreamEncoder/implFlush
        StreamEncoder.java:  141  sun.nio.cs.StreamEncoder/flush
   OutputStreamWriter.java:  229  java.io.OutputStreamWriter/flush
       BufferedWriter.java:  254  java.io.BufferedWriter/flush
                   out.clj:  101  cider.nrepl.middleware.out/print-stream/fn
                       nil:   -1  cider.nrepl.middleware.out.proxy$java.io.OutputStream$ff19274a/flush
          PrintStream.java:  338  java.io.PrintStream/flush
        StreamEncoder.java:  297  sun.nio.cs.StreamEncoder/implFlush
        StreamEncoder.java:  141  sun.nio.cs.StreamEncoder/flush
   OutputStreamWriter.java:  229  java.io.OutputStreamWriter/flush
       BufferedWriter.java:  254  java.io.BufferedWriter/flush
                   out.clj:  101  cider.nrepl.middleware.out/print-stream/fn
                       nil:   -1  cider.nrepl.middleware.out.proxy$java.io.OutputStream$ff19274a/flush
          PrintStream.java:  338  java.io.PrintStream/flush
        StreamEncoder.java:  297  sun.nio.cs.StreamEncoder/implFlush
        StreamEncoder.java:  141  sun.nio.cs.StreamEncoder/flush
   OutputStreamWriter.java:  229  java.io.OutputStreamWriter/flush
       BufferedWriter.java:  254  java.io.BufferedWriter/flush
                   out.clj:  101  cider.nrepl.middleware.out/print-stream/fn
                       nil:   -1  cider.nrepl.middleware.out.proxy$java.io.OutputStream$ff19274a/flush
          PrintStream.java:  338  java.io.PrintStream/flush
        StreamEncoder.java:  297  sun.nio.cs.StreamEncoder/implFlush
        StreamEncoder.java:  141  sun.nio.cs.StreamEncoder/flush
   OutputStreamWriter.java:  229  java.io.OutputStreamWriter/flush
       BufferedWriter.java:  254  java.io.BufferedWriter/flush
```
