#!/bin/bash

cd "../../kubernetes-stack/yamls-pre-chaos"

#kubectl create ns "ingress-nginx"


#sleep 10

echo "Arrancamos: Ingress-gateway"

cd "istio"

kubectl apply -f "ingress-gateway.yaml"

cd "../yamls-post-chaos/istio"

#kubectl apply -f "istio-prueba.yaml"

cd "../../yamls-pre-chaos/postgres-db"

#cd "../postgres-db"

#kubectl apply -f "ingress-controller-nginx-tfm.yaml,ingress-rules-tfm.yaml,ingress-service-tfm.yaml"

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

cd "../VirtualServices"

kubectl apply -f "."


cd "../../../scripts-bash/pre-chaos/"