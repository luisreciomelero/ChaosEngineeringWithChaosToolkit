#!/bin/bash

cd "../../kubernetes-stack/yamls-pre-chaos/istio"

kubectl delete -f "ingress-gateway.yaml"

cd "../postgres-db"

kubectl delete -f "postgres-dep.yaml"

cd "../kafka-app"

kubectl delete -f "zookeeper-svc.yaml,zookeeper-dep.yaml,kafka-svc.yaml,kafka-dep.yaml"

cd "../mg-app"

kubectl delete -f "mg-deploy.yaml"

cd "../crud-app"

kubectl delete -f "ms-create.yaml,ms-delete.yaml,ms-update.yaml,ms-read.yaml"

cd "../mailer-app"

kubectl delete -f "."

cd "../sendersms-app"

kubectl delete -f "."

cd "../VirtualServices"

kubectl delete -f "."

cd "../../../scripts-bash/pre-chaos/"