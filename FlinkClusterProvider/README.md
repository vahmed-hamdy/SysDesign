### Creating a cluster (Deprecated)

```
curl -X POST http://localhost:8015/cluster -H "Content-Type: application/json" -d '{"id":"some-example"}' 
```

### Creating a new application 

```
 curl -X POST http://localhost:8015/application  -H "Content-Type: application/json" -d '{"name":"some-app","parallelism":"1","jarLocation":"s3://sysdesigncdkstack-artifacts3bucket2de51773-1v6tsswoseu1m/flink117-1.0-SNAPSHOT.jar"}'
```

- Get KO pod

`k get pods -l app.kubernetes.io/name=flink-kubernetes-operator  -o=jsonpath='{.items[0].metadata.name}'`