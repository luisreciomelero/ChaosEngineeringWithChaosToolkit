#!/bin/bash

cd "../../kubernetes-stack/yamls-pre-chaos"

#kubectl create ns "ingress-nginx"


#sleep 10

echo "Arrancamos: Ingress-gateway"

cd "istio"

kubectl apply -f "ingress-gateway.yaml"

cd ".."

#kubectl apply -f "ingress-controller-nginx-tfm.yaml,ingress-rules-tfm.yaml,ingress-service-tfm.yaml"

echo "Arrancamos: Postgres"

cd "postgres-db"

kubectl apply -f "postgres-dep.yaml"

cd ".."

cd "kafka-app"

echo "Arrancamos: Zookeeper"

kubectl apply -f "zookeeper-svc.yaml,zookeeper-dep.yaml"

sleep 30

echo "Arrancamos: Kafka"

kubectl apply -f "kafka-svc.yaml,kafka-dep.yaml"

cd ".."

sleep 30

echo "Arrancamos microservicios"

cd "mg-app"

kubectl apply -f "mg-deploy.yaml"

cd ".."

cd "crud-app"

kubectl apply -f "ms-create.yaml,ms-delete.yaml,ms-update.yaml,ms-read.yaml"

cd ".."

cd "mailer-app"

kubectl apply -f "ms-mailer.yaml,fakesmtp.yaml"

cd "../../../scripts-bash/pre-chaos/"