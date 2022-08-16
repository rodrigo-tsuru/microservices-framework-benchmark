Quarkus guide: https://quarkus.io/guides/reactive-routes

# Performance test:
```
wrk -t4 -c128 -d30s "http://localhost:8080/" -s pipeline.lua --latency -- / 16
```
