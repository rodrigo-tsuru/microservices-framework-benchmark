Quarkus guide: https://quarkus.io/guides/reactive-routes

# Performance test:
```
wrk -t4 -c128 -d30s "http://localhost:8080/" -s pipeline.lua --latency -- / 16
```
# Performance Results:
Running 30s test @ http://localhost:8080/
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     6.05ms    6.74ms  86.43ms   88.16%
    Req/Sec    88.27k    27.03k  175.84k    75.25%
  Latency Distribution
     50%    3.84ms
     75%    7.65ms
     90%   13.89ms
     99%   34.04ms
  10548096 requests in 30.05s, 794.70MB read
Requests/sec: 350999.73
Transfer/sec:     26.44MB
