#!/bin/bash

cd "../kubernetes-stack/yamls"


kubectl delete -f "ingress-controller-nginx-tfm.yaml,ingress-rules-tfm.yaml,ingress-service-tfm.yaml,kafka-svc.yaml,kafka-dep.yaml,postgres-dep.yaml,zookeeper-svc.yaml,zookeeper-dep.yaml,mg-deploy.yaml,ms-create.yaml,ms-delete.yaml,ms-mailer.yaml,ms-update.yaml,fakesmtp.yaml"

kubectl delete ns "ingress-nginx"

cd "../../scripts-bash/"