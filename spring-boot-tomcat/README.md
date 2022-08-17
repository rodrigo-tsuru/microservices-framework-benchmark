```
mvn clean install
mvn spring-boot:run
```

# Results:
Running 30s test @ http://localhost:8080
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   169.85ms  160.21ms   1.50s    85.28%
    Req/Sec     3.37k     2.08k    9.76k    66.11%
  Latency Distribution
     50%  118.83ms
     75%  236.76ms
     90%  387.76ms
     99%  725.17ms
  401896 requests in 30.08s, 48.36MB read
Requests/sec:  13361.93
Transfer/sec:      1.61MB
