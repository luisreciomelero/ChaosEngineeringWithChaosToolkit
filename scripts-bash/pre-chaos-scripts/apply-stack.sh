#!/bin/bash

cd "../../kubernetes-stack/yamls-pre-chaos"

#kubectl create ns "ingress-nginx"


#sleep 10

echo "Arrancamos: Ingress-gateway"

cd "istio"

kubectl apply -f "ingress-gateway.yaml"

cd "../http-requester"

kubectl apply -f "."

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


cd "../../../scripts-bash/pre-chaos-scripts/"