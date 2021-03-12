#!/bin/bash

cd "../kubernetes-stack/yamls"

kubectl delete -f "ingress-gateway.yaml"

kubectl delete -f "kafka-svc.yaml,kafka-dep.yaml,postgres-dep.yaml,zookeeper-svc.yaml,zookeeper-dep.yaml,mg-deploy.yaml,ms-create.yaml,ms-delete.yaml,ms-mailer.yaml,ms-update.yaml,fakesmtp.yaml, ms-read.yaml"

#kubectl delete ns "ingress-nginx"

cd "../../scripts-bash/"