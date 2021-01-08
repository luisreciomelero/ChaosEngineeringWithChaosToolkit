#!/bin/bash

cd "../kubernetes-stack/yamls"

kubectl create ns "ingress-nginx"

sleep 10

echo "Arrancamos: Ingress"

kubectl apply -f "ingress-controller-nginx-tfm.yaml,ingress-rules-tfm.yaml,ingress-service-tfm.yaml"

echo "Arrancamos: Postgres"

kubectl apply -f "postgres-dep.yaml"

echo "Arrancamos: Zookeeper"

kubectl apply -f "zookeeper-svc.yaml,zookeeper-dep.yaml"

sleep 30

echo "Arrancamos: Kafka"

kubectl apply -f "kafka-svc.yaml,kafka-dep.yaml"

sleep 30

echo "Arrancamos microservicios"

kubectl apply -f "mg-deploy.yaml,ms-create.yaml,ms-delete.yaml,ms-mailer.yaml,ms-update.yaml,fakesmtp.yaml"

cd "../../scripts-bash/"