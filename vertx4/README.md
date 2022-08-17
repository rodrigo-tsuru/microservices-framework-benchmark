[Vertx](http://vertx.io/)

Build:
```
mvn package
```

Run:

```
java -jar vertx/target/vertx-fat.jar
```

Server will start on port 8080.

# Performance Results

Running 30s test @ http://localhost:8080/
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     6.53ms   21.67ms 436.77ms   97.68%
    Req/Sec   133.41k    36.27k  240.48k    78.24%
  Latency Distribution
     50%    2.66ms
     75%    5.36ms
     90%   11.53ms
     99%   76.75ms
  15846224 requests in 30.09s, 1.36GB read
Requests/sec: 526544.31
Transfer/sec:     46.20MB
