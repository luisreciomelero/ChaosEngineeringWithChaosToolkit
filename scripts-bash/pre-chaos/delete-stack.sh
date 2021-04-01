#!/bin/bash

cd "../../kubernetes-stack/yamls-pre-chaos"

cd "istio"

kubectl delete -f "ingress-gateway.yaml"

cd ".."

cd "postgres-db"

kubectl delete -f "postgres-dep.yaml"

cd ".."

cd "kafka-app"

kubectl delete -f "zookeeper-svc.yaml,zookeeper-dep.yaml,kafka-svc.yaml,kafka-dep.yaml"

cd ".."

cd "mg-app"

kubectl delete -f "mg-deploy.yaml"

cd ".."

cd "crud-app"

kubectl delete -f "ms-create.yaml,ms-delete.yaml,ms-update.yaml,ms-read.yaml"

cd ".."

cd "mailer-app"

kubectl delete -f "ms-mailer.yaml,fakesmtp.yaml"

cd "../../../scripts-bash/pre-chaos/"