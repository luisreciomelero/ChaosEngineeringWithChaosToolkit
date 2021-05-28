#!/bin/bash

cd "../../kubernetes-stack/yamls-post-chaos"


echo "Arrancamos: Ingress-gateway"

cd "istio"

kubectl apply -f "ingress-gateway.yaml"


cd "../postgres-db"

echo "Arrancamos: Postgres"

kubectl apply -f "postgres-dep.yaml"

cd "../kafka-app"

echo "Arrancamos: Zookeeper"

kubectl apply -f "zookeeper-svc.yaml,zookeeper-dep.yaml"

sleep 30

echo "Arrancamos: Kafka"

kubectl apply -f "kafka-svc.yaml,kafka-dep.yaml"

cd "../mg-app"

sleep 30

echo "Arrancamos microservicios"


kubectl apply -f "mg-deploy.yaml"

cd "../crud-app"

kubectl apply -f "."

cd "../mailer-app"


kubectl apply -f "config-mailer.yaml,ms-mailer.yaml"

cd "../sendersms-app"

kubectl apply -f "."

cd "../virtualservices"

kubectl apply -f "."

sleep 30

echo "Habilitamos las metricas y los HorizontalPodAutoscaler"


cd "../horizontalPodAutoScalers"

# Por cuestiones de recursos limitaciones de recursos de minikube
# únicamente habilitamos el autoescalamiento de read y mg.
# Si se contase con más recursos podríamos ejecutar un kubectl apply -f "."

#kubectl apply -f "metrics-components.yaml,mg-hpa.yaml,ms-read-hpa.yaml"

cd "../../../scripts-bash/post-chaos-scripts/"